package uk.co.myexample.jamescoggan.fragments;

import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.otto.Subscribe;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import butterknife.OnClick;
import uk.co.myexample.jamescoggan.R;
import uk.co.myexample.jamescoggan.SwipeAdapter;
import uk.co.myexample.jamescoggan.api.ApiBus;
import uk.co.myexample.jamescoggan.api.UserReloadEvent;

/*
 * File: app/src/main/java/uk.co.myexample.jamescoggan/fragments/MainFragment.java
 * <p/>
 * Description: Main fragment, controlls the tabs, swipeadapter, etc
 *
 * @author James Coggan
 * @version 1.0
 * @since 2015-05-10
 */
public class MainFragment extends Fragment implements ViewPager.OnPageChangeListener {
    View view;
    ArrayList<Fragment> fragments;
    ViewPager contentPager;
    FragmentManager fm;
    HomeFragment homeFragment;
    UserFragment userFragment;
    AboutFragment aboutFragment;

    private SwipeAdapter swipeAdapter;

    private int tabState = 0;

    // Unique ID for each page
    public static final int PAGE_HOME = 0;
    public static final int PAGE_USER = 1;
    public static final int PAGE_ABOUT = 2;

    // ButterKnife, atribute id to view object
    @InjectView(R.id.home_title)
    TextView homeTitle;

    @InjectView(R.id.user_title)
    TextView userTitle;

    @InjectView(R.id.about_title)
    TextView aboutTitle;

    @InjectView(R.id.home_icon)
    ImageView homeIcon;

    @InjectView(R.id.user_icon)
    ImageView userIcon;

    @InjectView(R.id.about_icon)
    ImageView aboutIcon;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_main, container, false);
        ButterKnife.inject(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (savedInstanceState != null) {   // If saved tab, load
            tabState = savedInstanceState.getInt("tab_state");
        }
    }

    @Override
    public void onResume() {    // Reload state page on resume
        super.onResume();
        ApiBus.getInstance().register(this);
        loadPage();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("tab_state", tabState); // Save tab state for reload
    }

    private void loadPage() {

        homeFragment = (HomeFragment) Fragment.instantiate(this.getActivity(), HomeFragment.class.getName());
        userFragment = (UserFragment) Fragment.instantiate(this.getActivity(), UserFragment.class.getName());
        aboutFragment = (AboutFragment) Fragment.instantiate(this.getActivity(), AboutFragment.class.getName());

        fragments = new ArrayList<>();  // Load the fragments
        fragments.add(homeFragment);
        fragments.add(userFragment);
        fragments.add(aboutFragment);

        fm = getChildFragmentManager();
        contentPager = (ViewPager) view.findViewById(R.id.contentPager);
        swipeAdapter = new SwipeAdapter(fm, fragments);

        contentPager.setAdapter(swipeAdapter);
        contentPager.setOnPageChangeListener(this);

        selectTab(tabState);
    }

    private void selectTab(int position) {
        selectTab(position, true);
    }

    private void selectTab(int position, boolean changePage) {  // Set colors and selections when tab clicked
        tabState = position;

        homeTitle.setTextColor(getResources().getColor(R.color.tab_unselected_color));
        userTitle.setTextColor(getResources().getColor(R.color.tab_unselected_color));
        aboutTitle.setTextColor(getResources().getColor(R.color.tab_unselected_color));

        homeIcon.setSelected(false);
        homeIcon.setColorFilter(getResources().getColor(R.color.tab_unselected_color), PorterDuff.Mode.SRC_IN);
        userIcon.setSelected(false);
        userIcon.setColorFilter(getResources().getColor(R.color.tab_unselected_color), PorterDuff.Mode.SRC_IN);
        aboutIcon.setSelected(false);
        aboutIcon.setColorFilter(getResources().getColor(R.color.tab_unselected_color), PorterDuff.Mode.SRC_IN);


        switch (position) {
            case PAGE_HOME:
                homeTitle.setTextColor(getResources().getColor(R.color.tab_selected_color));
                homeIcon.setSelected(true);
                homeIcon.setColorFilter(getResources().getColor(R.color.tab_selected_color), PorterDuff.Mode.SRC_IN);
                break;
            case PAGE_USER:
                userTitle.setTextColor(getResources().getColor(R.color.tab_selected_color));
                userIcon.setSelected(true);
                userIcon.setColorFilter(getResources().getColor(R.color.tab_selected_color), PorterDuff.Mode.SRC_IN);
                break;
            case PAGE_ABOUT:
                aboutTitle.setTextColor(getResources().getColor(R.color.tab_selected_color));
                aboutIcon.setSelected(true);
                aboutIcon.setColorFilter(getResources().getColor(R.color.tab_selected_color), PorterDuff.Mode.SRC_IN);
                break;
        }

        if (changePage) {
            contentPager.setCurrentItem(position);
            swipeAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onPageScrollStateChanged(int state) {   // Used when page scrolled with "finger"
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
    }

    @OnClick({R.id.home_tab, R.id.user_tab, R.id.about_tab})
    /*
        On tab clicked, use selectTab for styling
     */
    public void tabClick(View view) {
        switch (view.getId()) {
            case R.id.home_tab:
                selectTab(PAGE_HOME);
                break;
            case R.id.user_tab:
                selectTab(PAGE_USER);
                break;
            case R.id.about_tab:
                selectTab(PAGE_ABOUT);
                break;
        }
    }

    @Override
    public void onPageSelected(int position) {
        selectTab(position, false);
    }

    @Override
    public void onDestroy() {   // Cleanup on destroy
        super.onDestroy();

        List<Fragment> frags = getChildFragmentManager().getFragments();
        if (frags != null) {
            for (Fragment f : frags) {
                if (f != null) {
                    FragmentTransaction ft = getChildFragmentManager().beginTransaction();
                    ft.remove(f);
                    ft.commit();
                }
            }
        }
    }

    @Subscribe
    public void onUserLoad(UserReloadEvent event) {
        selectTab(PAGE_USER);
    }

    @Override
    public void onPause() {
        super.onPause();

        // Always unregister when an object no longer should be on the bus.
        ApiBus.getInstance().unregister(this);
    }

    public void backButtonPressed() {
        switch (tabState) {
            case PAGE_ABOUT:
                selectTab(PAGE_HOME);
                break;
            case PAGE_USER:
                selectTab(PAGE_HOME);
                break;
            case PAGE_HOME:
                System.exit(0);
                break;
        }
    }
}


