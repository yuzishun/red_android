package com.heshi.niuniu.ui.login;


import com.heshi.niuniu.app.ApiUrl;
import com.heshi.niuniu.model.ImModel;
import com.heshi.niuniu.model.LoginModel;
import com.heshi.niuniu.model.Response;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by Administrator on 2018/6/28 0028.
 */

public interface LoginApi {

    @Headers({"urlname:manage"})
    @FormUrlEncoded
    @POST(ApiUrl.Login)
    Observable<Response<LoginModel>> login(@Field("username") String username
            , @Field("password") String password);

    @FormUrlEncoded
    @POST(ApiUrl.getImPass)
    Observable<Response<ImModel>>getImPass(@Field("user_name") String user_name);


}
