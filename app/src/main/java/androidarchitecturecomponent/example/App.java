package androidarchitecturecomponent.example;

import android.app.Application;

import javax.inject.Inject;

import androidarchitecturecomponent.example.data.repository.UserRepository;
import androidarchitecturecomponent.example.di.AppComponent;
import androidarchitecturecomponent.example.di.AppModule;
import androidarchitecturecomponent.example.di.DaggerAppComponent;


/**
 * Created by DOUIRI Ali on 12/03/2018.
 * my.alidouiri@gmail.com
 */

public class App extends Application {

    private AppComponent appComponent;

    @Inject
    UserRepository mUserRepository;

    @Override
    public void onCreate() {
        super.onCreate();

        initializeDagger();

        appComponent.inject(this);

        // Get users from api
        mUserRepository.getUsersFromApi();
    }

    /**
     * Initialize {@link AppComponent}
     *
     */
    private void initializeDagger(){

        this.appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();

    }

    public AppComponent getAppComponent() {
        return appComponent;
    }
}
