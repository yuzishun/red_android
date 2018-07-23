package com.heshi.niuniu.util.recyclerview.manager;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;

/**
 * Created by FrameJack on 2017/7/12.
 * RecyclerView 垂直滚动 禁止滚动
 */

public class ScrollLinearLayoutManager extends LinearLayoutManager {
    public ScrollLinearLayoutManager(Context context) {
        super(context);
    }

    public ScrollLinearLayoutManager(Context context, int orientation, boolean reverseLayout) {
        super(context, orientation, reverseLayout);
    }

    @Override
    public boolean canScrollVertically() {
        return false;
    }

}
