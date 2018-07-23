package com.heshi.niuniu.ui.main.connect;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.heshi.niuniu.R;
import com.heshi.niuniu.app.Constants;
import com.heshi.niuniu.base.BaseActivity;
import com.heshi.niuniu.di.component.AppComponent;
import com.heshi.niuniu.di.component.DaggerActivityComponent;
import com.heshi.niuniu.di.module.ActivityModule;
import com.heshi.niuniu.ui.main.MainActivity;
import com.heshi.niuniu.ui.main.connect.search.ConnectSearchActivity;
import com.heshi.niuniu.ui.mine.qr.QrCodeActivity;
import com.heshi.niuniu.ui.mine.qr.scan.ScanningActivity;
import com.heshi.niuniu.util.UIHelper;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/7/13 0013.
 */

public class ConnectAddFriendActivity extends BaseActivity<ConnectAddFriendPresent>
        implements ConnectAddFriendContract.Model {

    @BindView(R.id.view_back)
    RelativeLayout viewBack;
    @BindView(R.id.text_title)
    TextView textTitle;
    @BindView(R.id.rel_add_friend_search)
    RelativeLayout relAddFriendSearch;
    @BindView(R.id.text_add_friend_my_qr)
    TextView textAddFriendMyQr;
    @BindView(R.id.img_add_friend_qr)
    ImageView imgAddFriendQr;
    @BindView(R.id.rel_add_friend_qr)
    RelativeLayout relAddFriendQr;
    @BindView(R.id.img_add_friend_contact)
    ImageView imgAddFriendContact;
    @BindView(R.id.rel_add_friend_contact)
    RelativeLayout relAddFriendContact;
    @BindView(R.id.img_add_friend_company)
    ImageView imgAddFriendCompany;
    @BindView(R.id.rel_add_friend_company)
    RelativeLayout relAddFriendCompany;
    private final int REQUEST_PERMISION_CODE_CAMARE = 0;


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
        return R.layout.activity_connect_add_friend;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        viewBack.setVisibility(View.VISIBLE);
        textTitle.setText("添加朋友");
        textAddFriendMyQr.setText(String.format("我的牛牛号是：%s", Constants.USER_NAME));

    }

    @OnClick({R.id.rel_add_friend_search, R.id.view_add_friend_my_qr, R.id.rel_add_friend_qr, R.id.rel_add_friend_contact, R.id.rel_add_friend_company})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rel_add_friend_search:
                UIHelper.startActivity(mContext, ConnectSearchActivity.class);

                break;
            case R.id.view_add_friend_my_qr:
                UIHelper.startActivity(mContext, QrCodeActivity.class);

                break;
            case R.id.rel_add_friend_qr:
                if (ContextCompat.checkSelfPermission(ConnectAddFriendActivity.this
                        , Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
                    UIHelper.startActivity(mContext, ScanningActivity.class);
                } else {
                    ActivityCompat.requestPermissions(ConnectAddFriendActivity.this
                            , new String[]{Manifest.permission.CAMERA}, REQUEST_PERMISION_CODE_CAMARE);
                }

                break;
            case R.id.rel_add_friend_contact:


                break;
            case R.id.rel_add_friend_company:


                break;
        }
    }


}
