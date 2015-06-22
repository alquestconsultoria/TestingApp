package uk.co.myexample.jamescoggan.app;

import android.app.Application;

import uk.co.myexample.jamescoggan.api.client.RestClient;

/*
 * File: app/src/main/java/uk.co.myexample.jamescoggan/app/App.java
 * <p/>
 * Description: Prepare and load the restClient
 *
 * @author James Coggan
 * @version 1.0
 * @since 2015-05-10
 */
public class App extends Application {
    private static RestClient restClient;

    @Override
    public void onCreate() {
        super.onCreate();

        restClient = new RestClient();
    }

    public static RestClient getRestClient() {
        if (restClient == null) restClient = new RestClient();
        return restClient;
    }
}