package com.heshi.niuniu.rx.data.exception;

import android.content.Context;

import com.heshi.niuniu.base.MyApplication;
import com.heshi.niuniu.util.MyLog;
import com.heshi.niuniu.util.ToashUtils;

import retrofit2.adapter.rxjava.HttpException;


/**
 * Created by min on 2017/3/1.
 */
public class ErrorHanding {
    private static String TAG = "loginEmChat";
    private static Context mContext;

    //    static HttpDialog myHttpDialog;
    public static void handleError(Throwable throwable, Object msg, Context context) {
        throwable.printStackTrace();
        mContext = context;
        //        myHttpDialog = new HttpDialog(context);
        if (!MyApplication.ExtImpl.g().isAvailable()) {
            ToashUtils.show(context, "您已经处于无网络的异次元");
        } else if (throwable instanceof HttpException) {
            ToashUtils.show(context, ((ServerException) throwable).message);
        } else if (throwable instanceof ApiException) {
            ToashUtils.show(context, ((ApiException) throwable).message);
        } else if (throwable instanceof ServerException) {
            ServerException exception = (ServerException) throwable;
            ToashUtils.show(mContext, "" + msg);
        }
    }


}
