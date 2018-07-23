package com.heshi.niuniu.util;


import android.util.Base64;

import java.io.UnsupportedEncodingException;

/**
 * Author : Haily
 * Date   : 2018/1/26 14:24
 * Email  : zhanghailei55@gmail.com
 * Desc   :
 * Comment: //TODO
 */

public class Base64Utils {
    /**
     * base64 解码
     * @param str
     * @return
     */
    public static String decoder(String str) {
        String strEncoder = null;
        try {
            strEncoder = new String( Base64.decode(str,Base64.DEFAULT), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return strEncoder;
    }

}
