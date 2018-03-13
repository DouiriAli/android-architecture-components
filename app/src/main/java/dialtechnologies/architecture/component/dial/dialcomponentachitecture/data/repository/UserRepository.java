package dialtechnologies.architecture.component.dial.dialcomponentachitecture.data.repository;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.util.Log;

import java.util.List;

import javax.inject.Inject;

import dialtechnologies.architecture.component.dial.dialcomponentachitecture.data.remote.RemoteDataSource;
import dialtechnologies.architecture.component.dial.dialcomponentachitecture.data.remote.UserResponse;
import dialtechnologies.architecture.component.dial.dialcomponentachitecture.data.room.RoomDataSource;
import dialtechnologies.architecture.component.dial.dialcomponentachitecture.data.room.UserEntity;
import dialtechnologies.architecture.component.dial.dialcomponentachitecture.utils.Transformation;
import retrofit2.Response;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by DOUIRI Ali on 12/03/2018.
 * Dial Technologies
 * a.douiri@dialy.net
 * my.alidouiri@gmail.com
 */

public class UserRepository implements Repository {

    private static final String TAG = UserRepository.class.getSimpleName();

    private final RoomDataSource mRoomDataSource;
    private final RemoteDataSource mRemoteDataSource;

    private MediatorLiveData<List<UserEntity>> mObservableUsers = new MediatorLiveData<>();

    public UserRepository(RoomDataSource roomDataSource, RemoteDataSource remoteDataSource) {

        this.mRoomDataSource   = roomDataSource;
        this.mRemoteDataSource = remoteDataSource;

        mObservableUsers = new MediatorLiveData<>();

        mObservableUsers.addSource(mRoomDataSource.getUserDao().getAllUsers(),
                users -> mObservableUsers.postValue(users));
    }

    @Override
    public void saveUsersDb(List<UserEntity> users) {

        new Thread(new Runnable() {
            @Override
            public void run() {
                mRoomDataSource.getUserDao().insertAll(users);
            }
        }).start();
    }

    @Override
    public LiveData<List<UserEntity>> getUsersDb() {return mObservableUsers;}

    @Override
    public void getUsersOnline() {

        mRemoteDataSource.getUsers()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<Response<List<UserResponse>>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                        Log.e(TAG, e.getMessage());

                    }

                    @Override
                    public void onNext(Response<List<UserResponse>> response) {

                        if (response.isSuccessful()) saveUsersDb(Transformation.toUserEntities(response.body()));

                    }
                });

    }

    @Override
    public void deleteAllUsers() {

        new Thread(new Runnable() {
            @Override
            public void run() {
                mRoomDataSource.getUserDao().deleteAll();
            }
        }).start();

    }
}

