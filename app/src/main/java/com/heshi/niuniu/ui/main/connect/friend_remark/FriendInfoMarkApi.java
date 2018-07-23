package com.heshi.niuniu.ui.main.connect.friend_remark;

import com.heshi.niuniu.app.ApiUrl;

import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Administrator on 2018/7/13 0013.
 */

public interface FriendInfoMarkApi {

    @GET(ApiUrl.setInfoMark)
    Observable<Response<Object>> setInfoMark(@Query("user_id") String user_id
            , @Query("friend_id") String friend_id, @Query("friend_text") String friend_text);

}
