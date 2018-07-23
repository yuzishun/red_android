package com.heshi.niuniu.fragment.main.mine;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.heshi.niuniu.R;
import com.heshi.niuniu.app.Constants;
import com.heshi.niuniu.base.BaseFragment;
import com.heshi.niuniu.custom.RoundedImageView;
import com.heshi.niuniu.di.component.AppComponent;
import com.heshi.niuniu.di.component.DaggerFragmentComponent;
import com.heshi.niuniu.di.module.FragmentModule;
import com.heshi.niuniu.eventbus.PhotoMsgEvent;
import com.heshi.niuniu.model.BaseInfoModel;
import com.heshi.niuniu.ui.main.MainActivity;
import com.heshi.niuniu.ui.mine.info.PersonInfoActivity;
import com.heshi.niuniu.util.GlideUtils;
import com.heshi.niuniu.util.UIHelper;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2018/6/29 0029.
 */

public class MineFragment extends BaseFragment<MinePresent>
        implements MineContract.Model {


    @BindView(R.id.img_mine_head)
    RoundedImageView imgMineHead;
    @BindView(R.id.text_mine_name)
    TextView textMineName;
    @BindView(R.id.text_mine_count)
    TextView textMineCount;
    @BindView(R.id.img_add_right)
    ImageView imgAddRight;
    @BindView(R.id.rel_mine)
    RelativeLayout relMine;
    @BindView(R.id.text_mine_weibo_num)
    TextView textMineWeiboNum;
    @BindView(R.id.view_mine_weibo_num)
    LinearLayout viewMineWeiboNum;
    @BindView(R.id.text_mine_dynamic_num)
    TextView textMineDynamicNum;
    @BindView(R.id.view_mine_dynamic_num)
    LinearLayout viewMineDynamicNum;
    @BindView(R.id.text_mine_fan_num)
    TextView textMineFanNum;
    @BindView(R.id.view_mine_fan_num)
    LinearLayout viewMineFanNum;
    @BindView(R.id.text_mine_red_packet_num)
    TextView textMineRedPacketNum;
    @BindView(R.id.view_mine_red_packet_num)
    LinearLayout viewMineRedPacketNum;
    @BindView(R.id.rec_mine)
    RecyclerView recMine;
    @BindView(R.id.text_mine_wallet)
    LinearLayout textMineWallet;
    @BindView(R.id.view_mine_collection)
    LinearLayout viewMineCollection;
    @BindView(R.id.view_mine_photo)
    LinearLayout viewMinePhoto;
    @BindView(R.id.view_mine_setting)
    LinearLayout viewMineSetting;
    private MainActivity activity;
    private BaseInfoModel model;
    private int data;

    @Override
    protected void setupActivityComponent(AppComponent appComponent, FragmentModule fragmentModule) {
        DaggerFragmentComponent.builder()
                .appComponent(appComponent)
                .fragmentModule(fragmentModule)
                .build()
                .inject(this);

    }


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_home_mine;
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.getBaseInfo(Constants.userName);

    }

    @Override
    protected void initData() {
        super.initData();
        EventBus.getDefault().register(this);
        activity = ((MainActivity) mContext);
    }

    @Override
    public void setBaseInfo(BaseInfoModel model) {
        if (model != null) {
            Constants.USER_ID = String.valueOf(model.getUser_id());
            Constants.USER_NAME=model.getNick_name();

            textMineCount.setText(model.getNick_name());
            String name = Constants.userName;
            String userId = String.valueOf(model.getUser_id());

            mPresenter.getWeiBoNum(name, textMineWeiboNum);
            mPresenter.getDynamic(name, textMineDynamicNum);
            mPresenter.getFan(userId, textMineFanNum);
            mPresenter.getRedPacket(userId, textMineDynamicNum);

            String url = Constants.Im_Url + "red/use/gethard_pic.do?user_name=" + Constants.userName;
            GlideUtils.noCacheloadImg(url, imgMineHead);
            model.setPhotoUrl(url);

            this.model = model;

        }

    }

    @OnClick({R.id.view_mine_weibo_num, R.id.view_mine_dynamic_num, R.id.view_mine_fan_num,
            R.id.view_mine_red_packet_num, R.id.view_mine_collection,
            R.id.view_mine_photo, R.id.view_mine_setting, R.id.rel_mine})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.view_mine_weibo_num:
                activity.setSelectTab(2);
                break;
            case R.id.view_mine_dynamic_num:
                activity.setSelectTab(3);

                break;
            case R.id.view_mine_fan_num:
                activity.setSelectTab(1);

                break;
            case R.id.view_mine_red_packet_num:
                activity.setSelectTab(0);

                break;
            case R.id.view_mine_collection:

                break;
            case R.id.view_mine_photo:

                break;
            case R.id.view_mine_setting:

                break;
            case R.id.rel_mine:
                if (model != null) {
                    Bundle data = new Bundle();
                    data.putSerializable("data", model);
                    UIHelper.startActivity(mContext, PersonInfoActivity.class, data);
                }

                break;

        }
    }

    @Subscribe
    public void getEvent(PhotoMsgEvent event) {
//        if (!TextUtils.isEmpty(event.getUrl())) {
//            data=1;
//            model.setPhotoUrl(event.getUrl());
//            GlideUtils.noCacheloadImg(event.getUrl(), imgMineHead);
//        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);

    }
}
