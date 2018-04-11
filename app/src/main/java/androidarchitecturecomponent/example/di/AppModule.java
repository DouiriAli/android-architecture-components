package androidarchitecturecomponent.example.di;

import android.app.Application;
import android.arch.persistence.room.Room;
import android.content.Context;

import javax.inject.Singleton;

import androidarchitecturecomponent.example.BuildConfig;
import dagger.Module;
import dagger.Provides;
import androidarchitecturecomponent.example.data.remote.RemoteDataSource;
import androidarchitecturecomponent.example.data.remote.RemoteService;
import androidarchitecturecomponent.example.data.repository.UserRepository;
import androidarchitecturecomponent.example.data.local.LocalDataSource;
import androidarchitecturecomponent.example.data.local.UserDao;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by DOUIRI Ali on 12/03/2018.
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
    public LocalDataSource providesRoomDataSource() {
        return Room.databaseBuilder(application, LocalDataSource.class, "user_database")
                .build();
    }

    @Singleton
    @Provides
    public UserDao providesUserDao(LocalDataSource userRoomDatabase) {
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
    public UserRepository providesUserRepository(RemoteDataSource remoteDataSource, LocalDataSource roomDataSource) {

        return new UserRepository(roomDataSource, remoteDataSource);
    }
}
