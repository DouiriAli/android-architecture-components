package androidarchitecturecomponent.example.data.local;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

/**
 * Created by DOUIRI Ali on 12/03/2018.
 * my.alidouiri@gmail.com
 */

@Dao
public interface UserDao {

    @Query("SELECT * FROM users")
    LiveData<List<UserEntity>> getUsers();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void saveUsers(List<UserEntity> users);

    @Query("DELETE FROM users")
    void deleteUsers();
}
