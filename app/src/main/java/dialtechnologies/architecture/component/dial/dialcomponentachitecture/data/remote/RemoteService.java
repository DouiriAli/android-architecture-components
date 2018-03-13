package dialtechnologies.architecture.component.dial.dialcomponentachitecture.data.remote;

import java.util.List;

import dialtechnologies.architecture.component.dial.dialcomponentachitecture.data.room.UserEntity;
import retrofit2.Response;
import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by DOUIRI Ali on 12/03/2018.
 * Dial Technologies
 * a.douiri@dialy.net
 * my.alidouiri@gmail.com
 */

public interface RemoteService {

    @GET("users")
    Observable<Response<List<UserResponse>>> getUsers();
}
