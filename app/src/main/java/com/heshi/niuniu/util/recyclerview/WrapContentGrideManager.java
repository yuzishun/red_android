package com.heshi.niuniu.util.recyclerview;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

/**
 * Name: WrapContentGrideManager
 * Author: FrameJack
 * Email: framejackname@gmail.com
 * Date: 2018-01-19 14:10
 * Desc:
 * Comment: //TODO
 */
public class WrapContentGrideManager extends GridLayoutManager {


    public WrapContentGrideManager(Context context, int spanCount) {
        super(context, spanCount);
    }

    public WrapContentGrideManager(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public WrapContentGrideManager(Context context, int spanCount, int orientation, boolean reverseLayout) {
        super(context, spanCount, orientation, reverseLayout);
    }

    @Override
    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
        //这里加个异常处理 ，经测试可以避免闪退，具体副作用暂时还没有发现，可以作为临时解决方案
        try {
            super.onLayoutChildren(recycler, state);
        } catch (IndexOutOfBoundsException e) {
            e.printStackTrace();
        }
    }
}
