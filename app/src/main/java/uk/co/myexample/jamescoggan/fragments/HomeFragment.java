package uk.co.myexample.jamescoggan.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import butterknife.ButterKnife;
import butterknife.InjectView;
import uk.co.myexample.jamescoggan.R;
import uk.co.myexample.jamescoggan.adapter.ListUsersAdapter;
import uk.co.myexample.jamescoggan.api.ApiBus;
import uk.co.myexample.jamescoggan.api.UserReloadEvent;

/*
 * File: app/src/main/java/uk.co.myexample.jamescoggan/fragments/HomeFragment.java
 * <p/>
 * Description: First tab of the app, List the users by Name
 *
 * @author James Coggan
 * @version 1.0
 * @since 2015-05-10
 */
public class HomeFragment extends Fragment {
    View view;
    private ListUsersAdapter adapter;

    @InjectView(R.id.home_list_users)
    ListView list_users;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.inject(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        adapter = new ListUsersAdapter(getActivity().getApplicationContext());
        list_users.setAdapter(adapter);
        list_users.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position,
                                    long id) { // Send the selection to the userfragment via bus
                ApiBus.getInstance().post(new UserReloadEvent(adapter.getID(position)));

            }
        });
    }
}
