package com.heshi.niuniu.ui.password.forget;

import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.heshi.niuniu.R;
import com.heshi.niuniu.base.BaseActivity;
import com.heshi.niuniu.di.component.AppComponent;
import com.heshi.niuniu.di.component.DaggerActivityComponent;
import com.heshi.niuniu.di.module.ActivityModule;
import com.heshi.niuniu.util.SnackbarUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/6/29 0029.
 */

public class ForgetPassActivity extends BaseActivity<ForgetPassPresent>
        implements ForgetPassContract.Model {

    @BindView(R.id.text_forget_name)
    EditText textForgetName;
    @BindView(R.id.text_forget_num)
    EditText textForgetNum;
    @BindView(R.id.text_forget_send_code)
    Button textForgetSendCode;
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
        return R.layout.activity_forget_pass;
    }

    @OnClick({R.id.text_forget_send_code, R.id.text_forget_commit})
    public void onViewClicked(View view) {
        String name = textForgetName.getText().toString().trim();

        switch (view.getId()) {
            case R.id.text_forget_send_code:
                if (TextUtils.isEmpty(name)) {
                    SnackbarUtil.ShortSnackbar(container, "手机号不能为空！", 5).show();

                } else {
                    mPresenter.forgetPass(name, textForgetSendCode,container);
                }

                break;
            case R.id.text_forget_commit:
                String code = textForgetNum.getText().toString().trim();

                if (TextUtils.isEmpty(name)) {
                    SnackbarUtil.ShortSnackbar(container, "手机号不能为空！", 5).show();

                } else if (TextUtils.isEmpty(code)) {
                    SnackbarUtil.ShortSnackbar(container, "验证码不能为空！", 5).show();

                } else {
                    mPresenter.commitVerCode(name, code);
                }

                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.stopTimer();
    }

}
