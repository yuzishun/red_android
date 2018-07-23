package com.heshi.niuniu.util;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

/**
 * Created by Administrator on 2017/1/10 0010.
 */

public class PackageUtils {
   public static String getVersionName(Context context){
       PackageManager manager = context.getPackageManager();

       try {
           PackageInfo packageInfo = manager.getPackageInfo(context.getPackageName(), 0);
           String versionName = packageInfo.versionName;
           System.out.println(versionName);
           return versionName;
       } catch (PackageManager.NameNotFoundException e) {
           e.printStackTrace();
       }
       return "";

   }
    public static String getVersionNum(Context context){
        PackageManager manager = context.getPackageManager();
        try {
            PackageInfo packageInfo = manager.getPackageInfo(context.getPackageName(), 0);
            int versioncode = packageInfo.versionCode;
            System.out.println(versioncode+"");
            return versioncode+"";
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
