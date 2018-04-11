package androidarchitecturecomponent.example.di;


import javax.inject.Singleton;

import dagger.Component;
import androidarchitecturecomponent.example.App;
import androidarchitecturecomponent.example.ui.user.UserViewModel;

/**
 * Created by DOUIRI Ali on 12/03/2018.
 * my.alidouiri@gmail.com
 */

@Component(modules = AppModule.class)
@Singleton
public interface AppComponent {

    void inject(UserViewModel userViewModel);

    void inject(App dialApp);

}
