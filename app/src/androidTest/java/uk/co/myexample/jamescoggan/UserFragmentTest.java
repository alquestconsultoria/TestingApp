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
 * File: app/src/main/java/uk.co.myexample.jamescoggan/UserFragmentTest.java
 * <p/>
 * Description: Tests the Userfragment fragment
 *
 * @author James Coggan
 * @version 1.0
 * @since 2015-05-10
 */
public class UserFragmentTest extends ActivityUnitTestCase<UserFragmentTest.AppActivity> {

    public static class AppActivity extends FragmentActivity {
    }

    public static class UserFragment extends Fragment {
        @InjectView(R.id.user_name)
        TextView user_name;

        @InjectView(R.id.user_image)
        ImageView user_image;

        @InjectView(R.id.user_message)
        TextView user_message;

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
            view = inflater.inflate(R.layout.fragment_user, container, false);
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

    public UserFragmentTest() {
        super(AppActivity.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        startActivity(new Intent(Intent.ACTION_MAIN), null, null);
        mFragmentManager = getActivity().getSupportFragmentManager();
    }

    public void testUserFragment() throws Exception {
        mFragmentManager.beginTransaction()
                .replace(android.R.id.content, new UserFragment())
                .commit();
        mFragmentManager.executePendingTransactions();

        UserFragment underTest = (UserFragment) mFragmentManager.findFragmentById(android.R.id.content);

        assertNotNull(underTest);

        getInstrumentation().callActivityOnStart(getActivity());
        getInstrumentation().callActivityOnResume(getActivity());
        mFragmentManager.executePendingTransactions();

        assertTrue(underTest.created);
        assertTrue(underTest.started);
        assertTrue(underTest.viewCreated);

        assertNotNull(underTest.user_name);
        assertNotNull(underTest.user_image);
        assertNotNull(underTest.user_message);
    }
}
