package uk.co.myexample.jamescoggan;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.test.ActivityUnitTestCase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;

/*
 * File: app/src/main/java/uk.co.myexample.jamescoggan/SplashFragmentTest.java
 * <p/>
 * Description: Tests the SplashFragment fragment
 *
 * @author James Coggan
 * @version 1.0
 * @since 2015-05-10
 */
public class SplashFragmentTest extends ActivityUnitTestCase<SplashFragmentTest.AppActivity> {

    public static class AppActivity extends FragmentActivity {
    }

    public static class SplashFragment extends Fragment {

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
            view = inflater.inflate(R.layout.fragment_splash, container, false);
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

    public SplashFragmentTest() {
        super(AppActivity.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        startActivity(new Intent(Intent.ACTION_MAIN), null, null);
        mFragmentManager = getActivity().getSupportFragmentManager();
    }

    public void testSplashFragment() throws Exception {
        mFragmentManager.beginTransaction()
                .replace(android.R.id.content, new SplashFragment())
                .commit();
        mFragmentManager.executePendingTransactions();

        SplashFragment underTest = (SplashFragment) mFragmentManager.findFragmentById(android.R.id.content);

        assertNotNull(underTest);

        getInstrumentation().callActivityOnStart(getActivity());
        getInstrumentation().callActivityOnResume(getActivity());
        mFragmentManager.executePendingTransactions();

        assertTrue(underTest.created);
        assertTrue(underTest.started);
        assertTrue(underTest.viewCreated);
    }
}