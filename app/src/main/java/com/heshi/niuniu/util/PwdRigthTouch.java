package com.heshi.niuniu.util;

import android.graphics.drawable.Drawable;
import android.text.InputType;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;

import java.lang.ref.SoftReference;

/**
 * 通用的密码编辑框眼睛的显示与隐藏
 * Created by Min on 2017/3/26.
 */

public class PwdRigthTouch implements View.OnTouchListener {
    private SoftReference<EditText> mView;
    private int mVisibilityRes;
    private int mInVisibilityRes;
    private boolean isVisible;

    public PwdRigthTouch(EditText view, int visibilityRes, int inVisibilityRes) {
        if (visibilityRes <= 0 || inVisibilityRes <= 0) {
            throw new RuntimeException("visibilityRes or inVisibilityRes is not found ResFile");
        }
        this.mView = new SoftReference<EditText>(view);
        this.mInVisibilityRes = inVisibilityRes;
        this.mVisibilityRes = visibilityRes;
        isVisible = false;
    }

    private EditText $() {
        return mView.get();
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        if ($() == null) {
            return false;
        }
        // et.getCompoundDrawables()得到一个长度为4的数组，分别表示左上右下四张图片
        Drawable drawable = $().getCompoundDrawables()[2];
        //如果右边没有图片，不再处理
        if (drawable == null)
            return false;
        //如果不是按下事件，不再处理
        if (event.getAction() != MotionEvent.ACTION_UP)
            return false;
        //按下时将右边图片设置为可见


        if (event.getX() > $().getWidth()
                - $().getPaddingRight()
                - drawable.getIntrinsicWidth()) {
            Drawable mVisible = $().getResources().getDrawable(this.mVisibilityRes);
            Drawable mUnVisible = $().getResources().getDrawable(this.mInVisibilityRes);
            if (!isVisible) {
                mVisible.setBounds(0, 0, mVisible.getMinimumWidth(), mVisible.getMinimumHeight());

                //显示密码
                $().setInputType(InputType.TYPE_CLASS_TEXT);
                isVisible = true;
                $().setSelection($().length());
                drawable = mVisible;
            } else {
                mUnVisible.setBounds(0, 0, mUnVisible.getMinimumWidth(), mUnVisible.getMinimumHeight());
                //隐藏 密码
                $().setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
                isVisible = false;
                $().setSelection($().length());
                drawable = mUnVisible;
            }
            $().setCompoundDrawables($().getCompoundDrawables()[0],
                    $().getCompoundDrawables()[1], drawable, $().getCompoundDrawables()[3]);
        }
        return false;
    }
}
