package dialtechnologies.architecture.component.dial.dialcomponentachitecture.data.remote;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import dialtechnologies.architecture.component.dial.dialcomponentachitecture.data.room.UserEntity;

/**
 * Created by DOUIRI Ali on 12/03/2018.
 * Dial Technologies
 * a.douiri@dialy.net
 * my.alidouiri@gmail.com
 */

public class UserResponse {

    @SerializedName("login")
    String mName;

    @SerializedName("avatar_url")
    String mAvatar;

    public String getmAvatar() {
        return mAvatar;
    }

    public void setmAvatar(String mAvatar) {
        this.mAvatar = mAvatar;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public UserEntity toUserEntity(){

        final UserEntity userEntity = new UserEntity();
        userEntity.setName(mName);
        userEntity.setAvatar(mAvatar);
        return userEntity;
    }
}
