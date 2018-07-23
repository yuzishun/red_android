package com.heshi.niuniu.ui.main;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.util.Log;
import android.widget.BaseAdapter;

import com.alibaba.mobileim.channel.event.IWxCallback;
import com.heshi.niuniu.adapter.BaseFragmentAdapter;
import com.heshi.niuniu.app.Constants;
import com.heshi.niuniu.fragment.main.mine.MineApi;
import com.heshi.niuniu.im.common.Constant;
import com.heshi.niuniu.im.sample.LoginSampleHelper;
import com.heshi.niuniu.model.ImModel;
import com.heshi.niuniu.present.BasePresenter;
import com.heshi.niuniu.rx.data.RxResultHelper;
import com.heshi.niuniu.rx.data.SchedulersCompat;

import java.util.List;

import javax.inject.Inject;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Subscriber;

/**
 * Created by Administrator on 2018/6/28 0028.
 */

public class MainPresent extends BasePresenter<MainContract.Model> implements MainContract.Presenter {

    private MainApi api;
    private LoginSampleHelper loginHelper;


    @Inject
    public MainPresent(Activity activity, OkHttpClient okHttpClient, Retrofit retrofit) {
        super(activity, okHttpClient, retrofit);
        Retrofit retrofit1 = new Retrofit.Builder()
                .baseUrl(Constants.Im_Url)
                .client(okHttpClient)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        api = retrofit1.create(MainApi.class);

    }


    @Override
    public void initAdapter(FragmentManager fm, List<Fragment> fragmentList,
                            List<String> strList, ViewPager viewPager) {

        BaseFragmentAdapter adapter = new BaseFragmentAdapter(fm, strList, fragmentList);
        viewPager.setAdapter(adapter);

    }





}
