package com.heshi.niuniu.model;

/**
 * Created by Administrator on 2018/7/13 0013.
 */

public class ConnectFriendModel {


    /**
     * user_id : 173
     * user_name : 15618315007
     * nick_name : 匿名
     */

    private int user_id;
    private String user_name;
    private String nick_name;

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
}
