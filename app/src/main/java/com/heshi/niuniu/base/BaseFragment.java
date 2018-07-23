package com.heshi.niuniu.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.heshi.niuniu.R;
import com.heshi.niuniu.di.component.AppComponent;
import com.heshi.niuniu.di.module.FragmentModule;
import com.heshi.niuniu.present.IPresenter;
import com.heshi.niuniu.present.MModel;
import com.heshi.niuniu.util.AppUtils;

import javax.inject.Inject;

import butterknife.ButterKnife;
import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrDefaultHandler2;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler2;

/**
 * Created by  min on 2016/4/12.
 */
public abstract class BaseFragment<T extends IPresenter> extends Fragment implements MModel {
    @Inject
    protected T mPresenter;
    protected View view;
    protected Activity mContext;
    public static final String CONST_NOCONTENT = "请重写getLayoutId或者getLayoutView,进行activity的内容设置";

    protected RecyclerView recyclerView;
    protected View contentView;
    protected PtrClassicFrameLayout ptrFrame;
    protected String mFragmentTitle;
    private boolean isVisible;                  //是否可见状态
    private boolean isPrepared;                 //标志位，View已经初始化完成。
    private boolean isFirstLoad = true;         //是否第一次加载


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(getLayoutId(), container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);

        /**
         * 自适应状态栏高度
         */
        _autoAdaptiveTopBar();
//        _ContentViewInit();
        setupActivityComponent(MyApplication.getAppComponent(), new FragmentModule(this));
        mContext = getActivity();

        if (ptrFrame != null & recyclerView != null) {
            initRefresh(ptrFrame, recyclerView);
        }
        if (mPresenter != null) {
            mPresenter.attachView(this);
        }
        isFirstLoad = true;
        isPrepared = true;
        lazyLoad();

//        _ContentViewInit();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getData(savedInstanceState);
        initEventAndData();
    }

    public View findViewById(int res) {
        return view.findViewById(res);
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

    /**
     * 返回一个topbar的id让base处理状态栏的高度
     * 通过view的高度方式解决
     *
     * @return
     */
    protected View _topBarAdaptiveByViewVH() {
        View result = null;
        int cache = _topBarAdaptiveVH();
        if (cache > 0) {
            result = findViewById(cache);
        }
        return result;
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
    public void onStart() {
        super.onStart();
    }

//    @Override
//    public void onDestroyView() {
//        super.onDestroyView();
//        ButterKnife.bind(this, view).unbind();
//    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) mPresenter.detachView();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        saveData(outState);
        super.onSaveInstanceState(outState);

    }


    /**
     * 依赖注入的入口
     *
     * @param appComponent appComponent
     */
    protected abstract void setupActivityComponent(AppComponent appComponent, FragmentModule fragmentModule);

    protected int getLayoutId() {
        return 0;
    }

    protected View getLayoutView() {
        return null;
    }

    protected void initEventAndData() {
    }

    protected void getData(Bundle bundle) {
    }

    protected void saveData(Bundle bundle) {
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

    public String getmFragmentTitle() {
        return mFragmentTitle;
    }

    public void setmFragmentTitle(String mFragmentTitle) {
        this.mFragmentTitle = mFragmentTitle;
    }

    /**
     * 如果是与ViewPager一起使用，调用的是setUserVisibleHint
     */
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (getUserVisibleHint()) {
            isVisible = true;
            onVisible();
        } else {
            isVisible = false;
            onInvisible();
        }
    }

    /**
     * 如果是通过FragmentTransaction的show和hide的方法来控制显示，调用的是onHiddenChanged.
     * 若是初始就show的Fragment 为了触发该事件 需要先hide再show
     */
    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {
            isVisible = true;
            onVisible();
        } else {
            isVisible = false;
            onInvisible();
        }
    }

    protected void onVisible() {
        lazyLoad();
    }

    protected void onInvisible() {

    }

    protected void lazyLoad() {
        if (!isPrepared || !isVisible || !isFirstLoad) {
            return;
        }
        isFirstLoad = false;
        initData();
    }

    //做取消预加载的操作viewpager
    protected void initData() {

    }

    protected void setTextEmptyView(String str) {
        View layout = view.findViewById(R.id.layout_empty_view);
        layout.setVisibility(View.VISIBLE);

        TextView textEmpty = (TextView) view.findViewById(R.id.text_empty_view);

//        ImageView imgEmpty= (ImageView) view.findViewById(R.id.img_empty_view);
        textEmpty.setText(str);
    }

    protected void setCorrectText(TextView tv, Object str) {
        if (str instanceof Integer || str instanceof Float || str instanceof Long || str instanceof Double || str instanceof String) {
            String newStr = str + "";

            if (!TextUtils.isEmpty(newStr)) {
                if (!newStr.contains("null")) {
                    tv.setText(newStr);
                } else {
                    tv.setText("");
                }
            } else {
                tv.setText("");
            }
        }

    }

}
