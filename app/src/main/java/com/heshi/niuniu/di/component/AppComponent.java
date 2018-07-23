package com.heshi.niuniu.di.component;



import com.heshi.niuniu.base.MyApplication;
import com.heshi.niuniu.di.ContextLife;
import com.heshi.niuniu.di.module.AppModule;

import javax.inject.Singleton;

import dagger.Component;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

/**
 * Created by min on 2017/3/1.
 */

@Singleton
@Component(modules = AppModule.class)
public interface AppComponent {

    @ContextLife("Application")
    MyApplication getContext();

    Retrofit getRetrofit();

    OkHttpClient getOkHttpClient();

}