package uk.co.myexample.jamescoggan.api;

import com.squareup.otto.Bus;

/*
 * File: app/src/main/java/uk.co.myexample.jamescoggan/api/ApiBus.java
 * <p/>
 * Description: Instance of the Api bus, used for to comunicate between fragments, services, etc
 *
 * @author James Coggan
 * @version 1.0
 * @since 2015-05-10
 */
public class ApiBus {
    private static final Bus BUS = new Bus();

    public static Bus getInstance() {
        return BUS;
    }
}
