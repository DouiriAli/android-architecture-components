package dialtechnologies.architecture.component.dial.dialcomponentachitecture;

import android.app.Application;

import javax.inject.Inject;

import dialtechnologies.architecture.component.dial.dialcomponentachitecture.data.repository.UserRepository;
import dialtechnologies.architecture.component.dial.dialcomponentachitecture.di.AppComponent;
import dialtechnologies.architecture.component.dial.dialcomponentachitecture.di.AppModule;
import dialtechnologies.architecture.component.dial.dialcomponentachitecture.di.DaggerAppComponent;


/**
 * Created by DOUIRI Ali on 12/03/2018.
 * Dial Technologies
 * a.douiri@dialy.net
 * my.alidouiri@gmail.com
 */

public class DialApp extends Application {

    private AppComponent appComponent;

    @Inject
    UserRepository mUserRepository;

    @Override
    public void onCreate() {
        super.onCreate();

        this.appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();

        appComponent.inject(this);

        mUserRepository.deleteAllUsers();
        mUserRepository.getUsersOnline();
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }
}
