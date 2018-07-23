package com.heshi.niuniu.di.component;

import android.app.Activity;

import com.heshi.niuniu.di.PerFragment;
import com.heshi.niuniu.di.module.FragmentModule;
import com.heshi.niuniu.fragment.main.connect.ConnectingFragment;
import com.heshi.niuniu.fragment.main.dynamic.DynamicFragment;
import com.heshi.niuniu.fragment.main.mine.MineFragment;
import com.heshi.niuniu.fragment.main.msg.MsgFragment;
import com.heshi.niuniu.fragment.main.weibo.WeiBoFragment;

import java.text.MessageFormat;

import dagger.Component;

/**
 * Created by min on 2017/4/12.
 * Fragment 注入
 */
@PerFragment
@Component(dependencies = AppComponent.class, modules = FragmentModule.class)
public interface FragmentComponent {
    Activity getActivity();

    //消息
    void inject(MsgFragment msgFragment);

    //人脉
    void inject(ConnectingFragment fragment);

    //动态
    void inject(DynamicFragment dynamicFragment);

    //微博
    void inject(WeiBoFragment weiBoFragment);

    //动态
    void inject(MineFragment mineFragment);

}
