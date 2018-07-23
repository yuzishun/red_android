package com.heshi.niuniu.util;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;

/**
 * Created by Administrator on 2017/5/9 0009.
 */

public class AnimationUtils {
    public static void setRotateAnimitation(View view, float start, float end, int druation, boolean isFillAfter) {
        RotateAnimation animation = new RotateAnimation(start, end, Animation.RELATIVE_TO_SELF,
                0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        animation.setDuration(druation);//设置动画持续时间
        animation.setFillAfter(isFillAfter);//动画执行完后是否停留在执行完的状态
        view.startAnimation(animation);
    }
}
