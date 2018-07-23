package com.heshi.niuniu.model;

import java.util.List;

/**
 * Created by Administrator on 2018/7/17 0017.
 */

public class DynamicListModel {


    /**
     * friend_nick :
     * circle_id : 1
     * user_name : 18606571216
     * pic_meta : 27,28
     * txt_meta :
     * sign :
     * duration :
     * pic_src :
     * id_Ju :
     * jurisdiction :
     */

    private String friend_nick;
    private int circle_id;
    private String user_name;
    private String pic_meta;
    private String txt_meta;
    private String sign;
    private String duration;
    private String pic_src;
    private String id_Ju;
    private String jurisdiction;
    private List<String>urlList;

    public List<String> getUrlList() {
        return urlList;
    }

    public void setUrlList(List<String> urlList) {
        this.urlList = urlList;
    }

    public String getFriend_nick() {
        return friend_nick;
    }

    public void setFriend_nick(String friend_nick) {
        this.friend_nick = friend_nick;
    }

    public int getCircle_id() {
        return circle_id;
    }

    public void setCircle_id(int circle_id) {
        this.circle_id = circle_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getPic_meta() {
        return pic_meta;
    }

    public void setPic_meta(String pic_meta) {
        this.pic_meta = pic_meta;
    }

    public String getTxt_meta() {
        return txt_meta;
    }

    public void setTxt_meta(String txt_meta) {
        this.txt_meta = txt_meta;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getPic_src() {
        return pic_src;
    }

    public void setPic_src(String pic_src) {
        this.pic_src = pic_src;
    }

    public String getId_Ju() {
        return id_Ju;
    }

    public void setId_Ju(String id_Ju) {
        this.id_Ju = id_Ju;
    }

    public String getJurisdiction() {
        return jurisdiction;
    }

    public void setJurisdiction(String jurisdiction) {
        this.jurisdiction = jurisdiction;
    }
}
