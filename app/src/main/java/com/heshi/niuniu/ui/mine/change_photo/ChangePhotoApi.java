package com.heshi.niuniu.ui.mine.change_photo;

import com.heshi.niuniu.app.ApiUrl;
import com.heshi.niuniu.model.Response;

import java.util.List;

import okhttp3.MultipartBody;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import rx.Observable;

/**
 * Created by Administrator on 2018/7/9 0009.
 */

public interface ChangePhotoApi {

    @Multipart
    @POST(ApiUrl.updataImg)
    Observable<Response<Object>> upDataUrl(@Part List<MultipartBody.Part> part);

}
