package com.heshi.niuniu.app;

import android.text.TextUtils;

import com.heshi.niuniu.model.ImModel;
import com.heshi.niuniu.model.LoginModel;
import com.heshi.niuniu.util.SharedHelper;

/**
 * Created by Administrator on 2018/6/28 0028.
 */

public class Constants {

    public static final String Base_Url = "http://api.zhimaim.com/";//正式
    public static final String Im_Url = "http://121.42.177.97:8080/";
    //icon头像获取
    public static final String Icon_url = "http://121.42.177.97:8080/red/use/pic_id.do?user_id=";

    //icon头像获取
    public static final String Dynamic_Img_url = "http://121.42.177.97:8080/redfive/face/get_pic.do?id=";

    public static final String Dynamic_Head_url = "red/use/gethard_pic.do?user_name=";

    //network time
    public static final int HTTP_CONNECT_TIMEOUT = 16000;

    public static String UUID = "";

    public static String access_token = "";
    public static String userName = "";

    public static String im_usrName = "";
    public static String im_pass = "";
    public static String appkey = "";

    public static String USER_ID = "";
    public static String USER_NAME = "";

    public static final String TOKEN_KEY = "token";
    public static final String USER_NAME_KEY = "usrName";
    public static final String IM_USER_KEY = "im_usrName";
    public static final String IM_PASS_KEY = "im_pass";
    public static final String APP_KEY = "appkey";
    public static boolean isLoginIm;
    public static final String EXTRA_RESULT_CODE_TYPE = "result_code_type";
    public static final String EXTRA_RESULT_CONTENT = "result_content";
    public static String USER_PORTRAIT = "000";
    public static String IMG_URL = "";


    public static void saveInfo(LoginModel loginModel, String name) {
        SharedHelper.put(Constants.TOKEN_KEY, loginModel.getAccess_token());
        SharedHelper.put(Constants.USER_NAME_KEY, name);

    }

    public static void saveImInfo(ImModel imModel) {
        SharedHelper.put(Constants.IM_USER_KEY, imModel.getIm_user());
        SharedHelper.put(Constants.IM_PASS_KEY, imModel.getIm_pwd());
        SharedHelper.put(Constants.APP_KEY, imModel.getAppkey());
    }


    public static void readInfo() {
        access_token = SharedHelper.getString(TOKEN_KEY, "");
        userName = SharedHelper.getString(USER_NAME_KEY, "");
        im_usrName = SharedHelper.getString(Constants.IM_USER_KEY, "");
        im_pass = SharedHelper.getString(Constants.IM_PASS_KEY, "");
        appkey = SharedHelper.getString(Constants.APP_KEY, "");

    }

}
