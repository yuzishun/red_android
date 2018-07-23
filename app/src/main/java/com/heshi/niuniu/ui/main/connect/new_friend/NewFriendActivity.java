package com.heshi.niuniu.ui.main.connect.new_friend;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.heshi.niuniu.R;
import com.heshi.niuniu.app.Constants;
import com.heshi.niuniu.base.BaseActivity;
import com.heshi.niuniu.di.component.AppComponent;
import com.heshi.niuniu.di.component.DaggerActivityComponent;
import com.heshi.niuniu.di.module.ActivityModule;
import com.heshi.niuniu.ui.main.connect.search.ConnectSearchActivity;
import com.heshi.niuniu.util.UIHelper;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/7/17 0017.
 */

public class NewFriendActivity extends BaseActivity<NewFriendPresent>
        implements NewFriendContract.Model {


    @BindView(R.id.view_back)
    RelativeLayout viewBack;
    @BindView(R.id.text_title)
    TextView textTitle;
    @BindView(R.id.layout_friend_add)
    LinearLayout layoutFriendAdd;
    @BindView(R.id.rec_new_friend)
    RecyclerView recNewFriend;
    @BindView(R.id.text_add_friend_my_qr)
    TextView textAddFriendMyQr;

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
        return R.layout.activity_new_friend;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        textTitle.setText("新的朋友");
        viewBack.setVisibility(View.VISIBLE);
        mPresenter.initAdapter(recNewFriend);
        mPresenter.getFriendList(Constants.USER_ID, 0);
        mPresenter.getFriendList(Constants.USER_ID, 1);
        textAddFriendMyQr.setText(String.format("我的手机号：%s",Constants.userName));
    }

    @OnClick({R.id.rel_add_friend_search, R.id.layout_friend_add})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rel_add_friend_search:
                UIHelper.startActivity(mContext, ConnectSearchActivity.class);

                break;
            case R.id.layout_friend_add:

                break;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }
}
