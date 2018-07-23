package com.heshi.niuniu.ui.main.dynamic;

import android.app.Activity;

import com.heshi.niuniu.eventbus.DynamicEvent;
import com.heshi.niuniu.present.BasePresenter;
import com.heshi.niuniu.rx.data.RxResultHelper;
import com.heshi.niuniu.rx.data.SchedulersCompat;
import com.heshi.niuniu.util.HttpDialog;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import javax.inject.Inject;

import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import rx.Subscriber;

/**
 * Created by Administrator on 2018/7/19 0019.
 */

public class PublishDynamicPresent extends BasePresenter<PublishDynamicContract.Model>
        implements PublishDynamicContract.Presenter {

    private PublishDynamicApi api;
    private HttpDialog dialog;

    @Inject
    public PublishDynamicPresent(Activity activity, OkHttpClient okHttpClient, Retrofit retrofit) {
        super(activity, okHttpClient, retrofit);
        api = retrofit.create(PublishDynamicApi.class);
        dialog = new HttpDialog(mActivity);

    }


    @Override
    public void publishDynamic(List<MultipartBody.Part> part) {
        dialog.show();

        addSubscription(api.publishDynamic(part)
                        .compose(SchedulersCompat.applyIoSchedulers())
                        .compose(RxResultHelper.handleResult())
                , new Subscriber() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        dialog.dismiss();

                    }

                    @Override
                    public void onNext(Object o) {
                        EventBus.getDefault().post(new DynamicEvent());
                        dialog.dismiss();
                        mActivity.finish();
                    }
                });

    }
}
