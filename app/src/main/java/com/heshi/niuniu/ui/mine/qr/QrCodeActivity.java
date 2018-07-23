package com.heshi.niuniu.ui.mine.qr;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.heshi.niuniu.R;
import com.heshi.niuniu.app.Constants;
import com.heshi.niuniu.base.BaseActivity;
import com.heshi.niuniu.custom.RoundedImageView;
import com.heshi.niuniu.di.component.AppComponent;
import com.heshi.niuniu.di.component.DaggerActivityComponent;
import com.heshi.niuniu.di.module.ActivityModule;
import com.heshi.niuniu.util.GlideUtils;
import com.uuzuche.lib_zxing.activity.CodeUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Administrator on 2018/7/16 0016.
 */

public class QrCodeActivity extends BaseActivity<QrCodePresent> implements QrCodeContract.Model {
    @BindView(R.id.text_title)
    TextView textTitle;
    @BindView(R.id.img_qr)
    ImageView imgQr;
    @BindView(R.id.img_friend_info)
    RoundedImageView imgFriendInfo;
    @BindView(R.id.text_info_name)
    TextView textInfoName;
    @BindView(R.id.view_back)
    RelativeLayout viewBack;
    private Bitmap mBitmap;
    private String url;

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
        return R.layout.activity_qr_code;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        viewBack.setVisibility(View.VISIBLE);
        textTitle.setText("我的二维码");

        textInfoName.setText(Constants.USER_NAME == null ? Constants.userName : Constants.USER_NAME);
        url = Constants.Im_Url + "red/use/gethard_pic.do?user_name=" + Constants.userName;
        GlideUtils.noCacheloadImg(url, imgFriendInfo);

        Glide.with(mContext).load(url).asBitmap().into(new SimpleTarget<Bitmap>() {
            @Override
            public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
                getBitmap(resource, Constants.userName);
            }
        });
    }

    private void getBitmap(Bitmap bitmap, String text) {
        mBitmap = CodeUtils.createImage(text, 400, 400, bitmap);
        imgQr.setImageBitmap(mBitmap);
    }


}
