package androidarchitecturecomponent.example.data.local;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import javax.inject.Singleton;

/**
 * Created by DOUIRI Ali on 12/03/2018.
 * my.alidouiri@gmail.com
 */

@Singleton
@Database(entities = UserEntity.class, version = 1)
public abstract class LocalDataSource extends RoomDatabase {

    public abstract UserDao getUserDao();

}
