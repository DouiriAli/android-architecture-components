package dialtechnologies.architecture.component.dial.dialcomponentachitecture.di;


import javax.inject.Singleton;

import dagger.Component;
import dialtechnologies.architecture.component.dial.dialcomponentachitecture.DialApp;
import dialtechnologies.architecture.component.dial.dialcomponentachitecture.ui.user.UserViewModel;

/**
 * Created by DOUIRI Ali on 12/03/2018.
 * Dial Technologies
 * a.douiri@dialy.net
 * my.alidouiri@gmail.com
 */

@Component(modules = AppModule.class)
@Singleton
public interface AppComponent {

    void inject(UserViewModel userViewModel);

    void inject(DialApp dialApp);

}
