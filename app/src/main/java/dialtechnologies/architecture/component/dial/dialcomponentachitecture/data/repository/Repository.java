package dialtechnologies.architecture.component.dial.dialcomponentachitecture.data.repository;

import android.arch.lifecycle.LiveData;

import java.util.List;

import dialtechnologies.architecture.component.dial.dialcomponentachitecture.data.remote.UserResponse;
import dialtechnologies.architecture.component.dial.dialcomponentachitecture.data.room.UserEntity;

/**
 * Created by DOUIRI Ali on 12/03/2018.
 * Dial Technologies
 * a.douiri@dialy.net
 * my.alidouiri@gmail.com
 */

public interface Repository {

    void saveUsersDb(List<UserEntity> users);

    LiveData<List<UserEntity>> getUsersDb();

    void getUsersOnline();

    void deleteAllUsers();

}
