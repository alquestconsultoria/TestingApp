package uk.co.myexample.jamescoggan.formula;

import com.j256.ormlite.dao.RuntimeExceptionDao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import uk.co.myexample.jamescoggan.api.ApiConst;
import uk.co.myexample.jamescoggan.data.StatisticData;

/*
 * File: app/src/main/java/uk.co.myexample.jamescoggan/formula/Statistic.java
 * <p/>
 * Description: Statistic formula, creating and listing functions
 *
 * @author James Coggan
 * @version 1.0
 * @since 2015-05-10
 */
public class Statistic {
    private RuntimeExceptionDao<StatisticData, Integer> dao;

    public Statistic(RuntimeExceptionDao<StatisticData, Integer> statisticDao) {
        this.dao = statisticDao;
    }

    public void setStatistic(final Integer UserID, final Integer ImageID, final String UserName, final Integer NumberOfPosts, final double AverageImageSize, final Integer MaxPhotoWidth) {
        StatisticData d;
        List<StatisticData> l;
        try {
            l = dao.queryBuilder().where().eq("UserID", UserID).query();
            if (l.size() > 0) {
                d = l.get(0);
                d.setUserID(UserID);
                d.setImageID(ImageID);
                d.setUserName(UserName);
                d.setNumberOfPosts(NumberOfPosts);
                d.setAverageImageSize(AverageImageSize);
                d.setMaxPhotoWidth(MaxPhotoWidth);
                dao.update(d);
            } else {
                d = new StatisticData(UserID, ImageID, UserName, NumberOfPosts, AverageImageSize, MaxPhotoWidth);
                dao.create(d);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<StatisticData> getStatistics(Formula formula) {
        StatisticData statistic;
        ArrayList<StatisticData> result = new ArrayList<>();

        try {
            List<StatisticData> statistics = formula.statisticDao.queryBuilder().orderBy("Title", true).query();
            for (int i = 0; i < statistics.size(); i++) {
                statistic = statistics.get(i);
                result.add(statistic);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static StatisticData getStatistic(Formula formula, Integer id) {
        StatisticData result = null;

        try {
            List<StatisticData> statistics = formula.statisticDao.queryBuilder().where().eq("id", id).query();
            if (statistics.size() > 0) {
                result = statistics.get(0);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }


    public void clearStatistic() {
        StatisticData d;
        List<StatisticData> l;
        try {
            l = dao.queryBuilder().query();
            if (l.size() > 0) {
                for (int i = 0; i < l.size(); i++) {
                    d = l.get(i);
                    dao.delete(d);
                }
            }
            dao.updateRaw("delete from sqlite_sequence where name='statisticdata';");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void Save(Integer userID, Integer imageID, String userName, int size, int width) {
        StatisticData d;
        List<StatisticData> l;
        int posts = 0;
        int maxWidth = 0;
        double average = 0;
        try {
            l = dao.queryBuilder().where().eq("userID", userID).query();
            if (l.size() > 0) {
                d = l.get(0);
                if (width > d.getMaxPhotoWidth()) maxWidth = width;
                d.setMaxPhotoWidth(maxWidth);
                posts = d.getNumberOfPosts() + 1;
                average = (size + d.getAverageImageSize()) / posts;
                dao.update(d);
            } else {
                average = size;
                posts = 1;
                d = new StatisticData(userID, imageID, userName, 1, size, width);
                dao.create(d);
            }

            ApiConst.DLOG("Username:" + userName + " - Posts: " + String.valueOf(posts) + " - " + "AverageImageSize: "
                    + String.valueOf(average) + " - " + "MaxWidth: " + String.valueOf(maxWidth));
            dao.updateRaw("delete from sqlite_sequence where name='statisticdata';");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
