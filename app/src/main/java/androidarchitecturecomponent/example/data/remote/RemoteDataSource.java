package androidarchitecturecomponent.example.data.remote;


import java.util.List;

import retrofit2.Response;
import rx.Observable;

/**
 * Created by DOUIRI Ali on 12/03/2018.
 * my.alidouiri@gmail.com
 */

public class RemoteDataSource {

    private RemoteService mRemoteUserService;

    public RemoteDataSource(RemoteService remoteUserService) {
        this.mRemoteUserService = remoteUserService;
    }

    /**
     * Get users from api
     *
     * @return
     */
    public Observable<Response<List<UserResponse>>> getUsersFromApi(){

        return mRemoteUserService.getUsersFromApi();
    }
}
