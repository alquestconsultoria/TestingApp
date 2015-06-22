package uk.co.myexample.jamescoggan.data;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;

import com.squareup.picasso.Picasso;

import java.io.IOException;

import uk.co.myexample.jamescoggan.api.ApiBus;
import uk.co.myexample.jamescoggan.api.ImageTools;
import uk.co.myexample.jamescoggan.formula.Formula;
import uk.co.myexample.jamescoggan.formula.Statistic;

/*
 * File: app/src/main/java/uk.co.myexample.jamescoggan/data/DatabaseStatisticsSave.java
 * <p/>
 * Description: Async task to save the statistics of the user/photos
 *
 * @author James Coggan
 * @version 1.0
 * @since 2015-05-10
 */
public class DatabaseStatisticsSave extends AsyncTask<DatabaseStatisticsParams, Void, Boolean> {

    private int request;
    Context context;

    @Override
    protected Boolean doInBackground(DatabaseStatisticsParams... URL) {
        boolean result = false;
        final Bitmap image;
        context = DatabaseStatisticsParams.context;
        UserData user = DatabaseStatisticsParams.user;
        Formula formula = new Formula(context);
        try {
            image = Picasso.with(context).load(DatabaseStatisticsParams.image_url).get();
            int width = image.getWidth();
            Statistic statistic = new Statistic(formula.statisticDao);
            statistic.Save(user.getUserID(), user.getImageID(), user.getUserName(), ImageTools.byteSizeOf(image), width);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    protected void onPostExecute(Boolean result) {
        super.onPostExecute(result);
        ApiBus.getInstance().post(new DatabaseResultEvent(result, request));
    }
}
