package uk.co.myexample.jamescoggan.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

import uk.co.myexample.jamescoggan.R;

/**
 * Created by James Coggan on 5/1/15.
 * <p/>
 * File: app/src/main/java/uk.co.myexample.jamescoggan/data/DatabaseHelper.java
 * sdf
 * Description: ORM Lite database helper. Create, delete and update
 */

public class DatabaseHelper extends OrmLiteSqliteOpenHelper {

    private static final String DATABASE_NAME = "uk.co.myexample.jamescoggan.db";   // Database name, located in /data/data/appName/databases/DATABASE_NAME
    private static final int DATABASE_VERSION = 2;
    private RuntimeExceptionDao<UserData, Integer> userRuntimeDao = null;
    private RuntimeExceptionDao<StatisticData, Integer> statisticRuntimeDao = null;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION, R.raw.data_config);
    }

    @Override
    public void onCreate(SQLiteDatabase db, ConnectionSource source) {
        try {
            TableUtils.createTable(source, UserData.class);
            TableUtils.createTable(source, StatisticData.class);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, ConnectionSource source, int oldVersion,
                          int newVersion) {
        try {
            TableUtils.dropTable(source, UserData.class, true);
            TableUtils.dropTable(source, StatisticData.class, true);
            onCreate(db, source);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public RuntimeExceptionDao<UserData, Integer> getUserDataDao() {
        if (userRuntimeDao == null) {
            userRuntimeDao = getRuntimeExceptionDao(UserData.class);
        }
        return userRuntimeDao;
    }

    public RuntimeExceptionDao<StatisticData, Integer> getStatisticDataDao() {
        if (statisticRuntimeDao == null) {
            statisticRuntimeDao = getRuntimeExceptionDao(StatisticData.class);
        }
        return statisticRuntimeDao;
    }

    @Override
    public void close() {
        super.close();
        userRuntimeDao = null;
        statisticRuntimeDao = null;
    }
}