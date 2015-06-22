package uk.co.myexample.jamescoggan.data;

/*
 * File: app/src/main/java/uk.co.myexample.jamescoggan/app/data/DatabaseResultEvent.java
 * <p/>
 * Description: Return data from the database asynctask event
 *
 * @author James Coggan
 * @version 1.0
 * @since 2015-05-10
 */
public class DatabaseResultEvent {

    private boolean result;
    private int request;

    public DatabaseResultEvent(boolean result, int request) {
        this.result = result;
        this.request = request;
    }

    public boolean getResult() {
        return result;
    }

    public int getRequest() {
        return request;
    }
}