package androidarchitecturecomponent.example.data.repository;

import android.arch.lifecycle.LiveData;

import java.util.List;

import androidarchitecturecomponent.example.data.local.UserEntity;

/**
 * Created by DOUIRI Ali on 12/03/2018.
 * my.alidouiri@gmail.com
 */

public interface Repository {

    void saveUsers(List<UserEntity> users);

    LiveData<List<UserEntity>> getUsersFromDb();

    void getUsersFromApi();

    void deleteUsers();

}
