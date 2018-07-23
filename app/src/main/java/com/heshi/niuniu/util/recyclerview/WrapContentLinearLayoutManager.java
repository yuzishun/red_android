package com.heshi.niuniu.util.recyclerview;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

/**
 * Name: WrapContentLinearLayoutManager
 * Author: FrameJack
 * Email: framejackname@gmail.com
 * Date: 2018-01-19 14:08
 * Desc:
 * Comment: //TODO
 */
public class WrapContentLinearLayoutManager extends LinearLayoutManager {


    public WrapContentLinearLayoutManager(Context context) {
        super(context);
    }

    public WrapContentLinearLayoutManager(Context context, int orientation, boolean reverseLayout) {
        super(context, orientation, reverseLayout);
    }

    public WrapContentLinearLayoutManager(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
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
