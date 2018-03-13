package dialtechnologies.architecture.component.dial.dialcomponentachitecture.data.remote;


import java.util.List;

import javax.inject.Inject;

import retrofit2.Response;
import rx.Observable;

/**
 * Created by DOUIRI Ali on 12/03/2018.
 * Dial Technologies
 * a.douiri@dialy.net
 * my.alidouiri@gmail.com
 */

public class RemoteDataSource {

    private RemoteService mRemoteUserService;

    public RemoteDataSource(RemoteService remoteUserService) {
        this.mRemoteUserService = remoteUserService;
    }

    public Observable<Response<List<UserResponse>>> getUsers(){

        return mRemoteUserService.getUsers();
    }
}
