package com.heshi.niuniu.im.activity.chat;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;

import com.heshi.niuniu.present.IPresenter;
import com.heshi.niuniu.present.MModel;

import java.util.List;

/**
 * Created by Administrator on 2018/7/4 0004.
 */

public class ChatContract {

    interface Model extends MModel {

    }

    interface Presenter extends IPresenter<Model> {

        void initAdapter(FragmentManager fm, List<Fragment> fragmentList,
                         List<String> strList, ViewPager viewPager);

    }

}
