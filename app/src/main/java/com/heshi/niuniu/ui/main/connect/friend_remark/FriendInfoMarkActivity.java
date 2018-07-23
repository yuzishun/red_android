package com.heshi.niuniu.ui.main.connect.friend_remark;

import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.heshi.niuniu.R;
import com.heshi.niuniu.app.Constants;
import com.heshi.niuniu.base.BaseActivity;
import com.heshi.niuniu.di.component.AppComponent;
import com.heshi.niuniu.di.component.DaggerActivityComponent;
import com.heshi.niuniu.di.module.ActivityModule;
import com.heshi.niuniu.util.SnackbarUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/7/13 0013.
 */

public class FriendInfoMarkActivity extends BaseActivity<FriendInfoMarkPresent>
        implements FriendInfoMarkContract.Model, TextWatcher {

    @BindView(R.id.text_add_left)
    TextView textAddLeft;
    @BindView(R.id.text_title)
    TextView textTitle;
    @BindView(R.id.text_add_right)
    TextView textAddRight;
    @BindView(R.id.edit_info_mark)
    EditText editInfoMark;
    @BindView(R.id.container)
    CoordinatorLayout container;
    private String friend_id;

    @Override
    protected void setupActivityComponent(AppComponent appComponent, ActivityModule activityModule) {
        DaggerActivityComponent
                .builder()
                .appComponent(appComponent)
                .activityModule(activityModule)
                .build()
                .inject(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_friend_info_mark;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        textTitle.setText("设置备注");
        textAddLeft.setVisibility(View.VISIBLE);
        textAddRight.setVisibility(View.VISIBLE);

        friend_id = getIntent().getStringExtra("friend_id");

        editInfoMark.addTextChangedListener(this);
        textAddRight.setEnabled(false);
    }


    @OnClick({R.id.text_add_left, R.id.text_add_right})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.text_add_left:
                appManager.finishActivity();
                break;
            case R.id.text_add_right:
                String data = editInfoMark.getText().toString().trim();
                mPresenter.setInfoMark(Constants.USER_ID, friend_id, data);

                break;
        }
    }

    @Override
    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

    }

    @Override
    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        if (charSequence.length() > 0) {
            textAddRight.setTextColor(getResources().getColor(R.color.colorWhite));
            textAddRight.setEnabled(true);

        } else {
            textAddRight.setTextColor(getResources().getColor(R.color.color_999999));
            textAddRight.setEnabled(false);

        }

    }

    @Override
    public void afterTextChanged(Editable editable) {

    }

    @Override
    public void onSuccess(int data) {
        String str = data == -1 ? "备注修改失败！" : "备注修改成功！";
        SnackbarUtil.ShortSnackbar(container, str, 5).show();

        if (data == 1) {
            appManager.finishActivity();
        }

    }
}
