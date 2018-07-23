package com.heshi.niuniu.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by Administrator on 2017/3/22 0022.
 */

public class ContentUtils {
    /**
     * uri2path
     *
     * @param context
     * @param contentUri
     */
    public static String uri2path(Context context, Uri contentUri) {
        Cursor cursor = null;
        try {
            String[] proj = {MediaStore.Images.Media.DATA};
            cursor = context.getContentResolver().query(contentUri, proj, null,
                    null, null);
            int column_index = cursor
                    .getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
            cursor.moveToFirst();
            return cursor.getString(column_index);
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }

    /**
     * Bitmap2File
     *
     * @param bitName
     * @param bitmap
     * @return
     * @throws IOException
     */
    public static File bitmap2file(String bitName, Bitmap bitmap)
            throws IOException {
        File f = new File("mnt/sdcard/" + bitName + ".jpg");
        f.createNewFile();
        FileOutputStream fOut = null;
        fOut = new FileOutputStream(f);
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, fOut);
        fOut.flush();
        fOut.close();
        return f;
    }

    /**
     * 取出whichSp中field字段对应的int类型的值如果该字段没对应值，则取出-1
     *
     * @param mContext
     * @param whichSp
     * @param field
     * @return
     */
    public static int getSharePreInt(Context mContext, String whichSp,
                                     String field) {
        SharedPreferences sp = (SharedPreferences) mContext
                .getSharedPreferences(whichSp, 0);
        int i = sp.getInt(field, -1);// 如果该字段没对应值，则取出-1
        return i;
    }

    /**
     * 取出whichSp中field字段对应的boolean类型的值如果该字段没对应值，则取出false
     *
     * @param mContext
     * @param whichSp
     * @param field
     * @return
     */
    public static boolean getSharePreBoolean(Context mContext, String whichSp,
                                             String field) {
        SharedPreferences sp = (SharedPreferences) mContext
                .getSharedPreferences(whichSp, 0);
        boolean i = sp.getBoolean(field, false);
        return i;
    }

    /**
     * 取出whichSp中field字段对应的boolean类型的值如果该字段没对应值，则取出true
     *
     * @param mContext
     * @param whichSp
     * @param field
     * @return
     */
    public static boolean getSharePreBooleanDefValueTrue(Context mContext, String whichSp,
                                                         String field) {
        SharedPreferences sp = (SharedPreferences) mContext
                .getSharedPreferences(whichSp, 0);
        boolean i = sp.getBoolean(field, true);
        return i;
    }

    /**
     * 取出whichSp中long字段对应的long类型的值如果该字段没对应值，则取出0L
     *
     * @param mContext
     * @param whichSp
     * @param field
     * @return
     */
    public static long getSharePreLong(Context mContext, String whichSp,
                                       String field) {
        SharedPreferences sp = (SharedPreferences) mContext
                .getSharedPreferences(whichSp, 0);
        return sp.getLong(field, 0L);
    }

    /**
     * 取出whichSp中field字段对应的String类型的值如果该字段没对应值，则取出空字符串
     *
     * @param mContext
     * @param whichSp
     * @param field
     * @return
     */
    public static String getSharePreString(Context mContext, String whichSp,
                                           String field) {
        SharedPreferences sp = (SharedPreferences) mContext
                .getSharedPreferences(whichSp, 0);
        String s = sp.getString(field, "");// 如果该字段没对应值，则取出空字符串
        return s;
    }

    /**
     * 保存long类型的value到whichSp中的field字段
     *
     * @param mContext
     * @param whichSp
     * @param field
     * @param value
     */
    public static void putSharePre(Context mContext, String whichSp,
                                   String field, long value) {
        SharedPreferences sp = (SharedPreferences) mContext
                .getSharedPreferences(whichSp, 0);
        sp.edit().putLong(field, value).commit();
    }

    /**
     * 保存string类型的value到whichSp中的field字段
     *
     * @param mContext
     * @param whichSp
     * @param field
     * @param value
     */
    public static void putSharePre(Context mContext, String whichSp,
                                   String field, String value) {
        SharedPreferences sp = (SharedPreferences) mContext
                .getSharedPreferences(whichSp, 0);
        sp.edit().putString(field, value).commit();
    }

    /**
     * 保存int类型的value到whichSp中的field字段
     *
     * @param mContext
     * @param whichSp
     * @param field
     * @param value
     */
    public static void putSharePre(Context mContext, String whichSp,
                                   String field, int value) {
        SharedPreferences sp = (SharedPreferences) mContext
                .getSharedPreferences(whichSp, 0);
        sp.edit().putInt(field, value).commit();
    }

    /**
     * 保存boolen类型的value到whichSp中的field字段(主要做登陆状态)
     *
     * @param mContext
     * @param whichSp
     * @param field
     * @param value
     */
    public static void putSharePre(Context mContext, String whichSp,
                                   String field, boolean value) {
        SharedPreferences sp = (SharedPreferences) mContext
                .getSharedPreferences(whichSp, 0);
        sp.edit().putBoolean(field, value).commit();
    }

    /**
     * 删除某个文件
     *
     * @param mContext
     * @param whichSp
     */
    public static void deleteField(Context mContext, String whichSp) {
        SharedPreferences sp = (SharedPreferences) mContext
                .getSharedPreferences(whichSp, 0);
        sp.edit().clear();
    }

    private static Toast toast = null;

    /**
     * Toast的封装
     *
     * @param ，来区别哪一个activity调用的
     * @param msg                你希望显示的值。
     */
    public static void showMsg(final Context act, final String msg) {
        if (toast != null) {
            toast.setText(msg);
        } else {
            toast = Toast.makeText(act, msg, Toast.LENGTH_SHORT);
        }
        toast.show();
    }

    /**
     * 获取当前的版本号
     *
     * @param context
     * @return
     */
    public static String getCurrentVersionCode(Context context) {
        String versionCode = "";
        PackageManager manager = context.getPackageManager();
        try {
            versionCode = manager.getPackageInfo(context.getPackageName(), 0).versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        return versionCode;
    }



}
