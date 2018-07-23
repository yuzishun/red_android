package com.heshi.niuniu.fragment.main.weibo;

import android.app.Activity;

import com.heshi.niuniu.present.BasePresenter;

import javax.inject.Inject;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

/**
 * Created by Administrator on 2018/6/29 0029.
 */

public class WeiBoPresent extends BasePresenter<WeiBoContract.Model>
        implements WeiBoContract.Presenter{

    @Inject
    public WeiBoPresent(Activity activity, OkHttpClient okHttpClient, Retrofit retrofit) {
        super(activity, okHttpClient, retrofit);
    }


}
