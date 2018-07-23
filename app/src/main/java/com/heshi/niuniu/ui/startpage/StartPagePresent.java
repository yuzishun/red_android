package com.heshi.niuniu.ui.startpage;

import android.app.Activity;

import com.heshi.niuniu.present.BasePresenter;

import javax.inject.Inject;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

/**
 * Created by Administrator on 2018/6/28 0028.
 */

public class StartPagePresent extends BasePresenter<StartPageContract.Model> {

    @Inject
    public StartPagePresent(Activity activity, OkHttpClient okHttpClient, Retrofit retrofit) {
        super(activity, okHttpClient, retrofit);
    }

}
