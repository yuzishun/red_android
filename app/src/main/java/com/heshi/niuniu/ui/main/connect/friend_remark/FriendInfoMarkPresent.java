package com.heshi.niuniu.ui.main.connect.friend_remark;

import android.app.Activity;

import com.heshi.niuniu.app.Constants;
import com.heshi.niuniu.present.BasePresenter;
import com.heshi.niuniu.rx.data.RxResultHelper;
import com.heshi.niuniu.rx.data.SchedulersCompat;
import com.heshi.niuniu.util.HttpDialog;

import javax.inject.Inject;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Subscriber;

/**
 * Created by Administrator on 2018/7/13 0013.
 */

public class FriendInfoMarkPresent extends BasePresenter<FriendInfoMarkContract.Model>
        implements FriendInfoMarkContract.Presenter {

    private FriendInfoMarkApi api;
    private HttpDialog dialog;

    @Inject
    public FriendInfoMarkPresent(Activity activity, OkHttpClient okHttpClient, Retrofit retrofit) {
        super(activity, okHttpClient, retrofit);
        api = retrofit.create(FriendInfoMarkApi.class);
        dialog = new HttpDialog(mActivity);
    }


    @Override
    public void setInfoMark(String user_id, String friend_id, String friend_text) {
        dialog.show();

        addSubscription(api.setInfoMark(user_id, friend_id, friend_text)
                        .compose(SchedulersCompat.applyIoSchedulers())
                        .compose(RxResultHelper.handleResult())
                , new Subscriber() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        mModel.onSuccess(-1);
                        dialog.dismiss();
                    }

                    @Override
                    public void onNext(Object o) {
                        dialog.dismiss();
                        mModel.onSuccess(1);

                    }
                });

    }
}
