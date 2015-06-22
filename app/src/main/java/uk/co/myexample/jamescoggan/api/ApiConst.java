package uk.co.myexample.jamescoggan.api;

import android.util.Log;

/*
 * File: app/src/main/java/uk.co.myexample.jamescoggan/api/ApiConst.java
 * <p/>
 * Description: Main configuration file
 *
 * @author James Coggan
 * @version 1.0
 * @since 2015-05-10
 */

public class ApiConst {

    // Base url for all requests
    public static final String BASE_URL = "http://challenge.superfling.com";

    // Request results, used for bus communication
    public static final int REQUEST_DATABASE_INSERT_USERS = 1001;

    /**
     * Dev mode enable and logging
     * Todo: Create separate log file
     */
    public static final boolean DEBUG_MODE = true;
    public static final String DEBUG_TAG = "JAMESCOGGAN";

    public static void DLOG(String message) {
        if (ApiConst.DEBUG_MODE) {
            Log.d(DEBUG_TAG, message);
        }
    }
}
