package uk.co.myexample.jamescoggan.formula;

import android.content.Context;

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.RuntimeExceptionDao;

import uk.co.myexample.jamescoggan.data.DatabaseHelper;
import uk.co.myexample.jamescoggan.data.StatisticData;
import uk.co.myexample.jamescoggan.data.UserData;

/*
 * File: app/src/main/java/uk.co.myexample.jamescoggan/formula/Formula.java
 * <p/>
 * Description: Main formula file for database usage
 *
 * @author James Coggan
 * @version 1.0
 * @since 2015-05-10
 */
public class Formula {
    private DatabaseHelper dbHelper = null;
    public RuntimeExceptionDao<UserData, Integer> userDao;
    public RuntimeExceptionDao<StatisticData, Integer> statisticDao;

    public User user;
    public Statistic statistic;

    public Formula(Context context) {
        dbHelper = getDatabaseHelper(context);
        user = new User(userDao);
        userDao = dbHelper.getUserDataDao();
        statistic = new Statistic(statisticDao);
        statisticDao = dbHelper.getStatisticDataDao();
    }

    private DatabaseHelper getDatabaseHelper(Context context) {
        if (dbHelper == null) {
            dbHelper = OpenHelperManager.getHelper(context, DatabaseHelper.class);
        }
        return dbHelper;
    }
}
