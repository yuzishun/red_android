package com.heshi.niuniu.ui.password.register;

import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.heshi.niuniu.R;
import com.heshi.niuniu.base.BaseActivity;
import com.heshi.niuniu.di.component.AppComponent;
import com.heshi.niuniu.di.component.DaggerActivityComponent;
import com.heshi.niuniu.di.module.ActivityModule;
import com.heshi.niuniu.ui.login.LoginActivity;
import com.heshi.niuniu.util.SnackbarUtil;
import com.heshi.niuniu.util.ToashUtils;
import com.heshi.niuniu.util.UIHelper;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/6/29 0029.
 */

public class RegisterActivity extends BaseActivity<RegisterPresent>
        implements RegisterContract.Model {

    @BindView(R.id.text_register_name)
    EditText textRegisterName;
    @BindView(R.id.text_register_num)
    EditText textRegisterNum;
    @BindView(R.id.text_register_send_code)
    Button textRegisterSendCode;
    @BindView(R.id.text_register_commit)
    Button textRegisterCommit;
    @BindView(R.id.text_register_treaty)
    TextView textRegisterTreaty;
    @BindView(R.id.text_password)
    EditText textPassword;
    @BindView(R.id.container)
    CoordinatorLayout container;

    @Override
    protected void setupActivityComponent(AppComponent appComponent, ActivityModule activityModule) {
        DaggerActivityComponent.builder()
                .appComponent(appComponent)
                .activityModule(activityModule)
                .build()
                .inject(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_register_pass;

    }

    @OnClick({R.id.text_register_send_code, R.id.text_register_commit})
    public void onViewClicked(View view) {
        String userName = textRegisterName.getText().toString().trim();

        switch (view.getId()) {
            case R.id.text_register_send_code:
                if (TextUtils.isEmpty(userName)) {
                    SnackbarUtil.ShortSnackbar(container, "手机号不能为空！", 5).show();
                } else {
                    mPresenter.getCode(userName, textRegisterSendCode);
                }

                break;
            case R.id.text_register_commit:
                String code = textRegisterNum.getText().toString().trim();
                String pass = textPassword.getText().toString().trim();

                if (TextUtils.isEmpty(userName)) {
                    SnackbarUtil.ShortSnackbar(container, "手机号不能为空！", 5).show();

                } else if (TextUtils.isEmpty(code)) {
                    SnackbarUtil.ShortSnackbar(container, "验证码不能为空！", 5).show();

                } else if (TextUtils.isEmpty(pass)) {
                    SnackbarUtil.ShortSnackbar(container, "密码不能为空！", 5).show();

                } else {
                    mPresenter.registerCou(userName, pass, code);
                }

                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.stopTimer();

    }

    @Override
    public void onSuccess() {
        SnackbarUtil.ShortSnackbar(container, "注册成功！", 5).show();
        UIHelper.startActivity(mContext, LoginActivity.class);
        appManager.finishActivity(RegisterActivity.class);
    }
}
