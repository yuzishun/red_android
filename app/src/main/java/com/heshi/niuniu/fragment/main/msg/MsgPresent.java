package com.heshi.niuniu.fragment.main.msg;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.mobileim.YWChannel;
import com.alibaba.mobileim.YWIMKit;
import com.alibaba.mobileim.conversation.YWConversation;
import com.alibaba.mobileim.conversation.YWConversationType;
import com.alibaba.mobileim.ui.WxChattingActvity;
import com.heshi.niuniu.R;
import com.heshi.niuniu.adapter.msg.MessageAdapter;
import com.heshi.niuniu.app.Constants;
import com.heshi.niuniu.im.activity.chat.ChatActivity;
import com.heshi.niuniu.im.sample.LoginSampleHelper;
import com.heshi.niuniu.present.BasePresenter;
import com.heshi.niuniu.ui.webview.XWebViewActivity;
import com.heshi.niuniu.util.ToashUtils;
import com.heshi.niuniu.util.UIHelper;
import com.heshi.niuniu.util.recyclerview.BaseMyRecyclerVIewAdapter;
import com.yanzhenjie.recyclerview.swipe.SwipeMenu;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuBridge;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuCreator;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuItem;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuItemClickListener;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

import static com.heshi.niuniu.util.UiUtils.dp2px;

/**
 * Created by Administrator on 2018/6/29 0029.
 */

public class MsgPresent extends BasePresenter<MsgContract.Model>
        implements MsgContract.Presenter, BaseMyRecyclerVIewAdapter.CardListener, View.OnClickListener, BaseMyRecyclerVIewAdapter.setOnItemClickListener {

    private List<YWConversation> list = new ArrayList<>();
    private View view;
    private MessageAdapter adapter;
    private SwipeMenuBridge menuBridges;


    @Inject
    public MsgPresent(Activity activity, OkHttpClient okHttpClient, Retrofit retrofit) {
        super(activity, okHttpClient, retrofit);
    }


    @Override
    public void initAdapter(SwipeMenuRecyclerView recyclerView) {
        adapter = new MessageAdapter(mActivity, list);
        recyclerView.setSwipeMenuCreator(swipeMenuCreator);
        recyclerView.setSwipeMenuItemClickListener(mMenuItemClickListener);

        recyclerView.setLayoutManager(new LinearLayoutManager(mActivity));

//        recyclerView.addItemDecoration(new DividerItemDecoration(mActivity,
//                LinearLayout.VERTICAL, dp2px(1, mActivity),
//                mActivity.getResources().getColor(R.color.color_E8E8E9)));

        recyclerView.setAdapter(adapter);

        view = LayoutInflater.from(mActivity).inflate(R.layout.layout_message_head, null);
        initHead(view);
//        adapter.setCardListener(this);
        adapter.setOnItemClickListener(this);

    }

    private void initHead(View view) {
        View snatchPack = view.findViewById(R.id.view_message_head_snatch);
        View sendPack = view.findViewById(R.id.view_message_head_send);
        snatchPack.setOnClickListener(this);
        sendPack.setOnClickListener(this);

    }

    @Override
    public void getMsgList(List<YWConversation> mConversationList) {
        if (mConversationList!=null&&mConversationList.size()>0){
            list.addAll(mConversationList);
            adapter.notifyDataSetChanged();
        }
        adapter.setHeaderView(view);
    }

    @Override
    public void setNotify(List<YWConversation> mConversationList) {
        list.clear();
        list.addAll(mConversationList);
        adapter.setHeaderView(view);

        adapter.notifyDataSetChanged();
    }

    private SwipeMenuCreator swipeMenuCreator = new SwipeMenuCreator() {
        @Override
        public void onCreateMenu(SwipeMenu swipeLeftMenu, SwipeMenu swipeRightMenu, int viewType) {
            int width = dp2px(61, mActivity);

            // 1. MATCH_PARENT 自适应高度，保持和Item一样高;
            // 2. 指定具体的高，比如80;
            // 3. WRAP_CONTENT，自身高度，不推荐;
//            int height = dp2px(117, mActivity);
            int height = ViewGroup.LayoutParams.MATCH_PARENT;

            if (viewType == -2) {
                SwipeMenuItem deleteItem = new SwipeMenuItem(mActivity)
                        .setText("")
                        .setBackground(R.color.color_f93967)
                        .setImage(R.mipmap.icon_delect_1)
                        .setTextColor(Color.WHITE)
                        .setWidth(width)
                        .setHeight(height);
                swipeRightMenu.addMenuItem(deleteItem);// 添加菜单到右侧。
            }
        }
    };

    /**
     * RecyclerView的Item的Menu点击监听。
     */
    private SwipeMenuItemClickListener mMenuItemClickListener = new SwipeMenuItemClickListener() {
        @Override
        public void onItemClick(SwipeMenuBridge menuBridge) {
            menuBridges = menuBridge;

            int direction = menuBridge.getDirection(); // 左侧还是右侧菜单。
            int adapterPosition = menuBridge.getAdapterPosition(); // RecyclerView的Item的position。
            int menuPosition = menuBridge.getPosition(); // 菜单在RecyclerView的Item中的Position。

            if (direction == SwipeMenuRecyclerView.RIGHT_DIRECTION) {
                YWConversation data = adapter.getItem(adapterPosition);


            } else if (direction == SwipeMenuRecyclerView.LEFT_DIRECTION) {
//                Toast.makeText(mActivity, "list第" + adapterPosition + "; 左侧菜单第" + menuPosition, Toast.LENGTH_SHORT).show();
//                sendOrder(orderList.get(adapterPosition).getId(), menuPosition);

            }

            menuBridges.closeMenu();
        }
    };


    @Override
    public void onCardListener(View view, int position) {


    }

    @Override
    public void onClick(View view) {
        Bundle data = new Bundle();
        switch (view.getId()) {
            case R.id.view_message_head_snatch:
                data.putInt(XWebViewActivity.TYPE, XWebViewActivity.SNATCH_PACK);

                UIHelper.startActivity(mActivity, XWebViewActivity.class, data);

                break;
            case R.id.view_message_head_send:
                data.putInt(XWebViewActivity.TYPE, XWebViewActivity.SEND_PACK);
                UIHelper.startActivity(mActivity, XWebViewActivity.class, data);

                break;
        }

    }

    @Override
    public void onItemClick(View view, int position) {
        Intent intent = new Intent(YWChannel.getApplication(), WxChattingActvity.class);
        intent.putExtra("extraUserId", list.get(position).getLatestEServiceContactId());
        intent.putExtra("extraAppKey", Constants.appkey);
        intent.putExtra("conversationType", YWConversationType.P2P.getValue());
        mActivity.startActivity(intent);
    }
}
