package com.heshi.niuniu.fragment.main.mine;

import android.app.Activity;
import android.util.Log;
import android.widget.TextView;

import com.heshi.niuniu.app.Constants;
import com.heshi.niuniu.model.BaseInfoModel;
import com.heshi.niuniu.present.BasePresenter;
import com.heshi.niuniu.rx.data.RxResultHelper;
import com.heshi.niuniu.rx.data.SchedulersCompat;
import com.heshi.niuniu.ui.main.MainApi;

import javax.inject.Inject;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Subscriber;

/**
 * Created by Administrator on 2018/6/29 0029.
 */

public class MinePresent extends BasePresenter<MineContract.Model>
        implements MineContract.Presenter {

    private MineApi api;

    @Inject
    public MinePresent(Activity activity, OkHttpClient okHttpClient, Retrofit retrofit) {
        super(activity, okHttpClient, retrofit);
        api = retrofit.create(MineApi.class);
    }


    @Override
    public void getBaseInfo(String name) {
        addSubscription(api.getBaseInfo(name)
                        .compose(SchedulersCompat.applyIoSchedulers())
                        .compose(RxResultHelper.handleResult())
                , new Subscriber<BaseInfoModel>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(BaseInfoModel baseInfoModel) {
                        Log.e("slfs", baseInfoModel + "");
                        mModel.setBaseInfo(baseInfoModel);

                    }
                });

    }

    @Override
    public void getWeiBoNum(String user_name, final TextView tv) {
        addSubscription(api.getWeiBo(user_name)
                        .compose(SchedulersCompat.applyIoSchedulers())
                        .compose(RxResultHelper.handleResult())
                , new Subscriber<String>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(String s) {
                        tv.setText(s + "");

                    }
                });

    }

    @Override
    public void getDynamic(String user_name, final TextView tv) {
        addSubscription(api.getDynamic(user_name)
                        .compose(SchedulersCompat.applyIoSchedulers())
                        .compose(RxResultHelper.handleResult())
                , new Subscriber<String>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(String s) {
                        tv.setText(s + "");

                    }
                });

    }

    @Override
    public void getFan(String user_id, final TextView tv) {
        addSubscription(api.getFan(user_id)
                        .compose(SchedulersCompat.applyIoSchedulers())
                        .compose(RxResultHelper.handleResult())
                , new Subscriber<String>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(String s) {
                        tv.setText(s + "");

                    }
                });

    }

    @Override
    public void getRedPacket(String user_id, final TextView tv) {
        addSubscription(api.getRedPacket(user_id)
                        .compose(SchedulersCompat.applyIoSchedulers())
                        .compose(RxResultHelper.handleResult())
                , new Subscriber<String>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(String s) {
                        tv.setText(s + "");

                    }
                });

    }


}
