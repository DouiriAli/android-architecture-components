package androidarchitecturecomponent.example.data.remote;

import java.util.List;

import retrofit2.Response;
import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by DOUIRI Ali on 12/03/2018.
 * my.alidouiri@gmail.com
 */

public interface RemoteService {

    @GET("users")
    Observable<Response<List<UserResponse>>> getUsersFromApi();
}
