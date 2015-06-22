package uk.co.myexample.jamescoggan.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.ButterKnife;
import butterknife.InjectView;
import uk.co.myexample.jamescoggan.R;
import uk.co.myexample.jamescoggan.api.Animations;
import uk.co.myexample.jamescoggan.api.ApiConst;
import uk.co.myexample.jamescoggan.data.DatabaseStatisticsParams;
import uk.co.myexample.jamescoggan.data.DatabaseStatisticsSave;
import uk.co.myexample.jamescoggan.data.UserData;
import uk.co.myexample.jamescoggan.formula.Formula;
import uk.co.myexample.jamescoggan.formula.User;

/**
 * File: app/src/main/java/uk.co.myexample.jamescoggan/adapter/ListUsersAdapter.java
 * <p/>
 * Description: List users with image
 * <p/>
 *
 * @author James Coggan
 * @version 1.0
 * @since 2015-05-10
 */

public class ListUsersAdapter extends BaseAdapter {
    private LayoutInflater inflater;
    private List<UserData> usersList;
    private Context context;
    private Formula formula;

    public ListUsersAdapter(Context context) {
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.context = context;
        formula = new Formula(context);
        doQuery();
    }

    public Integer getID(int position) {
        Integer result = null;
        if (usersList != null) {
            UserData user = usersList.get(position);
            result = user.getID();
        }
        return result;
    }

    @Override
    public int getCount() {
        int result = 0;

        if (usersList != null) {
            result = usersList.size();
        }

        return result;
    }

    @Override
    public Object getItem(int i) {      // Get Object, not used but required
        return null;
    }

    @Override
    public long getItemId(int i) {      // Get itemid, not used but required
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        final ViewHolder holder;
        final UserData user;
        final String image_url;
        if (view == null) {
            view = inflater.inflate(R.layout.list_users, viewGroup, false);
            holder = new ViewHolder(view);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        if (usersList != null) {
            user = usersList.get(i);
            image_url = ApiConst.BASE_URL + "/photos/" + user.getImageID();
            String displayText = user.getUserName() + ": " + user.getTitle();
            holder.user_name.setText(displayText);
            if (ApiConst.DEBUG_MODE) {
                Picasso.with(context)
                        .setIndicatorsEnabled(true);
            }

            int scaleWidth = (int) context.getResources().getDimension(R.dimen.small_image_height);
            int scaleHeight = (int) context.getResources().getDimension(R.dimen.small_image_width);

            Picasso.with(context)
                    .load(image_url)
                    .placeholder(R.drawable.progress_animation)
                    .error(R.drawable.warning)
                    .resize(scaleWidth, scaleHeight)
                    .centerCrop()
                    .into(holder.user_image, new com.squareup.picasso.Callback() {
                        @Override
                        public void onSuccess() {
                            // Animate image after load
                            Animations.bounceImage(holder.user_image);
                            // Insert into statistics
                            if (ApiConst.DEBUG_MODE) {
                                DatabaseStatisticsParams params = new DatabaseStatisticsParams(context, user, image_url);
                                DatabaseStatisticsSave databaseExecute = new DatabaseStatisticsSave();
                                databaseExecute.execute(params);
                            }
                        }

                        @Override
                        public void onError() {

                        }
                    });
        }
        return view;
    }

    private void insertStatistic() {

    }

    private void doQuery() {
        usersList = User.getUsers(formula);
    }

    static class ViewHolder {
        @InjectView(R.id.list_users_image)
        ImageView user_image;
        @InjectView(R.id.list_users_name)
        TextView user_name;

        public ViewHolder(View view) {
            ButterKnife.inject(this, view);
        }
    }
}