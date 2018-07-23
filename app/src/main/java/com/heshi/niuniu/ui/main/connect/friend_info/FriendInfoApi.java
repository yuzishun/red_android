package com.heshi.niuniu.ui.main.connect.friend_info;

import com.heshi.niuniu.app.ApiUrl;
import com.heshi.niuniu.model.BaseInfoModel;
import com.heshi.niuniu.model.Response;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by Administrator on 2018/7/13 0013.
 */

public interface FriendInfoApi {

    @FormUrlEncoded
    @POST(ApiUrl.getBaseInfo)
    Observable<Response<BaseInfoModel>> getBaseInfo(@Field("name") String name);

    @FormUrlEncoded
    @POST(ApiUrl.addFriend)
    Observable<Response<Object>> addFriend(@Field("name")
                                                   String name, @Field("id") String id);

}
