package com.heshi.niuniu.fragment.main.mine;

import com.heshi.niuniu.app.ApiUrl;
import com.heshi.niuniu.model.BaseInfoModel;
import com.heshi.niuniu.model.Response;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Administrator on 2018/6/29 0029.
 */

public interface MineApi {

    @FormUrlEncoded
    @POST(ApiUrl.getBaseInfo)
    Observable<Response<BaseInfoModel>> getBaseInfo(@Field("name") String name);

    @GET(ApiUrl.getWeiBo)
    Observable<Response<String>> getWeiBo(@Query("user_name") String user_name);

    @GET(ApiUrl.getDynamic)
    Observable<Response<String>> getDynamic(@Query("user_name") String user_name);

    @GET(ApiUrl.getFan)
    Observable<Response<String>> getFan(@Query("user_id") String user_id);

    @GET(ApiUrl.getRedPacket)
    Observable<Response<String>> getRedPacket(@Query("user_id") String user_id);

}
