package uk.co.myexample.jamescoggan;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;

import java.util.List;

import uk.co.myexample.jamescoggan.fragments.MainFragment;
import uk.co.myexample.jamescoggan.fragments.SplashFragment;

/*
 * File: app/src/main/java/uk.co.myexample.jamescoggan/AppActivity.java
 * <p/>
 * Description: Main Application activity, contains 2 child fragments: splash  and Main
 *
 * @author James Coggan
 * @version 1.0
 * @since 2015-05-10
 */
public class AppActivity extends FragmentActivity {
    private SplashFragment splashFragment;
    private MainFragment mainFragment = null;
    //private ContactsFragment contactsFragment = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity);

        if (savedInstanceState == null) loadPage();
    }

    private void loadPage() {       // Loads the Splashscreen at launch
        splashFragment = new SplashFragment();
        mainFragment = new MainFragment();
        //contactsFragment = new ContactsFragment();
        showSplash();
    }

    private void showSplash() {     // Loads the container with the splash fragment
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.fragment_container, splashFragment);
        ft.commit();
    }

    public void showMain() {    // Loads the container with the main fragment
        if (mainFragment == null) mainFragment = new MainFragment();
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.fragment_container, mainFragment);
        ft.commit();
    }

    @Override
    protected void onDestroy() {    // On Destroy, remove all fragments
        super.onDestroy();

        List<Fragment> frags;
        frags = getSupportFragmentManager().getFragments();
        if (frags != null) {
            for (Fragment f : frags) {
                if (f != null) {
                    FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                    ft.remove(f);
                    ft.commit();
                }
            }
        }
    }

    @Override
    public void onBackPressed() {
        mainFragment.backButtonPressed();
    }
}
