package com.heshi.niuniu.fragment.main.msg;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.alibaba.mobileim.YWAPI;
import com.alibaba.mobileim.YWIMCore;
import com.alibaba.mobileim.YWIMKit;
import com.alibaba.mobileim.channel.event.IWxCallback;
import com.alibaba.mobileim.channel.util.YWLog;
import com.alibaba.mobileim.conversation.IYWConversationListener;
import com.alibaba.mobileim.conversation.IYWConversationService;
import com.alibaba.mobileim.conversation.IYWConversationUnreadChangeListener;
import com.alibaba.mobileim.conversation.IYWMessageLifeCycleListener;
import com.alibaba.mobileim.conversation.IYWMessageListener;
import com.alibaba.mobileim.conversation.YWConversation;
import com.alibaba.mobileim.conversation.YWConversationType;
import com.alibaba.mobileim.conversation.YWMessage;
import com.alibaba.mobileim.conversation.YWMessageChannel;
import com.alibaba.mobileim.conversation.YWMessageType;
import com.alibaba.mobileim.conversation.YWPushInfo;
import com.alibaba.mobileim.login.IYWConnectionListener;
import com.alibaba.mobileim.login.YWLoginCode;
import com.alibaba.mobileim.login.YWLoginState;
import com.google.gson.Gson;
import com.heshi.niuniu.R;
import com.heshi.niuniu.app.Constants;
import com.heshi.niuniu.base.BaseFragment;
import com.heshi.niuniu.base.MyApplication;
import com.heshi.niuniu.di.component.AppComponent;
import com.heshi.niuniu.di.component.DaggerFragmentComponent;
import com.heshi.niuniu.di.module.FragmentModule;
import com.heshi.niuniu.eventbus.GetContactEvent;
import com.heshi.niuniu.im.common.Notification;
import com.heshi.niuniu.im.core.InitSample;
import com.heshi.niuniu.im.sample.LoginSampleHelper;
import com.heshi.niuniu.im.util.ConversationSampleHelper;
import com.heshi.niuniu.model.ImCusModel;
import com.heshi.niuniu.ui.login.LoginActivity;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Administrator on 2018/6/29 0029.
 */

public class MsgFragment extends BaseFragment<MsgPresent>
        implements MsgContract.Model {


    @BindView(R.id.rec_msg)
    SwipeMenuRecyclerView recMsg;
    private IYWConversationService mConversationService;
    private Handler mUIHandler = new Handler(Looper.getMainLooper());
    private YWIMKit mIMKit;
    private ConversationSampleHelper helper;


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
        return R.layout.fragment_home_message;
    }

    @Override
    protected void initData() {
        super.initData();
        EventBus.getDefault().register(this);
        mPresenter.initAdapter(recMsg);

        ConversationSampleHelper helper = new ConversationSampleHelper();
        mConversationService = helper.getConversationService();
        mConversationService.removeConversationListener(mConversationListener);
        mConversationService.addConversationListener(mConversationListener);

        if (helper != null)
            mPresenter.getMsgList(helper.getAllConversations());

    }

    @Override
    public void onResume() {
        super.onResume();
    }

    IYWConversationListener mConversationListener = new IYWConversationListener() {
        @Override
        public void onItemUpdated() {
            List<YWConversation> mConversationList = mConversationService.getConversationList();
            mPresenter.setNotify(mConversationList);
        }
    };


    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);

    }

    @Subscribe
    public void getEvent(GetContactEvent event){

    }

}
