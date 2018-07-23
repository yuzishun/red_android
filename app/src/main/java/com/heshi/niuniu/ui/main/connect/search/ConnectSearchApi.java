package com.heshi.niuniu.ui.main.connect.search;

import com.heshi.niuniu.app.ApiUrl;
import com.heshi.niuniu.model.ConnectFriendModel;
import com.heshi.niuniu.model.ContactModel;
import com.heshi.niuniu.model.Response;

import java.util.List;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Administrator on 2018/7/13 0013.
 */

public interface ConnectSearchApi {

    @FormUrlEncoded
    @POST(ApiUrl.getBaseInfo)
    Observable<Response<ConnectFriendModel>> searchFriend(@Field("name") String name);

    @GET(ApiUrl.findFriend)
    Observable<Response<List<ContactModel>>> findFriend(@Query("friend_id") String friend_id);

}
