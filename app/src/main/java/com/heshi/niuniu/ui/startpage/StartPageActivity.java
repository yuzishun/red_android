package com.heshi.niuniu.ui.startpage;

import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;

import com.heshi.niuniu.R;
import com.heshi.niuniu.app.Constants;
import com.heshi.niuniu.base.BaseActivity;
import com.heshi.niuniu.di.component.AppComponent;
import com.heshi.niuniu.di.component.DaggerActivityComponent;
import com.heshi.niuniu.di.module.ActivityModule;
import com.heshi.niuniu.im.sample.LoginSampleHelper;
import com.heshi.niuniu.ui.login.LoginActivity;
import com.heshi.niuniu.ui.main.MainActivity;
import com.heshi.niuniu.util.UIHelper;

/**
 * Created by Administrator on 2018/6/28 0028.
 */

public class StartPageActivity extends BaseActivity {

    @Override
    protected void setupActivityComponent(AppComponent appComponent, ActivityModule activityModule) {


    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_start_pager;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        IsFIrstLogin();
    }

    private void IsFIrstLogin() {
        Constants.readInfo();

//        LoginSampleHelper.getInstance().initIMKit(Constants.im_usrName, Constants.appkey);

        new Handler().postDelayed(new Runnable() {
            public void run() {

                /**
                 * 判断是否在线，在线则跳转
                 */
                if (TextUtils.isEmpty(Constants.access_token)) {
                    UIHelper.startActivity(StartPageActivity.this, LoginActivity.class);
                    appManager.finishActivity();
                } else {
                    UIHelper.startActivity(StartPageActivity.this, MainActivity.class);
                    appManager.finishActivity();
//                    appManager.finishActivity();
                }
                finish();
            }

        }, 1500);
    }

}
