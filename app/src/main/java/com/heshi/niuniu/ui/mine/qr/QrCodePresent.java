package com.heshi.niuniu.ui.mine.qr;

import android.app.Activity;

import com.heshi.niuniu.present.BasePresenter;

import javax.inject.Inject;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

/**
 * Created by Administrator on 2018/7/16 0016.
 */

public class QrCodePresent extends BasePresenter<QrCodeContract.Model>
        implements QrCodeContract.Presenter{

    @Inject
    public QrCodePresent(Activity activity, OkHttpClient okHttpClient, Retrofit retrofit) {
        super(activity, okHttpClient, retrofit);
    }


}
