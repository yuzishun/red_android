package com.heshi.niuniu.fragment.main.dynamic;

import com.heshi.niuniu.app.ApiUrl;
import com.heshi.niuniu.model.DynamicInfoModel;
import com.heshi.niuniu.model.DynamicListModel;
import com.heshi.niuniu.model.Response;

import java.util.List;

import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Administrator on 2018/6/29 0029.
 */

public interface DynamicApi {

    @GET(ApiUrl.getDynamicList)
    Observable<Response<List<DynamicListModel>>> getDynamicList
            (@Query("user_name") String user_name, @Query("page") String page);

    @FormUrlEncoded
    @POST(ApiUrl.getDynamicInfo)
    Observable<Response<DynamicInfoModel>> getDynamicInfo
            (@Field("user_name") String user_name);

    @GET(ApiUrl.getZanList)
    Observable<Response<Object>> getZanList
            (@Query("user_name") String user_name, @Query("circle_id") String circle_id);


    @GET(ApiUrl.getOneStepComment)
    Observable<Response<Object>> getOneStepComment
            (@Query("user_name") String user_name, @Query("circle_id") String circle_id);


}
