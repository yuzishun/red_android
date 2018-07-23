package com.heshi.niuniu.ui.login;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.heshi.niuniu.R;
import com.heshi.niuniu.base.BaseActivity;
import com.heshi.niuniu.di.component.AppComponent;
import com.heshi.niuniu.di.component.DaggerActivityComponent;
import com.heshi.niuniu.di.module.ActivityModule;
import com.heshi.niuniu.ui.password.forget.ForgetPassActivity;
import com.heshi.niuniu.ui.password.register.RegisterActivity;
import com.heshi.niuniu.util.SnackbarUtil;
import com.heshi.niuniu.util.ToashUtils;
import com.heshi.niuniu.util.UIHelper;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/6/28 0028.
 */

public class LoginActivity extends BaseActivity<LoginPresent> {

    @BindView(R.id.text_username)
    EditText textUsername;
    @BindView(R.id.text_password)
    EditText textPassword;
    @BindView(R.id.btn_commit)
    Button btnCommit;
    @BindView(R.id.container)
    View container;
    public static final String AUTO_LOGIN_STATE_ACTION = "com.openim.autoLoginStateActionn";


    @Override
    protected void setupActivityComponent(AppComponent appComponent, ActivityModule activityModule) {
        DaggerActivityComponent
                .builder()
                .activityModule(activityModule)
                .appComponent(appComponent)
                .build()
                .inject(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }


    @Override
    protected void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);


    }

    @OnClick(R.id.btn_commit)
    public void onViewClicked() {
        String name = textUsername.getText().toString().trim();
        String pass = textPassword.getText().toString().trim();

        if (TextUtils.isEmpty(name)) {
            SnackbarUtil.ShortSnackbar(container, "账号不能为空！", 5).show();
        } else if (TextUtils.isEmpty(pass)) {
            SnackbarUtil.ShortSnackbar(container, "密码不能为空！", 5).show();
        } else {
            mPresenter.loginAction(name, pass);
        }

    }

    @OnClick({R.id.text_register_pass, R.id.text_forget_pass})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.text_register_pass:
                UIHelper.startActivity(mContext, RegisterActivity.class);

                break;
            case R.id.text_forget_pass:
                UIHelper.startActivity(mContext, ForgetPassActivity.class);

                break;
        }
    }

}
