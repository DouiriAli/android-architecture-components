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
import rx.Completable;
import rx.CompletableSubscriber;
import rx.Observer;
import rx.Scheduler;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
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

        this.mRoomDataSource = roomDataSource;
        this.mRemoteDataSource = remoteDataSource;

        mObservableUsers = new MediatorLiveData<>();

        mObservableUsers.addSource(mRoomDataSource.getUserDao().getAllUsers(),
                users -> mObservableUsers.postValue(users));
    }

    @Override
    public void saveUsersDb(List<UserEntity> users) {
        mRoomDataSource.getUserDao().insertAll(users);
    }

    @Override
    public LiveData<List<UserEntity>> getUsersDb() {
        return mObservableUsers;
    }

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

                        if (response.isSuccessful()) {

                            Completable.fromAction(new Action0() {
                                @Override
                                public void call() {

                                    deleteAllUsers();
                                    saveUsersDb(Transformation.toUserEntities(response.body()));
                                }
                            }).subscribeOn(Schedulers.io())
                                    .observeOn(AndroidSchedulers.mainThread())
                                    .subscribe(new CompletableSubscriber() {
                                        @Override
                                        public void onCompleted() {

                                        }

                                        @Override
                                        public void onError(Throwable e) {

                                            Log.e(TAG, e.getMessage());

                                        }

                                        @Override
                                        public void onSubscribe(Subscription d) {

                                        }
                                    });
                        }

                    }
                });

    }

    @Override
    public void deleteAllUsers() {
        mRoomDataSource.getUserDao().deleteAll();
    }
}

