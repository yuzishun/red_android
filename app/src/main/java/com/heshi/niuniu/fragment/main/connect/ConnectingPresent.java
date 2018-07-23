package com.heshi.niuniu.fragment.main.connect;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alibaba.mobileim.YWChannel;
import com.alibaba.mobileim.conversation.YWConversation;
import com.alibaba.mobileim.conversation.YWConversationType;
import com.alibaba.mobileim.ui.WxChattingActvity;
import com.heshi.niuniu.R;
import com.heshi.niuniu.adapter.ContactAllAdapter;
import com.heshi.niuniu.app.Constants;
import com.heshi.niuniu.im.sample.LoginSampleHelper;
import com.heshi.niuniu.model.ConnectFriendModel;
import com.heshi.niuniu.model.ContactModel;
import com.heshi.niuniu.model.ImModel;
import com.heshi.niuniu.present.BasePresenter;
import com.heshi.niuniu.rx.data.RxResultHelper;
import com.heshi.niuniu.rx.data.SchedulersCompat;
import com.heshi.niuniu.ui.login.LoginApi;
import com.heshi.niuniu.ui.main.connect.new_friend.NewFriendActivity;
import com.heshi.niuniu.util.HttpDialog;
import com.heshi.niuniu.util.UIHelper;
import com.heshi.niuniu.util.pinyin.CharacterParser;
import com.heshi.niuniu.util.pinyin.PinyinComparator;
import com.heshi.niuniu.util.recyclerview.BaseMyRecyclerVIewAdapter;
import com.yanzhenjie.recyclerview.swipe.SwipeMenu;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuBridge;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuCreator;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuItem;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuItemClickListener;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Subscriber;

import static com.heshi.niuniu.util.UiUtils.dp2px;

/**
 * Created by Administrator on 2018/6/29 0029.
 */

public class ConnectingPresent extends BasePresenter<ConnectingContract.Model>
        implements ConnectingContract.Presenter, BaseMyRecyclerVIewAdapter.setOnItemClickListener, View.OnClickListener {

    private ConnectingApi api;
    private CharacterParser characterParser;
    private PinyinComparator pinyinComparator;
    private List<ContactModel> list = new ArrayList<>();
    private ContactAllAdapter adapter;
    private SwipeMenuRecyclerView recyclerView;
    private View view;
    private HttpDialog dialog;

    @Inject
    public ConnectingPresent(Activity activity, OkHttpClient okHttpClient, Retrofit retrofit) {
        super(activity, okHttpClient, retrofit);
        dialog = new HttpDialog(mActivity);

        characterParser = CharacterParser.getInstance();
        pinyinComparator = new PinyinComparator();
        api = retrofit.create(ConnectingApi.class);

    }


    @Override
    public void upCurrListIndex(String key) {
        List<ContactModel> cache = list;
        for (int i = 0; i < cache.size(); i++) {
            if (key.equals(cache.get(i).getSortLetters())) {
                adapter.MoveToPosition((LinearLayoutManager) recyclerView.getLayoutManager(), i);
                break;
            }
        }
    }

    @Override
    public void initAdapter(SwipeMenuRecyclerView recyclerView, int type) {
        this.recyclerView = recyclerView;
        adapter = new ContactAllAdapter(mActivity, list);
//        recyclerView.setSwipeMenuCreator(swipeMenuCreator);
        recyclerView.setSwipeMenuItemClickListener(mMenuItemClickListener);

        recyclerView.setLayoutManager(new LinearLayoutManager(mActivity));
        recyclerView.setAdapter(adapter);
        adapter.setOnItemClickListener(this);

        view = LayoutInflater.from(mActivity).inflate(R.layout.layout_connect_head, null);
        initHead(view);

    }

    private void initHead(View view) {
        View newFriend = view.findViewById(R.id.layout_connect_new_friend);
        View groupChat = view.findViewById(R.id.layout_connect_group_chat);
        View labor = view.findViewById(R.id.layout_connect_labor);
        View company = view.findViewById(R.id.layout_connect_company);

        newFriend.setOnClickListener(this);
        groupChat.setOnClickListener(this);
        labor.setOnClickListener(this);
        company.setOnClickListener(this);

    }

    @Override
    public void getList(String name) {
        dialog.show();

        addSubscription(api.getAllFriend(name)
                        .compose(SchedulersCompat.applyIoSchedulers())
                        .compose(RxResultHelper.handleResult())
                , new Subscriber<List<ContactModel>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        dialog.dismiss();
                    }

                    @Override
                    public void onNext(List<ContactModel> contactModels) {
                        list.clear();
                        //设置图片路径
                        for (ContactModel model : contactModels) {
                            model.setHard_pic(Constants.Icon_url + model.getUser_id());

                        }
                        list.addAll(contactModels);

                        //设置过滤条件
                        setUpData();
                        dialog.dismiss();
                    }
                });
    }

    /**
     * 给列表加过滤条件
     */
    private void setUpData() {
        for (ContactModel model : list) {
            if (TextUtils.isEmpty(model.getFriend_nick())) {
                model.setFriend_nick("匿名");
            }
            String pinyin = characterParser.getSelling(model.getFriend_nick());
            String sortString = pinyin.substring(0, 1)
                    .toUpperCase();

            if (sortString.matches("[A-Z]")) {
                model.setSortLetters(sortString.toUpperCase());
            } else {
                model.setSortLetters("#");
            }
        }

        Collections.sort(list, pinyinComparator);

        adapter.notifyDataSetChanged();

        adapter.setHeaderView(view);


    }

    private SwipeMenuCreator swipeMenuCreator = new SwipeMenuCreator() {
        @Override
        public void onCreateMenu(SwipeMenu swipeLeftMenu, SwipeMenu swipeRightMenu, int viewType) {
            int width = dp2px(55, mActivity);

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
        public SwipeMenuBridge menuBridges;

        @Override
        public void onItemClick(SwipeMenuBridge menuBridge) {
            menuBridges = menuBridge;

            int direction = menuBridge.getDirection(); // 左侧还是右侧菜单。
            int adapterPosition = menuBridge.getAdapterPosition(); // RecyclerView的Item的position。
            int menuPosition = menuBridge.getPosition(); // 菜单在RecyclerView的Item中的Position。

            if (direction == SwipeMenuRecyclerView.RIGHT_DIRECTION) {
                ContactModel data = adapter.getItem(adapterPosition);

            } else if (direction == SwipeMenuRecyclerView.LEFT_DIRECTION) {
//                Toast.makeText(mActivity, "list第" + adapterPosition + "; 左侧菜单第" + menuPosition, Toast.LENGTH_SHORT).show();
//                sendOrder(orderList.get(adapterPosition).getId(), menuPosition);

            }
            menuBridges.closeMenu();
        }
    };

    @Override
    public void onItemClick(View view, int position) {
        Intent intent = new Intent(YWChannel.getApplication(), WxChattingActvity.class);
        intent.putExtra("extraUserId", list.get(position).getIm_user());
        intent.putExtra("extraAppKey", Constants.appkey);
        intent.putExtra("conversationType", YWConversationType.P2P.getValue());
        mActivity.startActivity(intent);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.layout_connect_new_friend:
                UIHelper.startActivity(mActivity, NewFriendActivity.class);
                break;

            case R.id.layout_connect_group_chat:

                break;

            case R.id.layout_connect_labor:

                break;

            case R.id.layout_connect_company:

                break;

        }

    }
}
