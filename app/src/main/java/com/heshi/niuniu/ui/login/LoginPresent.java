package com.heshi.niuniu.ui.login;

import android.app.Activity;
import android.util.Log;

import com.alibaba.mobileim.channel.event.IWxCallback;
import com.heshi.niuniu.app.Constants;
import com.heshi.niuniu.base.MyApplication;
import com.heshi.niuniu.fragment.main.mine.MineApi;
import com.heshi.niuniu.im.sample.InitHelper;
import com.heshi.niuniu.im.sample.LoginSampleHelper;
import com.heshi.niuniu.model.ImModel;
import com.heshi.niuniu.model.LoginModel;
import com.heshi.niuniu.present.BasePresenter;
import com.heshi.niuniu.rx.data.RxResultHelper;
import com.heshi.niuniu.rx.data.SchedulersCompat;
import com.heshi.niuniu.ui.main.MainActivity;
import com.heshi.niuniu.util.HttpDialog;
import com.heshi.niuniu.util.SharedHelper;
import com.heshi.niuniu.util.UIHelper;

import org.eclipse.jdt.internal.compiler.batch.Main;

import javax.inject.Inject;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Subscriber;

/**
 * Created by Administrator on 2018/6/28 0028.
 */

public class LoginPresent extends BasePresenter<LoginContract.Model> implements LoginContract.Presenter {

    private final LoginApi api;
    private final HttpDialog dialog;
    private LoginSampleHelper loginHelper;

    @Inject
    public LoginPresent(Activity activity, OkHttpClient okHttpClient, Retrofit retrofit) {
        super(activity, okHttpClient, retrofit);
        api = retrofit.create(LoginApi.class);
        dialog = new HttpDialog(mActivity);

    }

    @Override
    public void loginAction(final String name, String password) {
        dialog.setMessage("登录中");
        dialog.show();

        addSubscription(api.login(name, password)
                        .compose(SchedulersCompat.applyIoSchedulers())
                        .compose(RxResultHelper.handleResult())
                , new Subscriber<LoginModel>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onError(Throwable e) {
                        dialog.dismiss();

                    }

                    @Override
                    public void onNext(LoginModel loginModel) {
                        Constants.saveInfo(loginModel, name);
                        getImPass(name);

                    }
                });

    }

    @Override
    public void getImPass(String name) {

        addSubscription(api.getImPass(name)
                        .compose(SchedulersCompat.applyIoSchedulers())
                        .compose(RxResultHelper.handleResult())
                , new Subscriber<ImModel>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        dialog.dismiss();
                    }

                    @Override
                    public void onNext(ImModel imModel) {
                        Log.e("main+ssdas", imModel.toString());
                        Constants.saveImInfo(imModel);
                        LoginSampleHelper.getInstance().initIMKit(Constants.im_usrName, Constants.appkey);

                        loginIm(imModel.getIm_user(), imModel.getIm_pwd(), imModel.getAppkey());
                    }
                });

    }

    @Override
    public void loginIm(String userId, String password, String appKey) {
        loginHelper = LoginSampleHelper.getInstance();

        loginHelper.login_Sample(userId, password, appKey, new IWxCallback() {
            @Override
            public void onSuccess(Object... objects) {
                Constants.readInfo();
                dialog.dismiss();
                Constants.isLoginIm = true;
                Log.e("聊天服务器", "聊天服务器连接成功");
                InitHelper.initYWSDK(MyApplication.application);

                UIHelper.startActivity(mActivity, MainActivity.class);
                mActivity.finish();
            }

            @Override
            public void onError(int i, String s) {
                Constants.isLoginIm = false;
                Log.e("聊天服务器", "聊天服务器连接失败");

            }

            @Override
            public void onProgress(int i) {
                Log.e("聊天服务器", "登录中");

            }
        });
    }

}
