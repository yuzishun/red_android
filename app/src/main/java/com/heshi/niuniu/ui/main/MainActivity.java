package com.heshi.niuniu.ui.main;

import android.Manifest;
import android.animation.Animator;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.mobileim.YWAPI;
import com.alibaba.mobileim.YWIMCore;
import com.alibaba.mobileim.YWIMKit;
import com.alibaba.mobileim.channel.util.YWLog;
import com.alibaba.mobileim.conversation.IYWConversationService;
import com.alibaba.mobileim.conversation.IYWConversationUnreadChangeListener;
import com.alibaba.mobileim.conversation.IYWMessageLifeCycleListener;
import com.alibaba.mobileim.conversation.YWConversation;
import com.alibaba.mobileim.conversation.YWConversationType;
import com.alibaba.mobileim.conversation.YWMessage;
import com.alibaba.mobileim.conversation.YWMessageType;
import com.alibaba.mobileim.conversation.YWPushInfo;
import com.alibaba.mobileim.login.IYWConnectionListener;
import com.alibaba.mobileim.login.YWLoginCode;
import com.alibaba.mobileim.login.YWLoginState;
//import com.example.qrcode.Constant;
//import com.example.qrcode.ScannerActivity;
import com.google.gson.Gson;
import com.heshi.niuniu.R;
import com.heshi.niuniu.app.Constants;
import com.heshi.niuniu.base.BaseActivity;
import com.heshi.niuniu.base.MyApplication;
import com.heshi.niuniu.custom.tab.CustomTabView;
import com.heshi.niuniu.di.component.AppComponent;
import com.heshi.niuniu.di.component.DaggerActivityComponent;
import com.heshi.niuniu.di.module.ActivityModule;
import com.heshi.niuniu.fragment.main.connect.ConnectingFragment;
import com.heshi.niuniu.fragment.main.dynamic.DynamicFragment;
import com.heshi.niuniu.fragment.main.mine.MineFragment;
import com.heshi.niuniu.fragment.main.msg.MsgFragment;
import com.heshi.niuniu.fragment.main.weibo.WeiBoFragment;
import com.heshi.niuniu.im.sample.LoginSampleHelper;
import com.heshi.niuniu.model.ImCusModel;
import com.heshi.niuniu.ui.login.LoginActivity;
import com.heshi.niuniu.ui.main.connect.ConnectAddFriendActivity;
import com.heshi.niuniu.ui.main.dynamic.PublishDynamicActivity;
import com.heshi.niuniu.ui.mine.qr.scan.ScanningActivity;
import com.heshi.niuniu.util.AnimUtil;
import com.heshi.niuniu.util.GlideImageLoader;
import com.heshi.niuniu.util.NineGlideImageLoader;
import com.heshi.niuniu.util.UIHelper;
import com.lzy.ninegrid.NineGridView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

import static com.heshi.niuniu.util.UiUtils.dp2px;

/**
 * Created by Administrator on 2018/6/28 0028.
 */

public class MainActivity extends BaseActivity<MainPresent> implements CustomTabView.OnTabCheckListener, ViewPager.OnPageChangeListener, View.OnClickListener {


    @BindView(R.id.viewpager_main)
    ViewPager viewpagerMain;
    @BindView(R.id.mCustomTabView)
    CustomTabView mCustomTabView;
    @BindView(R.id.img_add_right)
    ImageView imgAddRight;
    @BindView(R.id.text_title)
    TextView textTitle;
    @BindView(R.id.t_title)
    Toolbar tTitle;
    private List<Fragment> fragmentList = new ArrayList<>();
    private List<String> strList = new ArrayList<>();
    private AnimUtil animUtil;
    private PopupWindow mPopupWindow;
    private static final long DURATION = 100;
    private static final float START_ALPHA = 0.7f;
    private static final float END_ALPHA = 1f;
    private float bgAlpha = 1f;
    private boolean bright = false;
    private TextView tv_1, tv_2, tv_3;
    private final int REQUEST_PERMISION_CODE_CAMARE = 0;
    private final int RESULT_REQUEST_CODE = 1;
    private IYWMessageLifeCycleListener mMessageLifeCycleListener;
    private IYWConnectionListener mConnectionListener;
    private IYWConversationUnreadChangeListener mConversationUnreadChangeListener;
    private IYWConversationService mConversationService;
    private YWIMKit mIMKit;
    private Handler mHandler = new Handler(Looper.getMainLooper());


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
        return R.layout.activity_main;
    }


    @Override
    protected void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        initFragmentData();
        setTabData();
        initPop();

        NineGridView.setImageLoader(new NineGlideImageLoader());

        YWIMCore kit = YWAPI.createIMCore(Constants.im_usrName, Constants.appkey);
        if (kit != null) {
            mConversationService = kit.getConversationService();
            mIMKit = LoginSampleHelper.getInstance().getIMKit();
            if (mIMKit != null) {
                initListener();
                setMessageLifeCycleListener();
            }
        }

    }

    /**
     * tab数据初始化
     */
    private void setTabData() {
        CustomTabView.Tab tabMsg = new CustomTabView.Tab().setText("消息")
                .setColor(getResources().getColor(R.color.color_8989a6))
                .setCheckedColor(getResources().getColor(R.color.color_f93967))
                .setNormalIcon(R.mipmap.icon_msg_select_un)
                .setPressedIcon(R.mipmap.icon_msg_select);

        mCustomTabView.addTab(tabMsg);

        CustomTabView.Tab tabConnect = new CustomTabView.Tab().setText("人脉")
                .setColor(getResources().getColor(R.color.color_8989a6))
                .setCheckedColor(getResources().getColor(R.color.color_f93967))
                .setNormalIcon(R.mipmap.icon_connection_select_un)
                .setPressedIcon(R.mipmap.icon_connection_select);

        mCustomTabView.addTab(tabConnect);

        CustomTabView.Tab tabWeiBo = new CustomTabView.Tab().setText("微博")
                .setColor(getResources().getColor(R.color.color_8989a6))
                .setCheckedColor(getResources().getColor(R.color.color_f93967))
                .setNormalIcon(R.mipmap.icon_vitas_select_un)
                .setPressedIcon(R.mipmap.icon_vitas_select);

        mCustomTabView.addTab(tabWeiBo);

        CustomTabView.Tab tabDynamic = new CustomTabView.Tab().setText("动态")
                .setColor(getResources().getColor(R.color.color_8989a6))
                .setCheckedColor(getResources().getColor(R.color.color_f93967))
                .setNormalIcon(R.mipmap.icon_dynamic_select_un)
                .setPressedIcon(R.mipmap.icon_dynamic_select);

        mCustomTabView.addTab(tabDynamic);

        CustomTabView.Tab tabMine = new CustomTabView.Tab().setText("我的")
                .setColor(getResources().getColor(R.color.color_8989a6))
                .setCheckedColor(getResources().getColor(R.color.color_f93967))
                .setNormalIcon(R.mipmap.icon_mine_select_un)
                .setPressedIcon(R.mipmap.icon_mine_select);

        mCustomTabView.addTab(tabMine);
        mCustomTabView.setOnTabCheckListener(this);
        mCustomTabView.setCurrentItem(0);
    }

    @Override
    public void onTabSelected(View v, int position) {
        viewpagerMain.setCurrentItem(position);
        imgAddRight.setVisibility(View.GONE);

        switch (position) {
            case 0:
                imgAddRight.setVisibility(View.VISIBLE);
                imgAddRight.setImageResource(R.drawable.icon_add);

                textTitle.setText("消息");

                break;
            case 1:
                imgAddRight.setVisibility(View.VISIBLE);
                imgAddRight.setImageResource(R.drawable.icon_connect_friend);

                textTitle.setText("人脉");

                break;
            case 2:
                textTitle.setText("微博");

                break;
            case 3:
                imgAddRight.setVisibility(View.VISIBLE);
                imgAddRight.setImageResource(R.drawable.icon_title_camera);
                textTitle.setText("动态");

                break;
            case 4:
                textTitle.setText("我的");

                break;
        }
    }

    /**
     * fragment数据初始化
     */
    private void initFragmentData() {
        fragmentList.add(new MsgFragment());
        fragmentList.add(new ConnectingFragment());
        fragmentList.add(new WeiBoFragment());
        fragmentList.add(new DynamicFragment());
        fragmentList.add(new MineFragment());

        strList.add("消息");
        strList.add("人脉");
        strList.add("微博");
        strList.add("动态");
        strList.add("我的");
        mPresenter.initAdapter(getSupportFragmentManager(), fragmentList, strList, viewpagerMain);
        viewpagerMain.addOnPageChangeListener(this);
        viewpagerMain.setOffscreenPageLimit(5);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        mCustomTabView.setCurrentItem(position);

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @OnClick(R.id.img_add_right)
    public void onViewClicked() {
        int index = viewpagerMain.getCurrentItem();

        switch (index) {
            case 0:
                showPop();
                toggleBright();
                break;
            case 1:
                UIHelper.startActivity(mContext, ConnectAddFriendActivity.class);

                break;
            case 2:

                break;
            case 3:
                UIHelper.startActivity(mContext, PublishDynamicActivity.class);

                break;
            case 4:

                break;
        }
    }

    private void initPop() {
        mPopupWindow = new PopupWindow(this);
        animUtil = new AnimUtil();
    }

    private void showPop() {
        // 设置布局文件
        mPopupWindow.setContentView(LayoutInflater.from(this).inflate(R.layout.pop_add, null));
        // 为了避免部分机型不显示，我们需要重新设置一下宽高
        mPopupWindow.setWidth(ViewGroup.LayoutParams.WRAP_CONTENT);
        mPopupWindow.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        // 设置pop透明效果
        mPopupWindow.setBackgroundDrawable(new ColorDrawable(0x0000));
        // 设置pop出入动画
        mPopupWindow.setAnimationStyle(R.style.pop_add);
        // 设置pop获取焦点，如果为false点击返回按钮会退出当前Activity，如果pop中有Editor的话，focusable必须要为true
        mPopupWindow.setFocusable(true);
        // 设置pop可点击，为false点击事件无效，默认为true
        mPopupWindow.setTouchable(true);
        // 设置点击pop外侧消失，默认为false；在focusable为true时点击外侧始终消失
        mPopupWindow.setOutsideTouchable(true);
        // 相对于 + 号正下面，同时可以设置偏移量
        mPopupWindow.showAsDropDown(imgAddRight, -100, 0);

//        mPopupWindow.showAtLocation(tTitle, 0&Gravity.RIGHT, 0, 0);
        // 设置pop关闭监听，用于改变背景透明度
        mPopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                toggleBright();
            }
        });

        tv_1 = (TextView) mPopupWindow.getContentView().findViewById(R.id.tv_1);
        tv_2 = (TextView) mPopupWindow.getContentView().findViewById(R.id.tv_2);
        tv_3 = (TextView) mPopupWindow.getContentView().findViewById(R.id.tv_3);


        tv_1.setOnClickListener(this);
        tv_2.setOnClickListener(this);
        tv_3.setOnClickListener(this);

    }

    private void toggleBright() {
        // 三个参数分别为：起始值 结束值 时长，那么整个动画回调过来的值就是从0.5f--1f的
        animUtil.setValueAnimator(START_ALPHA, END_ALPHA, DURATION);
        animUtil.addUpdateListener(new AnimUtil.UpdateListener() {
            @Override
            public void progress(float progress) {
                // 此处系统会根据上述三个值，计算每次回调的值是多少，我们根据这个值来改变透明度
                bgAlpha = bright ? progress : (START_ALPHA + END_ALPHA - progress);
                backgroundAlpha(bgAlpha);
            }
        });
        animUtil.addEndListner(new AnimUtil.EndListener() {
            @Override
            public void endUpdate(Animator animator) {
                // 在一次动画结束的时候，翻转状态
                bright = !bright;
            }
        });
        animUtil.startAnimator();
    }

    /**
     * 此方法用于改变背景的透明度，从而达到“变暗”的效果
     */
    private void backgroundAlpha(float bgAlpha) {
        WindowManager.LayoutParams lp = getWindow().getAttributes();
        // 0.0-1.0
        lp.alpha = bgAlpha;
        getWindow().setAttributes(lp);
        // everything behind this window will be dimmed.
        // 此方法用来设置浮动层，防止部分手机变暗无效
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.tv_1:
                mPopupWindow.dismiss();

                break;
            case R.id.tv_2:
                mPopupWindow.dismiss();

                break;
            case R.id.tv_3:
                mPopupWindow.dismiss();
                if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
                    UIHelper.startActivity(mContext, ScanningActivity.class);
                } else {
                    ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.CAMERA}, REQUEST_PERMISION_CODE_CAMARE);
                }

                break;
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String permissions[], @NonNull int[] grantResults) {
        switch (requestCode) {
            case REQUEST_PERMISION_CODE_CAMARE: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    UIHelper.startActivity(mContext, ScanningActivity.class);
                }
                return;
            }

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case RESULT_REQUEST_CODE:
                    if (data == null) return;
                    String type = data.getStringExtra(Constants.EXTRA_RESULT_CODE_TYPE);
                    String content = data.getStringExtra(Constants.EXTRA_RESULT_CONTENT);
                    Toast.makeText(this, "codeType:" + type
                            + "-----content:" + content, Toast.LENGTH_SHORT).show();
                    break;
                default:
                    break;

            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }


    public void setSelectTab(int index) {
        viewpagerMain.setCurrentItem(index);
    }

    private void setMessageLifeCycleListener() {
        mMessageLifeCycleListener = new IYWMessageLifeCycleListener() {
            /**
             * 发送消息前回调
             * @param conversation 当前消息所在会话
             * @param message      当前将要发送的消息
             * @return 需要发送的消息，若为null，则表示不发送消息
             */
            @Override
            public YWMessage onMessageLifeBeforeSend(YWConversation conversation, YWMessage message) {
                //todo 以下代码仅仅是示例，开发者无需按照以下方式设置，应该根据自己的需求对消息进行修改
                String cvsType = "单聊";
                if (conversation.getConversationType() == YWConversationType.Tribe) {
                    cvsType = "群聊：";
                }
                String msgType = "文本消息";
                if (message.getSubType() == YWMessage.SUB_MSG_TYPE.IM_IMAGE) {
                    msgType = "图片消息";
                } else if (message.getSubType() == YWMessage.SUB_MSG_TYPE.IM_GEO) {
                    msgType = "地理位置消息";
                } else if (message.getSubType() == YWMessage.SUB_MSG_TYPE.IM_AUDIO) {
                    msgType = "语音消息";
                } else if (message.getSubType() == YWMessage.SUB_MSG_TYPE.IM_P2P_CUS || message.getSubType() == YWMessage.SUB_MSG_TYPE.IM_TRIBE_CUS) {
                    msgType = "自定义消息";
                }

                Gson gson = new Gson();
                ImCusModel model = new ImCusModel(Constants.userName, Constants.IMG_URL);

                //TODO 设置APNS Push，如果开发者需要对APNS Push进行定制可以调用message.setPushInfo(YWPushInfo)方法进行设置，如果不需要APNS Push定制则不需要调用message.setPushInfo(YWPushInfo)方法
                //TODO Demo默认发送消息不需要APNS Push定制,所以注释掉以下两行代码
                YWPushInfo pushInfo = new YWPushInfo(1, cvsType + msgType, "dingdong", "我是自定义数据");
                message.setPushInfo(pushInfo);

                //根据消息类型对消息进行修改，切记这里只是示例，具体怎样对消息进行修改开发者可以根据自己的需求进行处理
                if (message.getSubType() == YWMessage.SUB_MSG_TYPE.IM_TEXT) {
                    String content = message.getContent();

                }
                return message;
            }

            /**
             * 发送消息结束后回调
             * @param message   当前发送的消息
             * @param sendState 消息发送状态，具体状态请参考{@link com.alibaba.mobileim.conversation.YWMessageType.SendState}
             */
            @Override
            public void onMessageLifeFinishSend(YWMessage message, YWMessageType.SendState sendState) {
//                Notification.showToastMsg(FragmentTabs.this, sendState.toString());
            }
        };
        mConversationService.setMessageLifeCycleListener(mMessageLifeCycleListener);
    }

    //设置连接状态的监听
    private void addConnectionListener() {
        mConnectionListener = new IYWConnectionListener() {
            @Override
            public void onDisconnect(int code, String info) {
                if (code == YWLoginCode.LOGON_FAIL_KICKOFF) {
                    //在其它终端登录，当前用户被踢下线
                    Toast.makeText(MyApplication.application, "被踢下线", Toast.LENGTH_LONG).show();
                    YWLog.i("LoginSampleHelper", "被踢下线");
                    LoginSampleHelper.getInstance().setAutoLoginState(YWLoginState.idle);
                    appManager.finishActivity();
                    Intent intent = new Intent(mContext, LoginActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                }
            }

            @Override
            public void onReConnecting() {

            }

            @Override
            public void onReConnected() {

            }
        };
        mIMKit.getIMCore().addConnectionListener(mConnectionListener);
    }

    private void initConversationServiceAndListener() {
        mConversationUnreadChangeListener = new IYWConversationUnreadChangeListener() {

            //当未读数发生变化时会回调该方法，开发者可以在该方法中更新未读数
            @Override
            public void onUnreadChange() {
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        LoginSampleHelper loginHelper = LoginSampleHelper.getInstance();
                        final YWIMKit imKit = loginHelper.getIMKit();
                        mConversationService = imKit.getConversationService();
                        //获取当前登录用户的所有未读数
                        int unReadCount = mConversationService.getAllUnreadCount();
                        //设置桌面角标的未读数
                        mIMKit.setShortcutBadger(unReadCount);

                        if (unReadCount > 0) {
                            mCustomTabView.setUnReadCount(unReadCount);
                        }
                    }
                });
            }
        };
        mConversationService.addTotalUnreadChangeListener(mConversationUnreadChangeListener);
    }

    private void initListener() {
        addConnectionListener();
        initConversationServiceAndListener();
    }

}
