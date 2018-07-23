package com.heshi.niuniu.im.activity.chat;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;

import com.heshi.niuniu.adapter.BaseFragmentAdapter;
import com.heshi.niuniu.present.BasePresenter;

import java.util.List;

import javax.inject.Inject;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

/**
 * Created by Administrator on 2018/7/4 0004.
 */

public class ChatPresent extends BasePresenter<ChatContract.Model>implements ChatContract.Presenter {

    @Inject
    public ChatPresent(Activity activity, OkHttpClient okHttpClient, Retrofit retrofit) {
        super(activity, okHttpClient, retrofit);
    }


    @Override
    public void initAdapter(FragmentManager fm, List<Fragment> fragmentList,
                            List<String> strList, ViewPager viewPager) {

        BaseFragmentAdapter adapter = new BaseFragmentAdapter(fm, strList, fragmentList);
        viewPager.setAdapter(adapter);

    }

}
