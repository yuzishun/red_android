package com.heshi.niuniu.model;

import java.io.Serializable;

/**
 * Created by Administrator on 2018/7/6 0006.
 */

public class BaseInfoModel implements Serializable {


    /**
     * user_id : 166
     * user_name : 17301794508
     * nick_name : 嵇伟
     */

    private int user_id;
    private String user_name;
    private String nick_name;
    private String photoUrl;

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getNick_name() {
        return nick_name;
    }

    public void setNick_name(String nick_name) {
        this.nick_name = nick_name;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }
}
