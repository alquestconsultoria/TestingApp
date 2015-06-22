package uk.co.myexample.jamescoggan;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

/*
 * File: app/src/main/java/uk.co.myexample.jamescoggan/SwipeAdapter.java
 * <p/>
 * Description: Controls the swipe/movement of the tabs
 *
 * @author James Coggan
 * @version 1.0
 * @since 2015-05-10
 */
public class SwipeAdapter extends FragmentPagerAdapter {
    private final ArrayList<Fragment> fragments;

    public SwipeAdapter(FragmentManager fm, ArrayList<Fragment> fragments) {
        super(fm);
        this.fragments = fragments;
    }

    @Override
    public Fragment getItem(int position) { // Get the position of the fragments
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    } // Get the fragments count

}