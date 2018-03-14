package dialtechnologies.architecture.component.dial.dialcomponentachitecture.ui.user;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import dialtechnologies.architecture.component.dial.dialcomponentachitecture.R;
import dialtechnologies.architecture.component.dial.dialcomponentachitecture.data.room.UserEntity;

/**
 * Created by DOUIRI Ali on 12/03/2018.
 * Dial Technologies
 * a.douiri@dialy.net
 * my.alidouiri@gmail.com
 */

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.UserHolder> {

    private static final String TAG = UserAdapter.class.getSimpleName();

    private List<UserEntity> mItems;
    private Context mContext;

    public UserAdapter(Context context, List<UserEntity> items) {

        this.mContext = context;
        this.mItems = items;
    }

    @Override
    public UserHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View mItemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_view, parent, false);

        return new UserHolder(mItemView);
    }

    @Override
    public void onBindViewHolder(UserHolder holder, int position) {

        UserEntity mUser = mItems.get(position);

        if(mUser != null) {

            holder.mName.setText(mUser.getName());

            Glide.with(mContext).load(mUser.getAvatar()).
                    asBitmap().into(holder.mThumbnail);

        }

    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    public void setItems(List<UserEntity> mItems){

        if(mItems != null && !mItems.isEmpty()){

            this.mItems = mItems;
            notifyDataSetChanged();
        }

    }

    static class UserHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.thumbnail)
        ImageView mThumbnail;
        @BindView(R.id.name)
        TextView mName;

        public UserHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
