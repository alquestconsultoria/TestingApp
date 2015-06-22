package uk.co.myexample.jamescoggan.api.client;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit.RestAdapter;
import retrofit.android.AndroidLog;
import retrofit.converter.GsonConverter;
import uk.co.myexample.jamescoggan.api.ApiConst;
import uk.co.myexample.jamescoggan.api.client.service.UserService;

/*
 * File: app/src/main/java/uk.co.myexample.jamescoggan/api/client/RestClient.java
 * <p/>
 * Description: Rest client, used for all server communications, uses GSON to serialize the data.
 *
 *
 * @author James Coggan
 * @version 1.0
 * @since 2015-05-10
 */

public class RestClient {
    private UserService userService;

    public RestClient() {
        Gson gson = new GsonBuilder().create();
        RestAdapter.LogLevel logLevel;
        if (ApiConst.DEBUG_MODE)
            logLevel = RestAdapter.LogLevel.FULL;
        else
            logLevel = RestAdapter.LogLevel.NONE;

        RestAdapter restAdapter = new RestAdapter.Builder()
                .setLogLevel(logLevel).setLog(new AndroidLog(ApiConst.DEBUG_TAG))
                .setEndpoint(ApiConst.BASE_URL)
                .setConverter(new GsonConverter(gson))
                .build();

        userService = restAdapter.create(UserService.class);
    }

    public UserService getUserService() {
        return userService;
    }
}
