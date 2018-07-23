package com.heshi.niuniu.ui.main.connect.friend_info;

import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.heshi.niuniu.R;
import com.heshi.niuniu.app.Constants;
import com.heshi.niuniu.base.BaseActivity;
import com.heshi.niuniu.custom.RoundedImageView;
import com.heshi.niuniu.di.component.AppComponent;
import com.heshi.niuniu.di.component.DaggerActivityComponent;
import com.heshi.niuniu.di.module.ActivityModule;
import com.heshi.niuniu.model.BaseInfoModel;
import com.heshi.niuniu.ui.main.connect.friend_remark.FriendInfoMarkActivity;
import com.heshi.niuniu.util.GlideUtils;
import com.heshi.niuniu.util.UIHelper;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/7/13 0013.
 */

public class FriendInfoActivity extends BaseActivity<FriendInfoPresent>
        implements FriendInfoContract.Model {


    @BindView(R.id.img_friend_info)
    RoundedImageView imgFriendInfo;
    @BindView(R.id.text_info)
    TextView textInfo;
    @BindView(R.id.text_info_add_friend)
    TextView textInfoAddFriend;
    @BindView(R.id.text_info_name)
    TextView textInfoName;
    @BindView(R.id.view_back)
    RelativeLayout viewBack;
    @BindView(R.id.text_title)
    TextView textTitle;
    private String name;
    private String id;

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
        return R.layout.activity_friend_info;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        textTitle.setText("详细资料");
        viewBack.setVisibility(View.VISIBLE);

        name = getIntent().getStringExtra("name");
        mPresenter.getBaseInfo(name);
    }

    @Override
    public void setBaseInfo(BaseInfoModel model) {
        this.id = String.valueOf(model.getUser_id());
        textInfoName.setText(model.getNick_name());

        String url = Constants.Im_Url + "red/use/gethard_pic.do?user_name=" + name;
        GlideUtils.noCacheloadImg(url, imgFriendInfo);
    }

    @Override
    public void addSuccess() {
        StartNext();

    }

    @OnClick({R.id.rel_info_remark, R.id.text_info_add_friend})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rel_info_remark:
                StartNext();

                break;
            case R.id.text_info_add_friend:
                mPresenter.addFriendAction(name, id);
                break;
        }
    }

    private void StartNext() {
        Bundle data = new Bundle();
        data.putString("friend_id", id);

        UIHelper.startActivity(mContext, FriendInfoMarkActivity.class, data);
    }

}
