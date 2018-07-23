package com.heshi.niuniu.present;

import android.app.Activity;


import com.heshi.niuniu.base.MyApplication;
import com.heshi.niuniu.util.HttpDialog;
import com.heshi.niuniu.util.NetUtil;
import com.heshi.niuniu.util.ToashUtils;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import rx.Observable;
import rx.Subscriber;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by min on 2017/3/1.
 */

public abstract class BasePresenter<T extends MModel> implements IPresenter<T> {
    protected T                     mModel;
    protected Activity              mActivity;
    protected CompositeSubscription mCompositeSubscription;
    protected OkHttpClient          okHttpClient;
    protected Retrofit              retrofit;
    HttpDialog myHttpDialog;

    public BasePresenter(Activity activity, OkHttpClient okHttpClient, Retrofit retrofit) {//,OkHttpClient okHttpClient,Retrofit retrofit
        this.mActivity = activity;
        this.okHttpClient = okHttpClient;
        this.retrofit = retrofit;
        this.myHttpDialog = new HttpDialog(activity);

    }

    public OkHttpClient okHttpClient() {
        return okHttpClient;
    }

    public Retrofit retrofit() {
        return retrofit;
    }

    public BasePresenter() {
    }

    @Override
    public void attachView(T model) {
        this.mModel = model;
    }

    @Override
    public void detachView() {
        this.mModel = null;
        unSubscribe();
    }

    public void addSubscription(Observable observable, Subscriber subscriber) {//,HttpDialog myHttpDialog
        if (mCompositeSubscription == null) {
            mCompositeSubscription = new CompositeSubscription();
        }
        /**
         * 针对网络操作做一个特殊封装
         */
        if (NetUtil.isNetworkAvailable(MyApplication.application)) {
            mCompositeSubscription.add(observable.subscribe(subscriber));
        } else {
            ToashUtils.show(MyApplication.application, "您已经处于无网络的异次元");
            myHttpDialog.dismiss();
            subscriber.onError(new RuntimeException("您已经处于无网络的异次元"));
        }
    }

    protected void unSubscribe() {
        if (mCompositeSubscription != null) {
            mCompositeSubscription.unsubscribe();
        }
    }
}
