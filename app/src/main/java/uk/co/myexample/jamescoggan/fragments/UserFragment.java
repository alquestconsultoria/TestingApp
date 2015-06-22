package uk.co.myexample.jamescoggan.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.otto.Subscribe;
import com.squareup.picasso.Picasso;

import butterknife.ButterKnife;
import butterknife.InjectView;
import uk.co.myexample.jamescoggan.R;
import uk.co.myexample.jamescoggan.api.Animations;
import uk.co.myexample.jamescoggan.api.ApiBus;
import uk.co.myexample.jamescoggan.api.ApiConst;
import uk.co.myexample.jamescoggan.api.UserReloadEvent;
import uk.co.myexample.jamescoggan.data.UserData;
import uk.co.myexample.jamescoggan.formula.Formula;
import uk.co.myexample.jamescoggan.formula.User;
import uk.co.senab.photoview.PhotoViewAttacher;

/*
 * File: app/src/main/java/uk.co.myexample.jamescoggan/fragments/UserFragment.java
 * <p/>
 * Description: Fragment containing the selected user information, the content is inside a scrolllview
 *
 * @author James Coggan
 * @version 1.0
 * @since 2015-05-10
 */
public class UserFragment extends Fragment {
    View view;
    Formula formula;
    Context context;
    Integer currentId;
    PhotoViewAttacher mAttacher;

    @InjectView(R.id.user_name)
    TextView user_name;

    @InjectView(R.id.user_image)
    ImageView user_image;

    @InjectView(R.id.user_message)
    TextView user_message;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_user, container, false);
        ButterKnife.inject(this, view);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        formula = new Formula(getActivity().getApplicationContext());
        context = getView().getContext();
        if (savedInstanceState != null) {   // If saved tab, load
            currentId = savedInstanceState.getInt("user_state");
            renderUser(currentId);
        }
    }

    /*
     * Render the current/selected user. Removes the no user selected message.
     * Load all the images async with Picasso
     *
     * @param userId - the id of the selected user
     */
    private void renderUser(Integer userId) {
        user_message.setVisibility(View.GONE);
        UserData userData = User.getUser(formula, userId);
        if (userData != null) {

            String displayText = userData.getUserName() + ": " + userData.getTitle();
            user_name.setText(displayText);

            if (ApiConst.DEBUG_MODE) {
                Picasso.with(context)
                        .setIndicatorsEnabled(true);
            }
            Picasso.with(context)
                    .load(ApiConst.BASE_URL + "/photos/" + userData.getImageID())
                    .placeholder(R.drawable.progress_animation)
                    .error(R.drawable.warning)
                    .into(user_image, new com.squareup.picasso.Callback() {
                        @Override
                        public void onSuccess() {
                            // Animate image after load
                            Animations.rotateImage(user_image);
                        }

                        @Override
                        public void onError() {

                        }
                    });

            mAttacher = new PhotoViewAttacher(user_image);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("user_state", String.valueOf(currentId)); // Save user id for reload
    }

    @Subscribe
    public void onUserLoad(UserReloadEvent event) {
        currentId = event.getId();
        renderUser(currentId);
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
