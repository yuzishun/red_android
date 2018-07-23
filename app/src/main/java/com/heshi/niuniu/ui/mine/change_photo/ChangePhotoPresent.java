package com.heshi.niuniu.ui.mine.change_photo;

import android.app.Activity;

import com.heshi.niuniu.app.Constants;
import com.heshi.niuniu.eventbus.PhotoMsgEvent;
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
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Subscriber;

/**
 * Created by Administrator on 2018/7/9 0009.
 */

public class ChangePhotoPresent extends BasePresenter<ChangePhotoContract.Model>
        implements ChangePhotoContract.Presenter {

    private ChangePhotoApi api;
    private HttpDialog dialog;

    @Inject
    public ChangePhotoPresent(Activity activity, OkHttpClient okHttpClient, Retrofit retrofit) {
        super(activity, okHttpClient, retrofit);
//        api = retrofit.create(ChangePhotoApi.class);
        api = retrofit.create(ChangePhotoApi.class);
        dialog = new HttpDialog(mActivity);

    }


    @Override
    public void upDataUrl(List<MultipartBody.Part> part, final String path) {
        dialog.setMessage("处理中...");
        dialog.show();
        addSubscription(api.upDataUrl(part)
                        .compose(SchedulersCompat.applyIoSchedulers())
                        .compose(RxResultHelper.handleResult())
                , new Subscriber() {
                    @Override
                    public void onCompleted() {
                        dialog.dismiss();

                    }

                    @Override
                    public void onError(Throwable e) {
                        dialog.dismiss();

                    }

                    @Override
                    public void onNext(Object o) {
                        dialog.dismiss();
                        EventBus.getDefault().post(new PhotoMsgEvent(path));

                    }
                });

    }
}
