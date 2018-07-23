package com.heshi.niuniu.util;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ScrollView;

/**
 * Author : Haily
 * Date   : 2018/1/1710:15
 * Email  : zhanghailei55@gmail.com
 * Desc   :
 * Comment: //TODO
 */

public class MyScrollView extends ScrollView {
    private View             contentView;  //ScrollView包含的子组件
    private OnBorderListener onBorderListener;

    public MyScrollView(Context context) {
        super(context);
    }

    public MyScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyScrollView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    /* 在滑动发生时监听滑动靠岸 */
    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        doOnBorderListener();
    }


    private OnTouchListener onBorderTouchListener;


    public void setOnBorderListener(final OnBorderListener onBorderListener) {
        if (onBorderListener == null) {
            onBorderTouchListener = null;
            return;
        } else {
            this.onBorderListener = onBorderListener;
        }

        if (contentView == null) {
            contentView = getChildAt(0);  //因为ScrollView只能包含一个子组件，因此可以直接通过索引‘0’获取子组件
        }

        /* 其实这是另一种处理监听滑动靠岸的方法，是在touch事件发生时进行滑动靠岸监听 */
        onBorderTouchListener = new OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                switch (event.getAction()) {
                    //这里面试了几个动作，最终正明MOVE动作的效果是最好，最及时的
                    case MotionEvent.ACTION_MOVE:
                        doOnBorderListener();
                        break;
                }
                return false;
            }

        };
        super.setOnTouchListener(onBorderTouchListener);
    }

    /**
     * 重写setOnTouchListener方法，防止在MyScrollView实例中调用该方法的时候，覆盖掉我们上面做的处理
     */
    @Override
    public void setOnTouchListener(final OnTouchListener l) {
        OnTouchListener onTouchListener = new OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {

                if (onBorderTouchListener != null) {
                    onBorderTouchListener.onTouch(v, event);
                }
                return l.onTouch(v, event);
            }

        };
        super.setOnTouchListener(onTouchListener);
    }

    private void doOnBorderListener() {
        if (contentView != null && contentView.getMeasuredHeight() <= getScrollY() + getHeight()) {
            if (onBorderListener != null) {
                onBorderListener.onBottom();
            }
        } else if (getScrollY() == 0) {
            if (onBorderListener != null) {
                onBorderListener.onTop();
            }
        } else { //没靠岸的时候调用onMiddle方法
            if (onBorderListener != null) {
                onBorderListener.onMiddle();
            }
        }
    }

    //在接口中加一个onMiddle方法，来处理没靠岸的情况
    public static interface OnBorderListener {
        /**
         * Called when scroll to bottom
         */
        public void onBottom();

        /**
         * Called when scroll to top
         */
        public void onTop();

        /**
         * Called when scroll in middle
         */
        public void onMiddle();
    }

}
