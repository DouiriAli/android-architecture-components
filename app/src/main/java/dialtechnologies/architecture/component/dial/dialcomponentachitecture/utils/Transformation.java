package dialtechnologies.architecture.component.dial.dialcomponentachitecture.utils;

import java.util.ArrayList;
import java.util.List;

import dialtechnologies.architecture.component.dial.dialcomponentachitecture.data.remote.UserResponse;
import dialtechnologies.architecture.component.dial.dialcomponentachitecture.data.room.UserEntity;

/**
 * Created by DOUIRI Ali on 13/03/2018.
 * Dial Technologies
 * a.douiri@dialy.net
 * my.alidouiri@gmail.com
 */

public class Transformation {

    public static List<UserEntity> toUserEntities(List<UserResponse> responseList){

        List<UserEntity> userEntities = new ArrayList<>();

        for(UserResponse response : responseList){

            if(response != null)
                userEntities.add(response.toUserEntity());

        }

        return userEntities;

    }
}
