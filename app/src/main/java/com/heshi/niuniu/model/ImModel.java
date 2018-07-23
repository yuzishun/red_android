package com.heshi.niuniu.model;

/**
 * Created by Administrator on 2018/7/3 0003.
 */

public class ImModel {


    /**
     * im_user : imuser_686676c5a6674a77a6271d81d316c014
     * im_pwd : 80825eeab9a64e0ab44611a1664c99fc
     * appkey : 24641603
     */

    private String im_user;
    private String im_pwd;
    private String appkey;

    public String getIm_user() {
        return im_user;
    }

    public void setIm_user(String im_user) {
        this.im_user = im_user;
    }

    public String getIm_pwd() {
        return im_pwd;
    }

    public void setIm_pwd(String im_pwd) {
        this.im_pwd = im_pwd;
    }

    public String getAppkey() {
        return appkey;
    }

    public void setAppkey(String appkey) {
        this.appkey = appkey;
    }

    @Override
    public String toString() {
        return "ImModel{" +
                "im_user='" + im_user + '\'' +
                ", im_pwd='" + im_pwd + '\'' +
                ", appkey='" + appkey + '\'' +
                '}';
    }
}
