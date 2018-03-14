package dialtechnologies.architecture.component.dial.dialcomponentachitecture.ui.user;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;

import java.util.List;

import javax.inject.Inject;

import dialtechnologies.architecture.component.dial.dialcomponentachitecture.DialApp;
import dialtechnologies.architecture.component.dial.dialcomponentachitecture.data.repository.UserRepository;
import dialtechnologies.architecture.component.dial.dialcomponentachitecture.data.room.UserEntity;

/**
 * Created by DOUIRI Ali on 12/03/2018.
 * Dial Technologies
 * a.douiri@dialy.net
 * my.alidouiri@gmail.com
 */

public class UserViewModel extends AndroidViewModel {

    private static final String TAG = UserViewModel.class.getSimpleName();

    private MediatorLiveData<List<UserEntity>> mObservableUsers;

    private LiveData<List<UserEntity>> mUsers;

    @Inject
    UserRepository mUserRepository;

    public UserViewModel(Application application) {

        super(application);

        ((DialApp) application).getAppComponent().inject(this);

        mObservableUsers = new MediatorLiveData<>();

        mObservableUsers.setValue(null);

        mUsers = mUserRepository.getUsersDb();

        mObservableUsers.addSource(mUsers,
                users -> mObservableUsers.setValue(users));
    }

    public LiveData<List<UserEntity>> getAllUsers() {
        return mObservableUsers;
    }

}
