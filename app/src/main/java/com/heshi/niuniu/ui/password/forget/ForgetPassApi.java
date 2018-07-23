package com.heshi.niuniu.ui.password.forget;

import com.heshi.niuniu.app.ApiUrl;
import com.heshi.niuniu.model.ForgetPassModel;
import com.heshi.niuniu.model.Response;


import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by Administrator on 2018/6/29 0029.
 */

public interface ForgetPassApi {

    @Headers({"urlname:manage"})
    @FormUrlEncoded
    @POST(ApiUrl.getVerCode)
    Observable<Response<Object>> getCode(@Field("Mobile") String Mobile);

    @Headers({"urlname:manage"})
    @FormUrlEncoded
    @POST(ApiUrl.commitVerCode)
    Observable<Response<ForgetPassModel>> commitVerCode(@Field("Mobile") String Mobile
            , @Field("VerifyCode") String commitVerCode);

}
