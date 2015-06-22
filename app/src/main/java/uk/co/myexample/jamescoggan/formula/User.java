package uk.co.myexample.jamescoggan.formula;

import com.j256.ormlite.dao.RuntimeExceptionDao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import uk.co.myexample.jamescoggan.data.UserData;

/*
 * File: app/src/main/java/uk.co.myexample.jamescoggan/formula/User.java
 * <p/>
 * Description: User formula, creating and listing functions
 *
 * @author James Coggan
 * @version 1.0
 * @since 2015-05-10
 */
public class User {
    private RuntimeExceptionDao<UserData, Integer> dao;

    public User(RuntimeExceptionDao<UserData, Integer> userDao) {
        this.dao = userDao;
    }

    public void setUser(final Integer ID, final Integer ImageID, final String Title, final Integer UserID, final String UserName) {
        UserData d;
        List<UserData> l;
        try {
            l = dao.queryBuilder().where().eq("ID", ID).query();
            if (l.size() > 0) {
                d = l.get(0);
                d.setID(ID);
                d.setImageID(ImageID);
                d.setTitle(Title);
                d.setUserID(UserID);
                d.setUserName(UserName);
                dao.update(d);
            } else {
                d = new UserData(ID, ImageID, Title, UserID, UserName);
                dao.create(d);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<UserData> getUsers(Formula formula) {
        UserData user;
        ArrayList<UserData> result = new ArrayList<>();

        try {
            List<UserData> users = formula.userDao.queryBuilder().orderBy("Title", true).query();
            for (int i = 0; i < users.size(); i++) {
                user = users.get(i);
                result.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    public static UserData getUser(Formula formula, Integer id) {
        UserData result = null;

        try {
            List<UserData> users = formula.userDao.queryBuilder().where().eq("id", id).query();
            if (users.size() > 0) {
                result = users.get(0);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }


    public void clearUser() {
        UserData d;
        List<UserData> l;
        try {
            l = dao.queryBuilder().query();
            if (l.size() > 0) {
                for (int i = 0; i < l.size(); i++) {
                    d = l.get(i);
                    dao.delete(d);
                }
            }
            dao.updateRaw("delete from sqlite_sequence where name='userdata';");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
