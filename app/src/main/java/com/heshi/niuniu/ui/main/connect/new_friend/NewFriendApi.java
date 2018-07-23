package com.heshi.niuniu.ui.main.connect.new_friend;

import com.heshi.niuniu.app.ApiUrl;
import com.heshi.niuniu.model.NewFriendModel;
import com.heshi.niuniu.model.Response;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Administrator on 2018/7/17 0017.
 */

public interface NewFriendApi {

    @GET(ApiUrl.getNewFiend)
    Observable<Response<List<NewFriendModel>>> getNewFriend
            (@Query("user_id") String user_id);

    @GET(ApiUrl.getOldFiend)
    Observable<Response<List<NewFriendModel>>> getOldFiend
            (@Query("user_id") String user_id);

    @GET(ApiUrl.agreeOperation)
    Observable<Response<Object>> agreeOperation
            (@Query("user_id") String user_id,
             @Query("friend_id") String friend_id);

}
