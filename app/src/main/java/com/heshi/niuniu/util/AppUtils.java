package com.heshi.niuniu.util;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Locale;

/**
 * Created by min on 2016/8/18.
 */
public class AppUtils {
    public final static int DRAWABLE_LEFT = 0;
    public final static int DRAWABLE_RIGHT = 1;
    public final static int DRAWABLE_TOP = 2;
    public final static int DRAWABLE_BOTTOM = 3;


    public static String sHA1(Context context) {
        try {
            PackageInfo info = context.getPackageManager().getPackageInfo(
                    context.getPackageName(), PackageManager.GET_SIGNATURES);
            byte[] cert = info.signatures[0].toByteArray();
            MessageDigest md = MessageDigest.getInstance("SHA1");
            byte[] publicKey = md.digest(cert);
            StringBuffer hexString = new StringBuffer();
            for (int i = 0; i < publicKey.length; i++) {
                String appendString = Integer.toHexString(0xFF & publicKey[i])
                        .toUpperCase(Locale.US);
                if (appendString.length() == 1)
                    hexString.append("0");
                hexString.append(appendString);
                hexString.append(":");
            }
            String result = hexString.toString();
            return result.substring(0, result.length() - 1);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }


    public static String getApplicationMetadata(Context context, String metaDataKey) {
        ApplicationInfo info = null;
        try {
            PackageManager pm = context.getPackageManager();

            info = pm.getApplicationInfo(context.getPackageName(), PackageManager.GET_META_DATA);

            return String.valueOf(info.metaData.get(metaDataKey));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getPackageName(Context context) {
        return context.getPackageName();
    }

    /**
     * 判断当前系统是否支持沉浸模式
     *
     * @return
     */
    public static boolean IsSteepMode() {
        return Build.VERSION.SDK_INT >= 19;
    }

    /**
     * 当系统支持沉浸模式的时候将调用此代码进行默认的pading三个字
     *
     * @return
     */
    public static void SteepModeProssByPading(View view) {
        if (view != null) {
            int top = view.getPaddingTop() + getStatusHeight(view.getContext());
            view.setPadding(view.getPaddingLeft(), top, view.getPaddingRight(), view.getPaddingBottom());
        }
    }

    /**
     * 当系统支持沉浸模式的时候将调用此代码进行默认的pading三个字
     *
     * @return
     */
    public static void SteepModeProssByHeight(View view) {
        if (view != null) {
            int h = getViewWhiteAndHigth(view)[0] + getStatusHeight(view.getContext());
            setViewHeight(view, h);
        }
    }

    /**
     * 对view进行测量，让其能获取到宽高，并返回
     * int Arr[0] 宽 Arr[1]高
     *
     * @param view
     * @return
     */
    public static int[] getViewWhiteAndHigth(View view) {
        int[] result = new int[2];
        int width = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        int height = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        view.measure(width, height);
        result[1] = view.getMeasuredHeight();
        result[0] = view.getMeasuredWidth();
        return result;
    }

    /**
     * 设置view的高度
     *
     * @param view
     * @param height
     */
    public static void setViewHeight(View view, int height) {
        ViewGroup.LayoutParams lp = view.getLayoutParams();
        if (lp == null) {
            lp = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, height);
        } else {
            lp.height = height;
        }
        view.setLayoutParams(lp);
    }

    /**
     * 自动根据系统是否为沉浸模式，对view增加padingTop
     *
     * @return
     */
    public static void AutoSteepProssByPadding(View view) {
        if (IsSteepMode()) {
            SteepModeProssByPading(view);
        }
    }

    /**
     * 自动根据系统是否为沉浸模式，对view增加高度
     *
     * @return
     */
    public static void AutoSteepProssByHeight(View view) {
        if (IsSteepMode()) {
            SteepModeProssByHeight(view);
        }
    }


    /**
     * 获得状态栏的高度
     *
     * @param context
     * @return
     */
    public static int getStatusHeight(Context context) {

        int statusHeight = -1;
        try {
            Class clazz = Class.forName("com.android.internal.R$dimen");
            Object object = clazz.newInstance();
            int height = Integer.parseInt(clazz.getField("status_bar_height")
                    .get(object).toString());
            statusHeight = context.getResources().getDimensionPixelSize(height);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return statusHeight;
    }

    /**
     * 设置textview的 drawable
     *
     * @param context     contex
     * @param imgResource 图片
     * @param textView    textview
     * @param location    那个方向
     */
    public static void setDrawable(Context context, int imgResource, CharSequence content, TextView textView, int location) {
        //  使用textview设置drawableLeft
        Drawable drawable = ContextCompat.getDrawable(context, imgResource);
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        textView.setText(content);
        switch (location) {
            case DRAWABLE_LEFT:
                textView.setCompoundDrawables(drawable, null, null, null);
                break;
            case DRAWABLE_RIGHT:
                textView.setCompoundDrawables(null, null, drawable, null);
                break;
            case DRAWABLE_TOP:
                textView.setCompoundDrawables(null, drawable, null, null);
                break;
            case DRAWABLE_BOTTOM:
                textView.setCompoundDrawables(null, null, null, drawable);
                break;
        }
    }

    //版本名
    public static String getVersionName(Context context) {
        return getPackageInfo(context).versionName;
    }

    //版本号
    public static int getVersionCode(Context context) {
        return getPackageInfo(context).versionCode;
    }

    private static PackageInfo getPackageInfo(Context context) {
        PackageInfo pi = null;

        try {
            PackageManager pm = context.getPackageManager();
            pi = pm.getPackageInfo(context.getPackageName(),
                    PackageManager.GET_CONFIGURATIONS);

            return pi;
        } catch (Exception e) {
            e.printStackTrace();
        }

        return pi;
    }

    public static String getTinkerAppKey(Context mContext) {
        String data = null;
        try {
            ApplicationInfo ai = mContext.getPackageManager().getApplicationInfo(mContext.getPackageName(), PackageManager.GET_META_DATA);
            data = (String) ai.metaData.get("tinker_appKey");
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return data;
    }

    /**
     * 软键盘显示和隐藏
     *
     * @param context
     * @param view
     * @param status
     */
    public static void setSoftInputStatus(Context context, EditText view, boolean status) {
        view.requestFocus();

        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (status) {
            imm.showSoftInput(view, InputMethodManager.SHOW_FORCED);
        } else {
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0); //强制隐藏键盘
        }
    }

}
