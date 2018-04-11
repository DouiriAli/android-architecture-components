package androidarchitecturecomponent.example.data.repository;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.util.Log;

import java.util.List;

import androidarchitecturecomponent.example.data.remote.RemoteDataSource;
import androidarchitecturecomponent.example.data.remote.UserResponse;
import androidarchitecturecomponent.example.data.local.LocalDataSource;
import androidarchitecturecomponent.example.data.local.UserEntity;
import androidarchitecturecomponent.example.utils.Transformation;
import retrofit2.Response;
import rx.Completable;
import rx.CompletableSubscriber;
import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.schedulers.Schedulers;

/**
 * Created by DOUIRI Ali on 12/03/2018.
 * my.alidouiri@gmail.com
 */

public class UserRepository implements Repository {

    private static final String TAG = UserRepository.class.getSimpleName();

    private final LocalDataSource mRoomDataSource;
    private final RemoteDataSource mRemoteDataSource;

    private MediatorLiveData<List<UserEntity>> mObservableUsers = new MediatorLiveData<>();

    public UserRepository(LocalDataSource roomDataSource, RemoteDataSource remoteDataSource) {

        this.mRoomDataSource = roomDataSource;
        this.mRemoteDataSource = remoteDataSource;

        mObservableUsers = new MediatorLiveData<>();

        mObservableUsers.addSource(mRoomDataSource.getUserDao().getAllUsers(),
                users -> mObservableUsers.postValue(users));
    }

    /**
     * Save user into database
     *
     * @param users
     */
    @Override
    public void saveUsers(List<UserEntity> users) {
        mRoomDataSource.getUserDao().saveUsers(users);
    }

    /**
     * Get users into database
     *
     * @return
     */
    @Override
    public LiveData<List<UserEntity>> getUsersFromDb() {
        return mObservableUsers;
    }

    /**
     * Get users from api
     *
     */
    @Override
    public void getUsersFromApi() {

        mRemoteDataSource.getUsersFromApi()
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

                                    deleteUsers();
                                    saveUsers(Transformation.toUserEntities(response.body()));
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

    /**
     * delete users
     *
     */
    @Override
    public void deleteUsers() {
        mRoomDataSource.getUserDao().deleteUsers();
    }
}

