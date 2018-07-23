package com.heshi.niuniu.ui.password.commit_pass;

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

public interface CommitPassApi {

    @Headers({"urlname:manage"})
    @FormUrlEncoded
    @POST(ApiUrl.verPass)
    Observable<Response<Object>> verPass(@Field("confirmpassword") String confirmpassword
            , @Field("newpassword") String newpassword, @Field("token") String token);


}
