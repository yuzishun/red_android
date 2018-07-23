package com.heshi.niuniu.ui.password.register;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.widget.TextView;
import android.widget.Toast;

import com.heshi.niuniu.present.BasePresenter;
import com.heshi.niuniu.rx.data.RxResultHelper;
import com.heshi.niuniu.rx.data.SchedulersCompat;
import com.heshi.niuniu.ui.login.LoginActivity;
import com.heshi.niuniu.util.HttpDialog;
import com.heshi.niuniu.util.ToashUtils;
import com.heshi.niuniu.util.UIHelper;

import java.util.Timer;
import java.util.TimerTask;

import javax.inject.Inject;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import rx.Subscriber;

/**
 * Created by Administrator on 2018/6/29 0029.
 */

public class RegisterPresent extends BasePresenter<RegisterContract.Model>
        implements RegisterContract.Presenter {

    private RegisterApi api;
    private HttpDialog dialog;
    private Timer mTimer;

    private TimerTask mTask;
    private long duration = 60000;//倒计时时长 设置默认10秒
    private long temp_duration;
    private String clickBeffor = "点击重新获取";//点击前
    private String clickAfter = "秒后重新开始";//点击后
    private TextView btn;

    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            btn.setText(temp_duration / 1000 + clickAfter);
            temp_duration -= 1000;
            if (temp_duration < 0) {//倒计时结束
                btn.setEnabled(true);
                btn.setText(clickBeffor);
                stopTimer();
            }

        }
    };

    @Inject
    public RegisterPresent(Activity activity, OkHttpClient okHttpClient, Retrofit retrofit) {
        super(activity, okHttpClient, retrofit);
        api = retrofit.create(RegisterApi.class);
        dialog = new HttpDialog(mActivity);

    }


    @Override
    public void getCode(String usrName, final TextView btn) {
        this.btn = btn;
        dialog.setMessage("发送中..");
        dialog.show();

        addSubscription(api.getCode(usrName)
                        .compose(SchedulersCompat.applyIoSchedulers())
                        .compose(RxResultHelper.handleResult()),
                new Subscriber() {
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
                        ToashUtils.show(mActivity, "验证码发送成功！");

                        startTimer(btn);
                    }
                });

    }

    @Override
    public void registerCou(String userName, String pass, String code) {
        dialog.setMessage("加载中...");
        dialog.show();

        addSubscription(api.register(userName, pass, code)
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

//                        ToashUtils.show(mActivity, "注册成功！");
//                        UIHelper.startActivity(mActivity, LoginActivity.class);
                    }
                });
    }

    //计时开始
    private void startTimer(TextView btn) {
        temp_duration = duration;
        btn.setEnabled(false);
        mTimer = new Timer();
        mTask = new TimerTask() {
            @Override
            public void run() {
                mHandler.sendEmptyMessage(0x01);
            }
        };
        mTimer.schedule(mTask, 0, 1000);//调度分配，延迟0秒，时间间隔为1秒
    }

    //计时结束
    public void stopTimer() {
        if (mTask != null) {
            mTask.cancel();
            mTask = null;
        }
        if (mTimer != null) {
            mTimer.cancel();
            mTimer = null;
        }
    }

}
