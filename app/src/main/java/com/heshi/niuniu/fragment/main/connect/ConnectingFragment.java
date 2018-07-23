package com.heshi.niuniu.fragment.main.connect;

import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;

import com.heshi.niuniu.R;
import com.heshi.niuniu.app.Constants;
import com.heshi.niuniu.base.BaseFragment;
import com.heshi.niuniu.di.component.AppComponent;
import com.heshi.niuniu.di.component.DaggerFragmentComponent;
import com.heshi.niuniu.di.module.FragmentModule;
import com.heshi.niuniu.util.AlphaImageView;
import com.heshi.niuniu.util.LetterToast;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Administrator on 2018/6/29 0029.
 */

public class ConnectingFragment extends BaseFragment<ConnectingPresent>
        implements ConnectingContract.Model, AlphaImageView.OnTouchLetterChangeListenner {
    @BindView(R.id.frg_all_contect_rv)
    SwipeMenuRecyclerView contectRv;
    @BindView(R.id.frg_contact_alpImgView)
    AlphaImageView aiv_Index;
    LetterToast letterToast;
    @BindView(R.id.layout_empty_view)
    FrameLayout layoutEmptyView;
    private int moreFilter;
    private List<Integer> newList;
    private int type = -1;


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
        return R.layout.fragment_home_connect;
    }

    @Override
    protected void initEventAndData() {
        mPresenter.initAdapter(contectRv, type);
        mPresenter.getList(Constants.userName);

        aiv_Index.setOnTouchLetterChangeListenner(this);

    }

    /**
     * 设置状态栏高度
     *
     * @return
     */
    @Override
    protected int _topBarAdaptiveVH() {
        return R.id.comm_topBarSteep;
    }


    /**
     * 单击右边索引调到指定联系人位置
     *
     * @param event
     * @param s
     */
    @Override
    public void onTouchLetterChange(MotionEvent event, String s) {
        if (!"MoveConfine".equals(s)) {
            if (letterToast == null) {
                letterToast = LetterToast.builderToast(getContext(), s);
            } else {
                letterToast.setText(s);
            }
            letterToast.show();
            if ("↑".equals(s)) {
                mPresenter.upCurrListIndex("C");
            }
            mPresenter.upCurrListIndex(s);
        }
    }

    @Override
    public void setEmptyView(int size) {
//        if (size == 0) {
//            setTextEmptyView("暂无列表数据\n点击空白处重试");
//        } else {
//            layoutEmptyView.setVisibility(View.GONE);
//        }

    }

    @OnClick(R.id.layout_empty_view)
    public void onViewClicked() {
        layoutEmptyView.setVisibility(View.GONE);
//        mPresenter.getContactVersion();
    }

//    @OnClick(R.id.view_contact)
//    public void OnClick(){
//        mPresenter.onClick();
//
//    }


}
