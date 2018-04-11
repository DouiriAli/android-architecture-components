package androidarchitecturecomponent.example.utils;

import java.util.ArrayList;
import java.util.List;

import androidarchitecturecomponent.example.data.remote.UserResponse;
import androidarchitecturecomponent.example.data.local.UserEntity;

/**
 * Created by DOUIRI Ali on 13/03/2018.
 * my.alidouiri@gmail.com
 */

public class Transformation {

    /**
     * Transform response of api to {@link UserEntity}
     *
     * @param responseList
     * @return
     */
    public static List<UserEntity> toUserEntities(List<UserResponse> responseList){

        List<UserEntity> userEntities = new ArrayList<>();

        for(UserResponse response : responseList){

            if(response != null)
                userEntities.add(response.toUserEntity());

        }

        return userEntities;

    }
}
