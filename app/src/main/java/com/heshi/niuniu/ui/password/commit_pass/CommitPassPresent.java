package com.heshi.niuniu.ui.password.commit_pass;

import android.app.Activity;
import android.support.design.widget.CoordinatorLayout;

import com.heshi.niuniu.present.BasePresenter;
import com.heshi.niuniu.rx.data.RxResultHelper;
import com.heshi.niuniu.rx.data.SchedulersCompat;
import com.heshi.niuniu.ui.login.LoginActivity;
import com.heshi.niuniu.util.HttpDialog;
import com.heshi.niuniu.util.SnackbarUtil;
import com.heshi.niuniu.util.ToashUtils;
import com.heshi.niuniu.util.UIHelper;

import javax.inject.Inject;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import rx.Subscriber;

/**
 * Created by Administrator on 2018/6/29 0029.
 */

public class CommitPassPresent extends BasePresenter<CommitPassContract.Model>
        implements CommitPassContract.Presenter {

    private CommitPassApi api;
    private HttpDialog dialog;

    @Inject
    public CommitPassPresent(Activity activity, OkHttpClient okHttpClient, Retrofit retrofit) {
        super(activity, okHttpClient, retrofit);
        api = retrofit.create(CommitPassApi.class);
        dialog = new HttpDialog(mActivity);

    }


    @Override
    public void verPass(String confirmpassword, String newpassword, String token
            , final CoordinatorLayout coordinatorLayout) {
        dialog.show();

        addSubscription(api.verPass(confirmpassword, newpassword, token)
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
                        mModel.onSuccess();
                    }
                });

    }
}
