package com.heshi.niuniu.ui.password.commit_pass;

import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.heshi.niuniu.R;
import com.heshi.niuniu.base.BaseActivity;
import com.heshi.niuniu.di.component.AppComponent;
import com.heshi.niuniu.di.component.DaggerActivityComponent;
import com.heshi.niuniu.di.module.ActivityModule;
import com.heshi.niuniu.ui.login.LoginActivity;
import com.heshi.niuniu.ui.password.forget.ForgetPassActivity;
import com.heshi.niuniu.util.SnackbarUtil;
import com.heshi.niuniu.util.UIHelper;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/6/29 0029.
 */

public class CommitPassActivity extends BaseActivity<CommitPassPresent> implements
        CommitPassContract.Model {


    @BindView(R.id.text_commit_pass)
    EditText textCommitPass;
    @BindView(R.id.text_commit_pass_again)
    EditText textCommitPassAgain;
    @BindView(R.id.btn_commit_commit)
    Button btnCommitCommit;
    @BindView(R.id.commit_container)
    CoordinatorLayout commitContainer;
    private String token;

    @Override
    protected void setupActivityComponent(AppComponent appComponent, ActivityModule activityModule) {
        DaggerActivityComponent.builder()
                .activityModule(activityModule)
                .appComponent(appComponent)
                .build()
                .inject(this);

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_commit_pass;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        token = getIntent().getStringExtra("token");

    }

    @OnClick(R.id.btn_commit_commit)
    public void onViewClicked() {
        String oneceStr = textCommitPass.getText().toString().trim();
        String twoStr = textCommitPass.getText().toString().trim();

        if (TextUtils.isEmpty(oneceStr)) {
            SnackbarUtil.ShortSnackbar(commitContainer, "密码不能为空", 5).show();

        } else if (TextUtils.isEmpty(twoStr)) {
            SnackbarUtil.ShortSnackbar(commitContainer, "密码不能为空", 5).show();

        } else {
            mPresenter.verPass(oneceStr, twoStr, token, commitContainer);

        }
    }

    @Override
    public void onSuccess() {
        appManager.finishActivity(ForgetPassActivity.class);
        appManager.finishActivity(CommitPassActivity.class);
        SnackbarUtil.ShortSnackbar(commitContainer,
                " 修改成功！", 5).show();

        UIHelper.startActivity(mContext, LoginActivity.class);
    }
}
