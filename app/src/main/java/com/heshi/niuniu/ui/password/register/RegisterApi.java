package com.heshi.niuniu.ui.password.register;

import com.heshi.niuniu.app.ApiUrl;
import com.heshi.niuniu.model.Response;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by Administrator on 2018/6/29 0029.
 */

public interface RegisterApi {

    @Headers({"urlname:manage"})
    @FormUrlEncoded
    @POST(ApiUrl.getCode)
    Observable<Response<Object>> getCode(@Field("mobile") String mobile);

    @Headers({"urlname:manage"})
    @FormUrlEncoded
    @POST(ApiUrl.Register)
    Observable<Response<Object>> register(@Field("mobile") String mobile
            , @Field("password") String password, @Field("verifycode") String verifycode);

}
