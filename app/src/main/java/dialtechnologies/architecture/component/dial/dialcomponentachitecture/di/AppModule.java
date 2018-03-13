package dialtechnologies.architecture.component.dial.dialcomponentachitecture.di;

import android.app.Application;
import android.arch.persistence.room.Room;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dialtechnologies.architecture.component.dial.dialcomponentachitecture.BuildConfig;
import dialtechnologies.architecture.component.dial.dialcomponentachitecture.data.remote.RemoteDataSource;
import dialtechnologies.architecture.component.dial.dialcomponentachitecture.data.remote.RemoteService;
import dialtechnologies.architecture.component.dial.dialcomponentachitecture.data.repository.UserRepository;
import dialtechnologies.architecture.component.dial.dialcomponentachitecture.data.room.RoomDataSource;
import dialtechnologies.architecture.component.dial.dialcomponentachitecture.data.room.UserDao;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by DOUIRI Ali on 12/03/2018.
 * Dial Technologies
 * a.douiri@dialy.net
 * my.alidouiri@gmail.com
 */

@Module
public class AppModule {

    private final Application application;

    public AppModule(Application application) {
        this.application = application;
    }

    @Singleton
    @Provides
    public RoomDataSource providesRoomDataSource() {
        return Room.databaseBuilder(application, RoomDataSource.class, "user_database")
                .build();
    }

    @Singleton
    @Provides
    public UserDao providesUserDao(RoomDataSource userRoomDatabase) {
        return userRoomDatabase.getUserDao();
    }

    @Provides
    public Context providesAppContext() {
        return application;
    }

    @Provides
    @Singleton
    public RemoteService providesRemoteService() {
        return new Retrofit.Builder()
                .baseUrl(BuildConfig.BASE_URL)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(RemoteService.class);
    }

    @Provides
    @Singleton
    public RemoteDataSource providesRemoteDataSource(RemoteService remoteService) {
        return new RemoteDataSource(remoteService);
    }

    @Provides
    @Singleton
    public UserRepository providesUserRepository(RemoteDataSource remoteDataSource, RoomDataSource roomDataSource) {

        return new UserRepository(roomDataSource, remoteDataSource);
    }
}
