package com.heshi.niuniu.util;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.CardView;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;

import java.lang.reflect.Field;
import java.util.Calendar;

import static com.heshi.niuniu.util.DisplayUtil.dip2px;

/**
 * Created by zhengping on 2017/4/2,10:27.
 * 处理和ui操作相关的工具方法
 * 分层思想
 */

public class UiUtils {

    //获取字符串资源
    public static String getString(int resId, Context context) {
        return context.getResources().getString(resId);
    }

    //获取字符串数组
    public static String[] getStringArray(int resId, Context context) {
        return context.getResources().getStringArray(resId);
    }

    //获取Drawable
    public static Drawable getDrawable(int resId, Context context) {
        return context.getResources().getDrawable(resId);
    }

    //获取color
    public static int getColor(int resId, Context context) {
        return context.getResources().getColor(resId);
    }


    public static ColorStateList getColorStateList(int resId, Context context) {
        return context.getResources().getColorStateList(resId);
    }

    public static int getDimen(int resId, Context context) {
        return context.getResources().getDimensionPixelSize(resId);
    }

    //dp-->px
    public static int dp2px(int dp, Context context) {
        //  dp  * 设备密度 = px
        float density = context.getResources().getDisplayMetrics().density;//屏幕密度
        // 1920*1080     ppi  440
        return (int) (dp * density + 0.5f);
    }

    //px -- > dp
    public static int px2dp(int px, Context context) {
        //  dp  * 设备密度 = px
        // px  / 设备密度   = dp
        float density = context.getResources().getDisplayMetrics().density;//屏幕密度
        // 1920*1080     ppi  440
        return (int) (px / density + 0.5f);
    }

    /**
     * 获取年份
     */
    public static int getCurrentYear() {
        Calendar calendar = Calendar.getInstance();
        return calendar.get(Calendar.YEAR);
    }

    /**
     * 验证手机格式
     */
    public static boolean isMobile(String number) {
    /*
    移动：134、135、136、137、138、139、150、151、157(TD)、158、159、187、188
    联通：130、131、132、152、155、156、185、186
    电信：133、153、180、189、（1349卫通）
    总结起来就是第一位必定为1，第二位必定为3或5或8，其他位置的可以为0-9
    */
        String num = "[1][3578]\\d{9}";//"[1]"代表第1位为数字1，"[358]"代表第二位可以为3、5、8中的一个，"\\d{9}"代表后面是可以是0～9的数字，有9位。
        if (TextUtils.isEmpty(number)) {
            return false;
        } else {
            //matches():字符串是否在给定的正则表达式匹配
            return number.matches(num);
        }
    }

    public static void reflex(final TabLayout tabLayout, final int dp) {
        //了解源码得知 线的宽度是根据 tabView的宽度来设置的
        tabLayout.post(new Runnable() {
            @Override
            public void run() {
                try {
                    //拿到tabLayout的mTabStrip属性
                    LinearLayout mTabStrip = (LinearLayout) tabLayout.getChildAt(0);

                    int dp10 = dip2px(tabLayout.getContext(), dp);

                    for (int i = 0; i < mTabStrip.getChildCount(); i++) {
                        View tabView = mTabStrip.getChildAt(i);

                        //拿到tabView的mTextView属性  tab的字数不固定一定用反射取mTextView
                        Field mTextViewField = tabView.getClass().getDeclaredField("mTextView");
                        mTextViewField.setAccessible(true);

                        TextView mTextView = (TextView) mTextViewField.get(tabView);

                        tabView.setPadding(0, 0, 0, 0);

                        //因为我想要的效果是   字多宽线就多宽，所以测量mTextView的宽度
                        int width = 0;
                        width = mTextView.getWidth();
                        if (width == 0) {
                            mTextView.measure(0, 0);
                            width = mTextView.getMeasuredWidth();
                        }

                        //设置tab左右间距为10dp  注意这里不能使用Padding 因为源码中线的宽度是根据 tabView的宽度来设置的
                        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) tabView.getLayoutParams();
                        params.width = width;
                        params.leftMargin = dp10;
                        params.rightMargin = dp10;
                        tabView.setLayoutParams(params);

                        tabView.invalidate();
                    }

                } catch (NoSuchFieldException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        });

    }

    public static int getScreenWidth(Context context) {
        WindowManager wm = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        return outMetrics.widthPixels;
    }

    /**
     * 获得屏幕高度
     * @param context
     * @return
     */
    public static int getScreenHeight(Context context) {
        WindowManager wm = (WindowManager) context
                .getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics outMetrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(outMetrics);
        return outMetrics.heightPixels;
    }

    private static final String TAG = "UiUtils";

    /**
     * 让子控件不可编辑
     * @param viewGroup
     */
    public static void disableSubControls(ViewGroup viewGroup, boolean isClick) {
        for (int i = 0; i < viewGroup.getChildCount(); i++) {
            View v = viewGroup.getChildAt(i);
            if (v instanceof ViewGroup) {
                if (v instanceof Spinner) {
                    Spinner spinner = (Spinner) v;
                    spinner.setClickable(isClick);
                    spinner.setEnabled(isClick);

                    Log.i(TAG, "A Spinner is unabled");
                } /*else if (v instanceof RecyclerView) {
                    ((RecyclerView) v).setClickable(isClick);
                    ((RecyclerView) v).setEnabled(isClick);
                    disableSubControls((ViewGroup) v, isClick);
                    Log.i(TAG, "A ListView is unabled");
                } */ else if (v instanceof RelativeLayout) {
                    ((RelativeLayout) v).setClickable(true);
                    ((RelativeLayout) v).setEnabled(true);
                    disableSubControls((ViewGroup) v, isClick);
                    Log.i(TAG, "A ListView is unabled");
                } else if (v instanceof LinearLayout) {
                    ((LinearLayout) v).setClickable(isClick);
                    ((LinearLayout) v).setEnabled(isClick);
                    disableSubControls((ViewGroup) v, isClick);
                    Log.i(TAG, "A LinearLayout is unabled");
                } else if (v instanceof ScrollView) {
                    ((ScrollView) v).setClickable(isClick);
                    ((ScrollView) v).setEnabled(isClick);
                    disableSubControls((ViewGroup) v, isClick);
                    Log.i(TAG, "A ListView is unabled");
                } else if (v instanceof CardView) {
                    ((CardView) v).setClickable(isClick);
                    ((CardView) v).setEnabled(isClick);
                    disableSubControls((ViewGroup) v, isClick);
                    Log.i(TAG, "A ListView is unabled");
                } else {
                    disableSubControls((ViewGroup) v, isClick);
                }
            } else if (v instanceof EditText) {
                ((EditText) v).setEnabled(isClick);
                ((EditText) v).setClickable(isClick);

                Log.i(TAG, "A EditText is unabled");
            } else if (v instanceof Button) {
                ((Button) v).setEnabled(isClick);
                ((Button) v).setEnabled(isClick);
                Log.i(TAG, "A Button is unabled");
            }/* else if (v instanceof ImageView) {
                ((ImageView) v).setEnabled(isClick);
                ((ImageView) v).setClickable(isClick);
                Log.i(TAG, "A Button is unabled");
            }*/ else if (v instanceof TextView) {
                ((TextView) v).setEnabled(isClick);
                ((TextView) v).setClickable(isClick);
                Log.i(TAG, "A Button is unabled");
            }
        }
    }
}
