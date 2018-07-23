package com.heshi.niuniu.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by min on 2017/6/21
 */
public class BaseFragmentAdapter extends FragmentPagerAdapter {
    //    private static final int ITEM_NUM = 4;
    private List<String>   mTitles;
    private List<Fragment> fragments;
    private Context        mContext;

    public BaseFragmentAdapter(FragmentManager fm, List<String> mTitles, List<Fragment> fragments) {
        super(fm);
        this.mTitles = mTitles;
        this.fragments = fragments;
    }

    public BaseFragmentAdapter(FragmentManager fm, List<String> mTitles, List<Fragment> fragments, Context mContext) {
        super(fm);
        this.mTitles = mTitles;
        this.fragments = fragments;
        this.mContext = mContext;
    }

    @Override
    public Fragment getItem(int position) {

        return fragments.get(position);

    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mTitles.get(position);
    }

}
