package com.heshi.niuniu.di.module;

import android.app.Activity;


import com.heshi.niuniu.di.PerActivity;

import dagger.Module;
import dagger.Provides;

/**
 * Created by wukewei on 16/7/19.
 */
@Module
public class ActivityModule {
    private final Activity mActivity;

    public ActivityModule(Activity activity) {
        this.mActivity = activity;
    }

    @Provides
    @PerActivity
    public Activity provideActivity() {
        return mActivity;
    }
}
