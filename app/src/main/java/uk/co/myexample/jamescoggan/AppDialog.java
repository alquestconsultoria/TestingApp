package uk.co.myexample.jamescoggan;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.widget.Toast;

/**
 * Created by James Coggan on 5/1/15.
 * <p/>
 * File: app/src/main/java/uk.co.myexample.jamescoggan/AppDialog.java
 * <p/>
 * Description: Class with a simple dialog, toast and  spinner
 */
/*
 * File: app/src/main/java/uk.co.myexample.jamescoggan/AppDialog.java
 * <p/>
 * Description: Class with a simple dialog and toast
 *
 * @author James Coggan
 * @version 1.0
 * @since 2015-05-10
 */
public class AppDialog {
    public static void showAlertDialog(Activity activity, String title, String message) { // Creates a simple confirmation dialog with title and message
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(activity);
        alertDialogBuilder.setTitle(title);
        alertDialogBuilder
                .setMessage(message)
                .setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.dismiss();
                    }
                });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    public static void showToast(Activity activity, String message) {                   // Creats an informational toast, param: message
        Toast.makeText(activity.getBaseContext(), message, Toast.LENGTH_SHORT).show();
    }

    public static ProgressDialog spinner(Context context, String message) {
        ProgressDialog loadingBar;
        loadingBar = new ProgressDialog(context);
        loadingBar.setCancelable(true);
        loadingBar.setMessage(message);
        loadingBar.setCanceledOnTouchOutside(false);
        loadingBar.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        return loadingBar;
    }
}
