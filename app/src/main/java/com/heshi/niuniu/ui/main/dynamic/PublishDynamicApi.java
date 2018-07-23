package com.heshi.niuniu.ui.main.dynamic;

import com.heshi.niuniu.app.ApiUrl;
import com.heshi.niuniu.model.Response;

import java.util.List;

import okhttp3.MultipartBody;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import rx.Observable;

/**
 * Created by Administrator on 2018/7/19 0019.
 */

public interface PublishDynamicApi {

    @Multipart
    @POST(ApiUrl.publishDynamic)
    Observable<Response<Object>> publishDynamic(@Part List<MultipartBody.Part> part);

}
