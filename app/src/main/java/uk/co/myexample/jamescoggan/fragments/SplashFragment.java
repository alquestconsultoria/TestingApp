package uk.co.myexample.jamescoggan.fragments;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.squareup.otto.Subscribe;

import java.util.List;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;
import uk.co.myexample.jamescoggan.AppActivity;
import uk.co.myexample.jamescoggan.AppDialog;
import uk.co.myexample.jamescoggan.R;
import uk.co.myexample.jamescoggan.api.ApiBus;
import uk.co.myexample.jamescoggan.api.ApiConst;
import uk.co.myexample.jamescoggan.app.App;
import uk.co.myexample.jamescoggan.data.DatabaseExecute;
import uk.co.myexample.jamescoggan.data.DatabaseExecuteParams;
import uk.co.myexample.jamescoggan.data.DatabaseResultEvent;
import uk.co.myexample.jamescoggan.data.UserData;

/*
 * File: app/src/main/java/uk.co.myexample.jamescoggan/fragments/SplashFragment.java
 * <p/>
 * Description: First Fragment to be loaded, wait 2 seconds displaying the company logo and download the user list from the server
 *
 * @author James Coggan
 * @version 1.0
 * @since 2015-05-10
 */
public class SplashFragment extends Fragment {
    View view;
    private ProgressDialog spinner;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        this.view = inflater.inflate(R.layout.fragment_splash, container, false);
        return this.view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // Display company logo for 2 seconds before download data
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                getUsers(); // Get data
            }
        }, 2000);
    }

    /*
     * Get the data from the server using the restclient, if sucess process the data, else issue error
     */
    private void getUsers() {
        showLoading();
        App.getRestClient().getUserService().getUsers(new Callback<List<UserData>>() {

            @Override
            public void success(List<UserData> result, Response response) {
                spinner.setMessage(getString(R.string.processing));
                processData(result);
            }

            @Override
            public void failure(RetrofitError error) {
                issueError();
                ApiConst.DLOG(error.getMessage());
            }
        });
    }

    private void showLoading() {
        try {
            spinner = AppDialog.spinner(getView().getContext(), getString(R.string.please_wait));
            spinner.show();
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    private void dissmissLoading() {
        spinner.dismiss();
    }

    /*
     * Call the asynctak to load the data in the sqlite database
     */
    private void processData(List<UserData> userList) {
        DatabaseExecuteParams params = new DatabaseExecuteParams(getActivity().getApplicationContext(), userList, ApiConst.REQUEST_DATABASE_INSERT_USERS);
        DatabaseExecute databaseExecute = new DatabaseExecute();
        databaseExecute.execute(params);
    }

    /*
     * Receive the data from the asynctask a
     */
    @Subscribe
    public void onAsyncTaskResult(DatabaseResultEvent event) {
        if (event.getRequest() == ApiConst.REQUEST_DATABASE_INSERT_USERS) {
            if (event.getResult()) {
                dissmissLoading();
                ((AppActivity) getActivity()).showMain();
            } else issueError();
        }
    }

    private void issueError() {
        dissmissLoading();
        AppDialog.showAlertDialog(getActivity(), getString(R.string.geterror_title), getString(R.string.geterror_message));
    }

    @Override
    public void onResume() {
        super.onResume();

        // Register ourselves so that we can provide the initial value.
        ApiBus.getInstance().register(this);
    }

    @Override
    public void onPause() {
        super.onPause();

        // Always unregister when an object no longer should be on the bus.
        ApiBus.getInstance().unregister(this);
    }
}