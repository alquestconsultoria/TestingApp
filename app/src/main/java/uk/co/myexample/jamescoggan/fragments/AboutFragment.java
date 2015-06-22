package uk.co.myexample.jamescoggan.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import uk.co.myexample.jamescoggan.R;

/*
 * File: app/src/main/java/uk.co.myexample.jamescoggan/fragments/AboutFragment.java
 * <p/>
 * Description: Fragment containing a simple message of the apps creator
 *
 * @author James Coggan
 * @version 1.0
 * @since 2015-05-10
 */
public class AboutFragment extends Fragment {
    View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_about, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }
}
