package com.heshi.niuniu.im.activity.chat;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.alibaba.mobileim.kit.chat.ChattingFragment;
import com.heshi.niuniu.R;
import com.heshi.niuniu.base.BaseActivity;
import com.heshi.niuniu.di.component.AppComponent;
import com.heshi.niuniu.di.module.ActivityModule;
import com.heshi.niuniu.im.sample.LoginSampleHelper;

/**
 * Created by Administrator on 2018/7/4 0004.
 */

public class ChatActivity extends BaseActivity<ChatPresent> implements ChatContract.Model {
    private static final String TAG = "ChattingActivity";
    public static final String TARGET_ID = "targetId";

    private Fragment mCurrentFrontFragment;


    @Override
    protected void setupActivityComponent(AppComponent appComponent, ActivityModule activityModule) {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_chat;
    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
        String targetId = getIntent().getStringExtra(TARGET_ID);
//        ImmersionBar.with(this).init();   //所有子类都将继承这些相同的属性
//        ImmersionBar.with(this)
//                .keyboardEnable(true)  //解决软键盘与底部输入框冲突问题
//                //  .keyboardEnable(true, WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE
//                //                        | WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE)  //软键盘自动弹出
//                .init();

        createFragment();
    }

    private void createFragment() {
        Intent intent = getIntent();
        String targetId = intent.getStringExtra(TARGET_ID);
        mCurrentFrontFragment = LoginSampleHelper.getInstance().getIMKit().getChattingFragment(targetId);
        getSupportFragmentManager().beginTransaction().replace(R.id.wx_chat_container, mCurrentFrontFragment).commit();

    }


    /**
     * 必须实现该方法，且该方法的实现必须跟以下示例代码完全一致！
     * todo 因为拍照和选择照片的时候会回调该方法，如果没有按以下方式覆写该方法会导致拍照和选择照片时应用crash或拍照和选择照片无效!
     *
     * @param arg0
     * @param arg1
     * @param arg2
     */
    @Override
    protected void onActivityResult(int arg0, int arg1, Intent arg2) {
        super.onActivityResult(arg0, arg1, arg2);
        if (mCurrentFrontFragment != null) {
            mCurrentFrontFragment.onActivityResult(arg0, arg1, arg2);
        }
    }

    /**
     * 必须实现该方法，且该方法的实现必须跟以下示例代码完全一致！
     */
    @Override
    public void onBackPressed() {

        if (mCurrentFrontFragment != null && mCurrentFrontFragment.isVisible()) {

            if (mCurrentFrontFragment instanceof ChattingFragment && ((ChattingFragment) mCurrentFrontFragment).onBackPressed()) {
                return;
            }
        }

        super.onBackPressed();
    }
}
