package com.heshi.niuniu.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;


import com.heshi.niuniu.R;
import com.heshi.niuniu.di.component.AppComponent;
import com.heshi.niuniu.di.module.ActivityModule;
import com.heshi.niuniu.present.IPresenter;
import com.heshi.niuniu.present.MModel;
import com.heshi.niuniu.util.AppManager;
import com.heshi.niuniu.util.AppUtils;
import com.heshi.niuniu.util.ToashUtils;

import org.greenrobot.eventbus.Subscribe;

import javax.inject.Inject;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrDefaultHandler2;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler2;

import static android.view.KeyEvent.KEYCODE_BACK;

/**
 * Created by min on 2017/3/1.
 */

public abstract class BaseActivity<T extends IPresenter> extends AppCompatActivity implements MModel {
    @Inject
    protected T mPresenter;
    protected Activity mContext;
    protected Context context;
    protected Unbinder unbinder;
    public AppManager appManager;
    public static final String CONST_NOCONTENT = "请重写getLayoutId或者getLayoutView,进行activity的内容设置";
    //    @Nullable
//    @BindView(R.id.rv_recyclerview)
    protected RecyclerView recyclerView;
    //    @Nullable
//    @BindView(R.id.fragment_rotate_header_with_view_group_frame)
    protected PtrClassicFrameLayout ptrFrame;
    Bundle savedInstanceState;
    //屏幕高度
    private int screenHeight = 0;
    //软件盘弹起后所占高度阀值
    private int keyHeight = 0;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.savedInstanceState = savedInstanceState;
        /**
         * 初始化activity的样式
         * 以及activity的ContentView
         */
        _activity_styleInit();
        _ContentViewInit();

        // 判断当前的Activity是堆栈中是否存在
        appManager = AppManager.getAppManager();
        appManager.addActivity(this);
        mContext = this;

        setupActivityComponent(MyApplication.getAppComponent(), new ActivityModule(this));

        ButterKnife.bind(this);
        if (mPresenter != null) {
            mPresenter.attachView(this);
        }
        initView();
        initData(savedInstanceState);

        if (ptrFrame != null & recyclerView != null) {
            initRefresh(ptrFrame, recyclerView);
        }
        bindEvent();

        /**
         * 自动转接back的事件
         */
        _backProce();
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        hintKb();
        ButterKnife.bind(this).unbind();
        if (mPresenter != null) mPresenter.detachView();

    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        mContext = null;
        context = null;
    }

    public T p() {
        return mPresenter;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (event.getKeyCode()) {
            /**
             * 转接按键back交由back方法处理
             */
            case KEYCODE_BACK:
                back();

                return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    /**
     * =====
     *
     * Base private Method
     *
     * =====
     */

    /**
     * 自动转接back的事件
     */
    protected void _backProce() {
        View temp = findViewById(R.id.view_back);
        if (temp != null) {
            temp.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    back();
                    try {
                        //隐藏键盘
                        ((InputMethodManager) MyApplication.application.getSystemService(
                                Context.INPUT_METHOD_SERVICE)).hideSoftInputFromWindow(
                                getCurrentFocus().getWindowToken(),
                                InputMethodManager.HIDE_NOT_ALWAYS);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }


    protected void _activity_styleInit() {
        /**
         * 将当前activity取消titile
         */
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        // 将状态栏设置为半透明的状态
        this.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
    }
//

    /**
     * 设置view的内容
     */
    protected void _ContentViewInit() {
        if (getLayoutId() != 0) {
            setContentView(getLayoutId());
        } else {
            View tempView = getLayoutView();
            if (tempView == null) {
                throw new RuntimeException(CONST_NOCONTENT);
            }
            setContentView(tempView);
        }
        ButterKnife.bind(this);
        /**
         * 自适应状态栏高度
         */
        _autoAdaptiveTopBar();
    }

    /**
     * 自适应状态栏高度
     */
    private void _autoAdaptiveTopBar() {
        /**
         * 自适应状态栏高度
         * Padding模式
         */
        View tempTopBar = _topBarAdaptiveByViewP();
        if (tempTopBar != null) {
            AppUtils.AutoSteepProssByPadding(tempTopBar);
        }
        /**
         * 自适应状态栏高度
         * ViewHight模式
         */
        View tempTopBarVH = _topBarAdaptiveByViewVH();
        if (tempTopBarVH != null) {
            AppUtils.AutoSteepProssByHeight(tempTopBarVH);
        }
    }

    /**
     * 返回一个topbar的id让base处理状态栏的高度
     * 通过padding方式解决
     *
     * @return
     */
    protected int _topBarAdaptiveP() {
        return -1;
    }

    /**
     * 返回一个topbar的id让base处理状态栏的高度
     * 通过padding方式解决
     *
     * @return
     */
    protected View _topBarAdaptiveByViewP() {
        View result = null;
        int cache = _topBarAdaptiveP();
        if (cache > 0) {
            result = findViewById(cache);
        }
        return result;
    }

    /**
     * 返回一个topbar的id让base处理状态栏的高度
     * 通过view的高度方式解决
     *
     * @return
     */
    protected int _topBarAdaptiveVH() {
        return -1;
    }

    protected int editHeightView() {
        return -1;
    }

    /**
     * 返回一个topbar的id让base处理状态栏的高度
     * 通过view的高度方式解决
     *
     * @return
     */
    protected View _topBarAdaptiveByViewVH() {
        View result = null;
        int cache = _topBarAdaptiveVH() == -1 ? R.id.comm_topBarSteep : _topBarAdaptiveVH();
        if (cache > 0) {
            result = findViewById(cache);
        }
        return result;
    }

    protected void back() {
        appManager.finishActivity();
    }

    /**
     * 初始化刷新
     */
    public void initRefresh(PtrClassicFrameLayout ptrFrame, final RecyclerView recyclerView) {
        this.ptrFrame = ptrFrame;
        this.recyclerView = recyclerView;
        ptrFrame.setPtrHandler(new PtrHandler2() {
            @Override
            public boolean checkCanDoLoadMore(PtrFrameLayout frame, View content, View footer) {
                return PtrDefaultHandler2.checkContentCanBePulledUp(frame, recyclerView, footer);
            }

            @Override
            public void onLoadMoreBegin(PtrFrameLayout frame) {
                loadMoreData();
            }

            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                return PtrDefaultHandler.checkContentCanBePulledDown(frame, recyclerView, header);
            }

            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                updateData();
            }
        });
        ptrFrame.setResistance(2.3f);
        ptrFrame.setRatioOfHeaderHeightToRefresh(1.2f);
        ptrFrame.setDurationToClose(150);
        // default is false
        ptrFrame.setPullToRefresh(false);
        // default is true
        ptrFrame.setKeepHeaderWhenRefresh(true);

    }

    @Override
    protected void onResume() {
        super.onResume();
//        initData(savedInstanceState);

    }

    /**
     * 依赖注入的入口
     *
     * @param appComponent appComponent
     */
    protected abstract void setupActivityComponent(AppComponent appComponent, ActivityModule activityModule);

    protected int getLayoutId() {
        return 0;
    }

    protected View getLayoutView() {
        return null;
    }

    protected void initData(Bundle savedInstanceState) {
    }

    protected void initView() {
    }

    protected void bindEvent() {
    }

    /**
     * 刷新调用
     */
    protected void updateData() {
    }

    /**
     * 加载更多调用
     */
    protected void loadMoreData() {
    }


    /**
     * =====
     *
     * Base provider Method
     *
     * =====
     */
    /**
     * 双击退出函数
     */
    private static long _mBackClickTime = -1;

    /**
     * 双击退出应用
     */
    protected void exitBy2Click() {
        long cache = System.currentTimeMillis();
        if (_mBackClickTime == -1 || cache - _mBackClickTime <= 2000) {
            appManager.appExit(mContext);
        } else {
            _mBackClickTime = cache;
            toa("再按一次退出程序");
        }
    }

    public BaseActivity<T> toa(String str) {
        ToashUtils.show(this, str);
        return this;
    }

//    /**
//     * * Subscribe 注册监听
//     * 详情之后 通知我的申请列表更新
//     *
//     * @param event
//     */
//    @Subscribe
//    public void getEvent(MessageEvent event) {
//        /**
//         *  如果检测到重新登陆完每个页面就自动刷新
//         */
//        if ("Login".equals(event.action)) {
//            initData(savedInstanceState);
//        }
////        else if ("Refresh".equals(event.action)){
////            onResume();
////        }
//    }

    /**
     * 此方法只是关闭软键盘
     */
    private void hintKb() {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm.isActive() && getCurrentFocus() != null) {
            if (getCurrentFocus().getWindowToken() != null) {
                imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
            }
        }
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
            if (isShouldHideKeyboard(v, ev)) {
                hideKeyboard(v.getWindowToken());
            }
        }
        return super.dispatchTouchEvent(ev);
    }

    /**
     * 根据EditText所在坐标和用户点击的坐标相对比，来判断是否隐藏键盘，因为当用户点击EditText时则不能隐藏
     *
     * @param v
     * @param event
     * @return
     */
    private boolean isShouldHideKeyboard(View v, MotionEvent event) {
        if (v != null && (v instanceof EditText)) {
            int[] l = {0, 0};
            v.getLocationInWindow(l);
            int left = l[0],
                    top = l[1],
                    bottom = top + v.getHeight(),
                    right = left + v.getWidth();
            if (event.getX() > left && event.getX() < right
                    && event.getY() > top && event.getY() < bottom) {
                // 点击EditText的事件，忽略它。
                return false;
            } else {
                return true;
            }
        }
        // 如果焦点不是EditText则忽略，这个发生在视图刚绘制完，第一个焦点不在EditText上，和用户用轨迹球选择其他的焦点
        return false;
    }

    /**
     * 获取InputMethodManager，隐藏软键盘
     *
     * @param token
     */
    private void hideKeyboard(IBinder token) {
        if (token != null) {
            InputMethodManager im = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            im.hideSoftInputFromWindow(token, InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

}
