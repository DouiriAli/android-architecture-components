package dialtechnologies.architecture.component.dial.dialcomponentachitecture.data.room;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import java.util.List;

/**
 * Created by DOUIRI Ali on 12/03/2018.
 * Dial Technologies
 * a.douiri@dialy.net
 * my.alidouiri@gmail.com
 */

@Dao
public interface UserDao {

    @Query("SELECT * FROM users")
    LiveData<List<UserEntity>> getAllUsers();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<UserEntity> users);

    @Query("DELETE FROM users")
    void deleteAll();
}
