package uk.co.myexample.jamescoggan.data;

import android.content.Context;
import android.os.AsyncTask;

import java.util.List;

import uk.co.myexample.jamescoggan.api.ApiBus;
import uk.co.myexample.jamescoggan.api.ApiConst;
import uk.co.myexample.jamescoggan.formula.Formula;
import uk.co.myexample.jamescoggan.formula.Statistic;
import uk.co.myexample.jamescoggan.formula.User;

/*
 * File: app/src/main/java/uk.co.myexample.jamescoggan/data/DatabaseExecute.java
 * <p/>
 * Description: Async task to save/load data to database without freezing the UI
 *
 * @author James Coggan
 * @version 1.0
 * @since 2015-05-10
 */
public class DatabaseExecute extends AsyncTask<DatabaseExecuteParams, Void, Boolean> {

    private int request;
    Context context;

    @Override
    protected Boolean doInBackground(DatabaseExecuteParams... URL) {
        boolean result = false;
        this.request = DatabaseExecuteParams.request;
        this.context = DatabaseExecuteParams.context;
        Formula formula = new Formula(context);
        switch (request) {
            case ApiConst.REQUEST_DATABASE_INSERT_USERS:
                Statistic statistic = new Statistic(formula.statisticDao);
                statistic.clearStatistic();
                User userData = new User(formula.userDao);
                userData.clearUser();

                List<UserData> users = DatabaseExecuteParams.users;

                for (UserData user : users) {
                    formula.userDao.create(user);
                }

                result = true;
                break;
        }
        return result;
    }

    @Override
    protected void onPostExecute(Boolean result) {
        super.onPostExecute(result);
        ApiBus.getInstance().post(new DatabaseResultEvent(result, request));
    }
}
