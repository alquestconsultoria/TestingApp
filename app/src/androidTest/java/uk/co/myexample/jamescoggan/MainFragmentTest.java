package uk.co.myexample.jamescoggan;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.test.ActivityUnitTestCase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import butterknife.ButterKnife;
import butterknife.InjectView;

/*
 * File: app/src/main/java/uk.co.myexample.jamescoggan/MainFragmentTest.java
 * <p/>
 * Description: Tests the MainFragment fragment
 *
 * @author James Coggan
 * @version 1.0
 * @since 2015-05-10
 */
public class MainFragmentTest extends ActivityUnitTestCase<MainFragmentTest.AppActivity> {

    public static class AppActivity extends FragmentActivity {
    }

    public static class MainFragment extends Fragment {
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

        View view;
        public boolean created;
        public boolean started;
        public boolean viewCreated;

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            created = true;
        }

        @Override
        public void onStart() {
            super.onStart();
            started = true;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            view = inflater.inflate(R.layout.fragment_main, container, false);
            ButterKnife.inject(this, view);
            return view;
        }

        @Override
        public void onViewCreated(View view, Bundle savedInstanceState) {
            super.onViewCreated(view, savedInstanceState);
            viewCreated = true;
        }
    }

    android.support.v4.app.FragmentManager mFragmentManager;

    public MainFragmentTest() {
        super(AppActivity.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        startActivity(new Intent(Intent.ACTION_MAIN), null, null);
        mFragmentManager = getActivity().getSupportFragmentManager();
    }

    public void testMainFragment() throws Exception {
        mFragmentManager.beginTransaction()
                .replace(android.R.id.content, new MainFragment())
                .commit();
        mFragmentManager.executePendingTransactions();

        MainFragment underTest = (MainFragment) mFragmentManager.findFragmentById(android.R.id.content);

        assertNotNull(underTest);

        getInstrumentation().callActivityOnStart(getActivity());
        getInstrumentation().callActivityOnResume(getActivity());
        mFragmentManager.executePendingTransactions();

        assertTrue(underTest.created);
        assertTrue(underTest.started);
        assertTrue(underTest.viewCreated);

        assertNotNull(underTest.homeTitle);
        assertNotNull(underTest.userTitle);
        assertNotNull(underTest.aboutTitle);
        assertNotNull(underTest.homeIcon);
        assertNotNull(underTest.userIcon);
        assertNotNull(underTest.aboutIcon);
    }
}