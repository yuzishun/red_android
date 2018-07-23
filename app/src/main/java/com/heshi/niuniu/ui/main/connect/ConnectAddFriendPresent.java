package com.heshi.niuniu.ui.main.connect;

import android.app.Activity;

import com.heshi.niuniu.present.BasePresenter;

import javax.inject.Inject;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

/**
 * Created by Administrator on 2018/7/13 0013.
 */

public class ConnectAddFriendPresent extends BasePresenter<ConnectAddFriendContract.Model>
        implements ConnectAddFriendContract.Presenter {

    @Inject
    public ConnectAddFriendPresent(Activity activity, OkHttpClient okHttpClient, Retrofit retrofit) {
        super(activity, okHttpClient, retrofit);
    }


}
