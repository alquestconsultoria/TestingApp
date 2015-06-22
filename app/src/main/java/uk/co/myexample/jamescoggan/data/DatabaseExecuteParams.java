package uk.co.myexample.jamescoggan.data;

import android.content.Context;

import java.util.List;

/*
 * File: app/src/main/java/uk.co.myexample.jamescoggan/app/data/DatabaseExecuteParams.java
 * <p/>
 * Description: Parameters for the database async execution
 *
 * @author James Coggan
 * @version 1.0
 * @since 2015-05-10
 */
public class DatabaseExecuteParams {
    static Context context;
    static List<UserData> users;
    static int request;

    public DatabaseExecuteParams(Context context, List<UserData> users, int request) {

        this.context = context;
        this.users = users;
        this.request = request;
    }
}