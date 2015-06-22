package uk.co.myexample.jamescoggan.api;

import android.graphics.Bitmap;
import android.os.Build;

/*
 * File: app/src/main/java/uk.co.myexample.jamescoggan/api/ImageTools.java
 * <p/>
 * Description: Tools to get image sizes, etc
 *
 * @author James Coggan
 * @version 1.0
 * @since 2015-05-10
 */
public class ImageTools {
    public static int byteSizeOf(Bitmap bitmap) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            return bitmap.getAllocationByteCount();
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR1) {
            return bitmap.getByteCount();
        } else {
            return bitmap.getRowBytes() * bitmap.getHeight();
        }
    }
}
