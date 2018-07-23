package com.heshi.niuniu.util.dialogutil;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;


import com.heshi.niuniu.R;
import com.heshi.niuniu.base.MyApplication;
import com.heshi.niuniu.di.component.AppComponent;
import com.heshi.niuniu.di.module.FragmentModule;
import com.heshi.niuniu.present.IPresenter;
import com.heshi.niuniu.present.MModel;
import com.heshi.niuniu.util.UiUtils;

import javax.inject.Inject;

import butterknife.ButterKnife;


/**
 * Name: BaseDialogFragment
 * Author: FrameJack
 * Email: framejackname@gmail.com
 * Date: 2017-07-31 10:40
 * Desc: 记住一点要Dialog要显示位置和大小一定要在onStart写
 * Comment: //TODO
 */
public abstract class BaseDialogFragment<T extends IPresenter> extends DialogFragment implements MModel {
    protected Context mContext;
    @Inject
    protected T mPresenter;
    protected View view;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NORMAL, R.style.TranslucentNoTitle);//设置透明
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(getLayoutId(), container, false);
        ButterKnife.bind(this, view);
        setupActivityComponent(MyApplication.getAppComponent(), new FragmentModule(this));
        if (mPresenter!=null){
            mPresenter.attachView(this);
        }
        mContext=getActivity();
        initData();
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        getDefaultOption();
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

    protected void initData() {

    }

    /**
     * 默认配置底部弹出
     */
    protected void getDefaultOption() {
        //设置全屏和底部显示
        Window window = getDialog().getWindow();

        WindowManager.LayoutParams p = window.getAttributes();
        p.width = ViewGroup.LayoutParams.MATCH_PARENT;
        p.height = ViewGroup.LayoutParams.WRAP_CONTENT;//高度自己设定
        window.setAttributes(p);
        window.setGravity(Gravity.BOTTOM);

        //在Java代码中设置窗口动画
        getDialog().getWindow().getAttributes().windowAnimations = R.style.CustomDialog;

        getDialog().setCanceledOnTouchOutside(true);

    }

    /**
     * 设置宽，高，弹出位置
     *
     * @param parameter
     */
    protected void setOption(DialogSetParameter parameter) {
        Window window = getDialog().getWindow();
        WindowManager.LayoutParams p = window.getAttributes();
        p.width = parameter.getWidth() != 0 ? UiUtils.dp2px(parameter.getWidth(), getActivity()) : ViewGroup.LayoutParams.MATCH_PARENT;
        p.height = parameter.getHeight() != 0 ? UiUtils.dp2px(parameter.getHeight(), getActivity()) : ViewGroup.LayoutParams.WRAP_CONTENT;//高度自己设定
        window.setAttributes(p);
        window.setGravity(parameter.getCurPosition() != 0 ? parameter.getCurPosition() : Gravity.BOTTOM);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) mPresenter.detachView();

    }

    public void dismissDialog(View view){
        this.dismiss();
    }
}
