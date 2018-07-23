package com.heshi.niuniu.ui.webview;

import android.app.Activity;

import com.heshi.niuniu.present.BasePresenter;

import javax.inject.Inject;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

/**
 * Created by Administrator on 2018/7/3 0003.
 */

public class XWebViewPresent extends BasePresenter<XWebViewContract.Model>
        implements XWebViewContract.Presenter {

    @Inject
    public XWebViewPresent(Activity activity, OkHttpClient okHttpClient, Retrofit retrofit) {
        super(activity, okHttpClient, retrofit);

    }
}
