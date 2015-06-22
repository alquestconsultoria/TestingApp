package uk.co.myexample.jamescoggan.api;

/*
 * File: app/src/main/java/uk.co.myexample.jamescoggan/api/UserReloadEvent.java
 * <p/>
 * Description: Reload the user fragment event
 *
 * @author James Coggan
 * @version 1.0
 * @since 2015-05-10
 */
public class UserReloadEvent {

    private Integer id;

    public UserReloadEvent(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }
}
