package uk.co.myexample.jamescoggan.api.client.service;

import java.util.List;

import retrofit.Callback;
import retrofit.http.GET;
import uk.co.myexample.jamescoggan.data.UserData;

/**
 * File: app/src/main/java/uk.co.myexample.jamescoggan/api/client/service/UserService.java
 * <p/>
 * Description: Getter service for Users
 * <p/>
 *
 * @author James Coggan
 * @version 1.0
 * @since 2015-05-10
 */


public interface UserService {
    /**
     * Get the users from server
     *
     * @param response will return Callback<List<UserData>>
     */
    @GET("/")
    void getUsers(Callback<List<UserData>> response);
}
