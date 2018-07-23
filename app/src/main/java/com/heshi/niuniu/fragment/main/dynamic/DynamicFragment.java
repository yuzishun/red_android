package com.heshi.niuniu.fragment.main.dynamic;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.heshi.niuniu.R;
import com.heshi.niuniu.app.Constants;
import com.heshi.niuniu.base.BaseFragment;
import com.heshi.niuniu.di.component.AppComponent;
import com.heshi.niuniu.di.component.DaggerFragmentComponent;
import com.heshi.niuniu.di.module.FragmentModule;
import com.heshi.niuniu.eventbus.DynamicEvent;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrFrameLayout;

/**
 * Created by Administrator on 2018/6/29 0029.
 */

public class DynamicFragment extends BaseFragment<DynamicPresent>
        implements DynamicContract.Model {


    @BindView(R.id.rec_dynamic_list)
    RecyclerView recDynamicList;
    @BindView(R.id.ptr_dynamic)
    PtrClassicFrameLayout ptrDynamic;
    private int page;

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
        return R.layout.fragment_home_dynamic;
    }

    @Override
    protected void initData() {
        super.initData();
        EventBus.getDefault().register(this);

        mPresenter.dialog.show();
        initRefresh(ptrDynamic, recDynamicList);

        mPresenter.initAdapter(recDynamicList);
        mPresenter.getDynamicList(Constants.userName, page);
        mPresenter.getDynamicInfo(Constants.userName);
        mPresenter.showPop();

    }

    /**
     * 更新
     */
    @Override
    protected void updateData() {
        page = 1;
        mPresenter.getDynamicList(Constants.userName, page);

    }

    /**
     * 加载更多
     */
    @Override
    protected void loadMoreData() {
        page++;
        mPresenter.getDynamicList(Constants.userName, page);

    }

    @Override
    public void complete() {
        if (ptrDynamic != null)
            ptrDynamic.refreshComplete();
    }

    @Override
    public void close() {
        if (ptrDynamic != null) {
            ptrDynamic.setMode(PtrFrameLayout.Mode.REFRESH);
        }
    }

    @Override
    public void noLoadMode() {
        if (ptrDynamic != null) {
            ptrDynamic.setMode(PtrFrameLayout.Mode.REFRESH);
        }
    }

    @Override
    public void defaultMode() {
        if (ptrDynamic != null) {
            ptrDynamic.setMode(PtrFrameLayout.Mode.BOTH);
        }
    }

    @Subscribe
    public void getEvent(DynamicEvent event) {
        page = 0;
        mPresenter.getDynamicList(Constants.userName, page);

    }

}
