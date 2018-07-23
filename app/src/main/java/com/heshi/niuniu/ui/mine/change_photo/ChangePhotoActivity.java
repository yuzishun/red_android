package com.heshi.niuniu.ui.mine.change_photo;

import android.os.Bundle;
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
import com.heshi.niuniu.model.BaseInfoModel;
import com.heshi.niuniu.util.GlideUtils;
import com.heshi.niuniu.util.dialogutil.SelectImageFragment;
import com.heshi.niuniu.util.http.HttpParams;

import java.io.File;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.MultipartBody;

/**
 * Created by Administrator on 2018/7/9 0009.
 */

public class ChangePhotoActivity extends BaseActivity<ChangePhotoPresent> implements
        ChangePhotoContract.Model, SelectImageFragment.selectCompletionListener {


    @BindView(R.id.text_title)
    TextView textTitle;
    @BindView(R.id.tv_back)
    ImageView tvBack;
    @BindView(R.id.img_add_right)
    ImageView imgAddRight;
    @BindView(R.id.img_change_photo)
    ImageView imgChangePhoto;
    @BindView(R.id.view_back)
    RelativeLayout viewBack;
    private BaseInfoModel model;

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
        return R.layout.activity_change_photo;
    }


    @Override
    protected void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        model = (BaseInfoModel) getIntent().getSerializableExtra("data");
        viewBack.setVisibility(View.VISIBLE);
        imgAddRight.setVisibility(View.VISIBLE);
        imgAddRight.setImageResource(R.mipmap.icon_title_more);

        textTitle.setText("个人头像");
        GlideUtils.noCacheloadImg(model.getPhotoUrl(), imgChangePhoto);

    }


    @OnClick(R.id.img_add_right)
    public void onViewClicked() {
        Bundle data = new Bundle();
        data.putBoolean("isCrop", true);

        SelectImageFragment.getInstance(data, imgChangePhoto, this)
                .show(getSupportFragmentManager(), SelectImageFragment.class.getName());

    }

    @Override
    public void completion(String path) {
        File file = new File(path);

        HttpParams params = new HttpParams();
        params.put("mFile", file);
        params.put("user_name", Constants.userName);
        List<MultipartBody.Part> list = params.setMoreImgType();

        mPresenter.upDataUrl(list,path);

    }
}
