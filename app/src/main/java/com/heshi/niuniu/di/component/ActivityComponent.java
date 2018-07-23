package com.heshi.niuniu.di.component;

import android.app.Activity;


import com.heshi.niuniu.di.PerActivity;
import com.heshi.niuniu.di.module.ActivityModule;
import com.heshi.niuniu.im.activity.chat.ChatActivity;
import com.heshi.niuniu.ui.login.LoginActivity;
import com.heshi.niuniu.ui.main.MainActivity;
import com.heshi.niuniu.ui.main.connect.ConnectAddFriendActivity;
import com.heshi.niuniu.ui.main.connect.friend_info.FriendInfoActivity;
import com.heshi.niuniu.ui.main.connect.friend_remark.FriendInfoMarkActivity;
import com.heshi.niuniu.ui.main.connect.new_friend.NewFriendActivity;
import com.heshi.niuniu.ui.main.connect.search.ConnectSearchActivity;
import com.heshi.niuniu.ui.main.dynamic.PublishDynamicActivity;
import com.heshi.niuniu.ui.mine.change_photo.ChangePhotoActivity;
import com.heshi.niuniu.ui.mine.info.PersonInfoActivity;
import com.heshi.niuniu.ui.mine.qr.QrCodeActivity;
import com.heshi.niuniu.ui.mine.qr.scan.ScanningActivity;
import com.heshi.niuniu.ui.password.commit_pass.CommitPassActivity;
import com.heshi.niuniu.ui.password.forget.ForgetPassActivity;
import com.heshi.niuniu.ui.password.register.RegisterActivity;
import com.heshi.niuniu.ui.webview.XWebViewActivity;

import dagger.Component;


/**
 * Created by min on 2017/3/2.
 * Activity 注入
 */

@PerActivity
@Component(dependencies = AppComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {
    Activity getActivity();

    // 首页
    void inject(MainActivity mainActivity);

    //登录
    void inject(LoginActivity loginActivity);

    //忘记密码
    void inject(ForgetPassActivity forgetPassActivity);

    //注册
    void inject(RegisterActivity registerActivity);

    //重置密码
    void inject(CommitPassActivity commitPassActivity);

    //webView
    void inject(XWebViewActivity xWebViewActivity);

    //webView
    void inject(ChatActivity chatActivity);

    //用户信息
    void inject(PersonInfoActivity personInfoActivity);

    //更改头像
    void inject(ChangePhotoActivity changePhotoActivity);

    //搜朋友
    void inject(ConnectAddFriendActivity connectAddFriendActivity);

    //搜索新朋友
    void inject(ConnectSearchActivity connectSearchActivity);

    //添加好友信息
    void inject(FriendInfoActivity friendInfoActivity);

    //设置备注
    void inject(FriendInfoMarkActivity friendInfoMarkActivity);

    //二维码生成
    void inject(QrCodeActivity qrCodeActivity);

    //二维码扫描
    void inject(ScanningActivity scanningActivity);

    //新朋友
    void inject(NewFriendActivity newFriendActivity);

    //发布动态
    void inject(PublishDynamicActivity publishDynamicActivity);


}