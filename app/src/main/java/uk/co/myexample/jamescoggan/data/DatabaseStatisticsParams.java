package uk.co.myexample.jamescoggan.data;

import android.content.Context;

/*
 * File: app/src/main/java/uk.co.myexample.jamescoggan/app/data/DatabaseStatisticsParams.java
 * <p/>
 * Description: Parameters for the database async execution
 *
 * @author James Coggan
 * @version 1.0
 * @since 2015-05-10
 */
public class DatabaseStatisticsParams {
    static Context context;
    static String image_url;
    static UserData user;

    public DatabaseStatisticsParams(Context context, UserData user, String image_url) {

        this.context = context;
        this.user = user;
        this.image_url = image_url;
    }
}