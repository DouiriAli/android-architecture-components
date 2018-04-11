package androidarchitecturecomponent.example.ui.user;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import java.util.List;

import javax.inject.Inject;

import androidarchitecturecomponent.example.App;
import androidarchitecturecomponent.example.data.repository.UserRepository;
import androidarchitecturecomponent.example.data.local.UserEntity;

/**
 * Created by DOUIRI Ali on 12/03/2018.
 * my.alidouiri@gmail.com
 */

public class UserViewModel extends AndroidViewModel {

    private static final String TAG = UserViewModel.class.getSimpleName();


    @Inject
    UserRepository mUserRepository;

    public UserViewModel(Application application) {

        super(application);

        ((App) application).getAppComponent().inject(this);

    }

    /**
     * Get users from database
     *
     * @return
     */
    public LiveData<List<UserEntity>> getUsers() {

        return mUserRepository.getUsersFromDb();
    }

}
