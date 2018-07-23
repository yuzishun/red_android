package com.heshi.niuniu.ui.mine.info;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.heshi.niuniu.R;
import com.heshi.niuniu.base.BaseActivity;
import com.heshi.niuniu.custom.RoundedImageView;
import com.heshi.niuniu.di.component.AppComponent;
import com.heshi.niuniu.di.component.DaggerActivityComponent;
import com.heshi.niuniu.di.module.ActivityModule;
import com.heshi.niuniu.eventbus.MessageEvent;
import com.heshi.niuniu.eventbus.PhotoMsgEvent;
import com.heshi.niuniu.model.BaseInfoModel;
import com.heshi.niuniu.ui.mine.change_photo.ChangePhotoActivity;
import com.heshi.niuniu.ui.mine.qr.QrCodeActivity;
import com.heshi.niuniu.util.GlideUtils;
import com.heshi.niuniu.util.UIHelper;

import org.eclipse.jdt.core.IField;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import butterknife.BindView;
import butterknife.OnClick;

import static com.heshi.niuniu.eventbus.Event_Base.EType.SET_PHOTO;

/**
 * Created by Administrator on 2018/7/9 0009.
 */

public class PersonInfoActivity extends BaseActivity<PersonInfoPresent> implements
        PersonInfoContract.Model {

    @BindView(R.id.text_title)
    TextView textTitle;
    @BindView(R.id.img_info_head)
    RoundedImageView imgInfoHead;
    @BindView(R.id.text_info_nick_name)
    TextView textInfoNickName;
    @BindView(R.id.text_info_count)
    TextView textInfoCount;
    @BindView(R.id.img_add_right)
    ImageView imgAddRight;

    @BindView(R.id.view_info_head)
    RelativeLayout viewInfoHead;
    @BindView(R.id.view_back)
    View tvBack;
    private BaseInfoModel model;

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
        return R.layout.activity_person_info;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        EventBus.getDefault().register(this);

        tvBack.setVisibility(View.VISIBLE);
        textTitle.setText("基本信息");

        model = (BaseInfoModel) getIntent().getSerializableExtra("data");
        initInfo(model);

    }

    private void initInfo(BaseInfoModel model) {
        if (model != null) {
            textInfoNickName.setText(model.getNick_name());
            GlideUtils.noCacheloadImg(model.getPhotoUrl(), imgInfoHead);
        }
    }


    @OnClick({R.id.view_info_nick_name, R.id.view_info_count, R.id.view_info_qr_code,
            R.id.view_info_more, R.id.view_info_head})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.view_info_nick_name:

                break;
            case R.id.view_info_count:

                break;
            case R.id.view_info_qr_code:
                UIHelper.startActivity(mContext, QrCodeActivity.class);

                break;
            case R.id.view_info_more:

                break;
            case R.id.view_info_head:
                Bundle data = new Bundle();
                data.putSerializable("data", model);
                UIHelper.startActivity(mContext, ChangePhotoActivity.class, data);

                break;
        }
    }

    @Subscribe
    public void getEvent(PhotoMsgEvent event) {
        if (!TextUtils.isEmpty(event.getUrl())) {
            GlideUtils.loadCacheUri(event.getUrl(), imgInfoHead);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);

    }
}
