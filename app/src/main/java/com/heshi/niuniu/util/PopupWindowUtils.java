package com.heshi.niuniu.util;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.WindowManager;
import android.widget.LinearLayout.LayoutParams;
import android.widget.PopupWindow;

public class PopupWindowUtils {

    private static PopupWindowUtils hUtils;
    private PopupWindow pWindow;
     static Context mContext;

    public static PopupWindowUtils getInstance(Context mContext) {
        if (hUtils == null) {
            hUtils = new PopupWindowUtils();
        }
        hUtils.mContext=mContext;
        return hUtils;
    }

//    /**
//     * 适当大小的弹窗
//     *
//     * @param context     上下文
//     * @param contentView 要显示的内容，一定要指定背景颜色，否则是透明的
//     * @param parent      要显示在的位置的参照
//     * @param w           弹窗的宽
//     * @param h           弹窗的高
//     * @param position    你要显示的位置，如：Gravity.BOTTOM 。。。。 如果在 parent 的下方，可以直接写0
//     * @return 放回一个PopupWindow
//     */
//    public PopupWindow showPopup(Context context, View contentView,
//                                 View parent, int w, int h, int position) {
//        pWindow = new PopupWindow(context);
//        pWindow.setWidth(w);
//        pWindow.setHeight(h);
//        pWindow.setContentView(contentView);
//        setPopup(parent, position, null, true);
//        return pWindow;
//
//    }

    /**
     * 包裹窗体的PopupWindow
     *
     * @param contentView 要显示的内容，一定要指定背景颜色，否则是透明的
     * @param parent      要显示在的位置的参照
     * @param position    你要显示的位置，如：Gravity.BOTTOM 。。。。 如果在parent 的下方，可以直接写0
     * @param model       弹窗的布局形式 ,model=1弹窗填充整个窗体, model=2弹窗的宽填充整个窗体 ,model=3弹窗的高填充整个窗体
     *                    ,model=4弹窗包裹内容
     * @return 返回一个PopupWindow
     */
    public PopupWindow showPopup(boolean isOutDimiss,View contentView,
                                 View parent, int position, int model) {
        pWindow = new PopupWindow(contentView);
//        pWindow.setAnimationStyle(animationStyle);
        switch (model) {
            case 1:
                pWindow.setWidth(LayoutParams.MATCH_PARENT);
                pWindow.setHeight(LayoutParams.MATCH_PARENT);
                break;

            case 2:
                pWindow.setWidth(LayoutParams.MATCH_PARENT);
                pWindow.setHeight(LayoutParams.WRAP_CONTENT);
                break;

            case 3:
                pWindow.setWidth(LayoutParams.WRAP_CONTENT);
                pWindow.setHeight(LayoutParams.MATCH_PARENT);
                break;
            case 4:
                pWindow.setWidth(LayoutParams.WRAP_CONTENT);
                pWindow.setHeight(LayoutParams.WRAP_CONTENT);
                break;

            default:
                break;
        }

        setPopup(parent, position, null, isOutDimiss);

        return pWindow;

    }

    /**
     * 包裹窗体的PopupWindow
     *
     * @param contentView 要显示的内容，一定要指定背景颜色，否则是透明的
     * @param parent      要显示在的位置的参照
     * @param position    你要显示的位置，如：Gravity.BOTTOM 。。。。 如果在parent 的下方，可以直接写0
     * @param model       弹窗的布局形式 ,model=1弹窗填充整个窗体, model=2弹窗的宽填充整个窗体 ,model=3弹窗的高填充整个窗体
     *                    ,model=4弹窗包裹内容
     * @return 返回一个PopupWindow
     */
    public PopupWindow showPopup(View contentView,
                                 View parent, int position, int model, int animationStyle) {
        pWindow = new PopupWindow(contentView);
        pWindow.setAnimationStyle(animationStyle);

        switch (model) {
            case 1:
                pWindow.setWidth(LayoutParams.MATCH_PARENT);
                pWindow.setHeight(LayoutParams.MATCH_PARENT);
                break;

            case 2:
                pWindow.setWidth(LayoutParams.MATCH_PARENT);
                pWindow.setHeight(LayoutParams.WRAP_CONTENT);
                break;

            case 3:
                pWindow.setWidth(LayoutParams.WRAP_CONTENT);
                pWindow.setHeight(LayoutParams.MATCH_PARENT);
                break;
            case 4:
                pWindow.setWidth(LayoutParams.WRAP_CONTENT);
                pWindow.setHeight(LayoutParams.WRAP_CONTENT);
                break;

            default:
                break;
        }
        setPopup(parent, position, null, true);
        return pWindow;

    }

    /**
     * 包裹窗体的PopupWindow
     *
     * @param contentView 要显示的内容，一定要指定背景颜色，否则是透明的
     * @param parent      要显示在的位置的参照
     * @param position    你要显示的位置，如：Gravity.BOTTOM 。。。。 如果在parent 的下方，可以直接写0
     * @param model       弹窗的布局形式 ,model=1弹窗填充整个窗体, model=2弹窗的宽填充整个窗体 ,model=3弹窗的高填充整个窗体
     *                    ,model=4弹窗包裹内容
     * @return 返回一个PopupWindow
     */
    public PopupWindow showPopup(View contentView,
                                 View parent, int position, int model, boolean needBackKey,int background) {
        Drawable bac=mContext.getDrawable(background);

        pWindow = new PopupWindow(contentView);
        switch (model) {
            case 1:
                pWindow.setWidth(LayoutParams.MATCH_PARENT);
                pWindow.setHeight(LayoutParams.MATCH_PARENT);
                break;

            case 2:
                pWindow.setWidth(LayoutParams.MATCH_PARENT);
                pWindow.setHeight(LayoutParams.WRAP_CONTENT);

                break;

            case 3:
                pWindow.setWidth(LayoutParams.WRAP_CONTENT);
                pWindow.setHeight(LayoutParams.MATCH_PARENT);
                break;
            case 4:
                pWindow.setWidth(LayoutParams.WRAP_CONTENT);
                pWindow.setHeight(LayoutParams.WRAP_CONTENT);
                break;

            default:
                break;
        }

        setPopup(parent, position, bac, needBackKey);

        return pWindow;

    }

    private void setPopup(View parent, int position,
                          Drawable background, boolean needBackKey) {

        //popwindow点击外面可以dimiss必须放在showAtLocation和showAsDropDown之前 setFocusable为false不能点击消失
        // setOutsideTouchable只不过是一个附带品
//        pWindow.setBackgroundDrawable(background);

        pWindow.update();

        pWindow.setFocusable(true);
        pWindow.setOutsideTouchable(true);

        pWindow.update();
        // pWindow.setContentView(contentView);

//        pWindow.setInputMethodMode(PopupWindow.INPUT_METHOD_NEEDED);
//        pWindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);

//        if (needBackKey) {
//
//            if (null == background) {
//                // 透明背景
//				pWindow.setBackgroundDrawable(context.getResources()
//						.getDrawable(R.drawable.mysetting_popup_transparent));
//            } else {
//                pWindow.setBackgroundDrawable(background);
//            }
//
//        }


//        pWindow.setBackgroundDrawable(new BitmapDrawable());

        if (pWindow.isShowing()) {
            pWindow.dismiss();
        } else {
            if (position == 0) {
                pWindow.showAsDropDown(parent);
            } else {
                pWindow.showAtLocation(parent, position, 0, 0);
            }

        }

        pWindow.update();

    }


    /**
     * 切换弹窗的状态
     *
     * @param pw 你要切换的PopupWindow
     */
    public void togglePopup(PopupWindow pw) {
        if (pw.isShowing())
            pw.dismiss();
    }

    public static float NOT_ALPHA = 1;

    /**
     * 设置屏幕的亮度
     *
     * @param activity
     * @param alphaValue 透明值
     */
    public void changeBackgroundAlpha(Activity activity, float alphaValue,
                                      int multiple) {

        float floatAlpha = 0;
        int intAlpha = 0;
        alphaValue = multiple * Math.abs(alphaValue);

        WindowManager.LayoutParams attributes = activity.getWindow()
                .getAttributes();

        if (alphaValue < multiple * NOT_ALPHA) {

            intAlpha = (int) alphaValue;

            for (int i = multiple; i >= intAlpha; i--) {

                floatAlpha = (float) i / multiple;
                attributes.alpha = floatAlpha;
                activity.getWindow().setAttributes(attributes);
            }

        } else {

            throw new RuntimeException(
                    "changeBackgroundAlpha(),lastAlphaValue mast be < 1");

        }
    }

    /**
     * 恢复 屏幕 亮度
     *
     * @param activity
     * @param lastAlphaValue 现在屏幕的暗度 值
     * @param multiple       平滑恢复的倍数值
     */
    public void renewBackgroundAlpha(Activity activity, float lastAlphaValue,
                                     int multiple) {

        float floatAlpha = 0;
        int intAlpha = 0;
        lastAlphaValue = multiple * Math.abs(lastAlphaValue);

        WindowManager.LayoutParams attributes = activity.getWindow()
                .getAttributes();

        if (lastAlphaValue < multiple * NOT_ALPHA) {

            intAlpha = (int) lastAlphaValue;

            for (int i = intAlpha; i <= multiple; i++) {

                floatAlpha = (float) i / multiple;
                attributes.alpha = floatAlpha;
                activity.getWindow().setAttributes(attributes);
            }

        } else {

            throw new RuntimeException(
                    "changeBackgroundAlpha(),lastAlphaValue mast be < 1");

        }

    }

}
