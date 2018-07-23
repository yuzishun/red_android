package com.heshi.niuniu.util;

import android.app.Activity;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.telephony.PhoneNumberUtils;
import android.widget.Toast;

import com.heshi.niuniu.R;
import com.rey.material.app.Dialog;

import java.text.MessageFormat;

/**
 * Created by JOHN on 2015/11/10.
 */
public class UIHelper {
//    private static SweetAlertDialog loading;

    /**
     * 默认弹出Toast消息
     */
    public static void defaultToast(Context context, String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
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
     * @param context
     * 加载框
     */
    public static Dialog getLoadingDialog(Context context) {
       final Dialog mDialog = new Dialog(context, R.style.SimpleDialogLight);//SellDialog  Material_App_Dialog
//        View contentView = LayoutInflater.from(context).inflate(R.layout.dialog_loading,null);
//        ImageView iv_loading = (ImageView) contentView.findViewById(R.id.iv_loading);
//        Glide.with(MyApplication.application).load(R.mipmap.run).into(iv_loading);//loading
//        mDialog.title("");
//        mDialog.positiveAction("");
//        mDialog.negativeAction("");
//        mDialog.setContentView(contentView);
//        mDialog.setCancelable(false);
////        mDialog.show();
        return mDialog;
    }

    /**
     * 正常启动Activity
     */
    public static void startActivity(Context mContext, Class<? extends Activity> clazz) {
        AppManager.getAppManager().finishActivity(clazz);
        Intent intent = new Intent(mContext, clazz);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
        mContext.startActivity(intent);
    }

    /**
     * 拨号
     */
    public static void startPhone(Context mContext, String phone) {
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse(MessageFormat.format("tel:{0}",phone)));
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        mContext.startActivity(intent);
    }

    /**
     * 携带数据启动Activity
     */
    public static void startActivity(Context mContext, Class<? extends Activity> clazz, Bundle bundle) {
        AppManager.getAppManager().finishActivity(clazz);
        Intent intent = new Intent(mContext, clazz);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtras(bundle);
        mContext.startActivity(intent);
    }

    /**
     * 启动Activity，获取数据
     */
    public static void startActivityForResult(Activity activity, Class<? extends Activity> clazz, int requestCode, Bundle bundle) {
        AppManager.getAppManager().finishActivity(clazz);
        Intent intent = new Intent(activity, clazz);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        activity.startActivityForResult(intent, requestCode);
    }
    /**
     * 启动Activity，获取数据
     */
    public static void startActivityForResult(Fragment fragment, Class<? extends Activity> clazz, int requestCode, Bundle bundle) {
        AppManager.getAppManager().finishActivity(clazz);
        Intent intent = new Intent(fragment.getActivity(), clazz);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        fragment.startActivityForResult(intent, requestCode);
    }
    /**
     * @param activity
     * @param intent
     * @param requestCode
     * @param bundle
     */
    public static void startActivityForResult(Activity activity, Intent intent, int requestCode, Bundle bundle) {
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        activity.startActivityForResult(intent, requestCode);
    }

    /**
     * 启动Service
     *
     * @param context
     * @param intent
     */
    public static void startService(Context context, Intent intent) {
        context.startService(intent);
    }

    /**
     * 启动Service
     *
     * @param context
     * @param clazz
     */
    public static void startService(Context context, Class<? extends Service> clazz) {
        Intent intent = new Intent(context, clazz);
        startService(context, intent);
    }

    /**
     * 携带数据启动Service
     */
    public static void startService(Context mContext, Class<? extends Service> clazz, Bundle bundle) {
        Intent intent = new Intent(mContext, clazz);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.putExtras(bundle);
        startService(mContext, intent);
    }

    /**
     * 弹出拨打电话Activity
     *
     * @param context
     * @param phone
     */
    public static void showTel(Activity context, String phone) {
        Uri uri = Uri.parse("tel:" + phone);
        Intent intent = new Intent(Intent.ACTION_DIAL, uri);
        context.startActivity(intent);
    }

    /**
     * @param context
     * @param dp
     * @return
     */
    public static int dp2Px(Context context, float dp) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }

    /**
     * 调起系统发短信功能
     * @param phoneNumber
     */
    public static void doSendSMSTo(Activity context,String phoneNumber){
        if(PhoneNumberUtils.isGlobalPhoneNumber(phoneNumber)){
            Intent intent = new Intent(Intent.ACTION_SENDTO, Uri.parse("smsto:"+phoneNumber));
//            intent.putExtra("sms_body", message);
            context.startActivity(intent);
        }
    }

}
