package com.heshi.niuniu.ui.mine.info;

import android.app.Activity;

import com.heshi.niuniu.present.BasePresenter;

import javax.inject.Inject;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

/**
 * Created by Administrator on 2018/7/9 0009.
 */

public class PersonInfoPresent extends BasePresenter<PersonInfoContract.Model>implements
        PersonInfoContract.Presenter {

    @Inject
    public PersonInfoPresent(Activity activity, OkHttpClient okHttpClient, Retrofit retrofit) {
        super(activity, okHttpClient, retrofit);



    }



}
