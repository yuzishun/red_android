package com.heshi.niuniu.model;

/**
 * Created by Administrator on 2018/7/17 0017.
 */

public class NewFriendModel {


    /**
     * friend_text : 海内存知己,天涯若比邻
     * friend_id : 159
     * follower : 1
     * user_id : 159
     * black_list : 0
     * count_id : 38
     * friend_nick : 匿名
     * write_list : 1
     */

    private String friend_text;
    private int friend_id;
    private int follower;
    private int user_id;
    private int black_list;
    private int count_id;
    private String friend_nick;
    private int write_list;
    private int type;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getFriend_text() {
        return friend_text;
    }

    public void setFriend_text(String friend_text) {
        this.friend_text = friend_text;
    }

    public int getFriend_id() {
        return friend_id;
    }

    public void setFriend_id(int friend_id) {
        this.friend_id = friend_id;
    }

    public int getFollower() {
        return follower;
    }

    public void setFollower(int follower) {
        this.follower = follower;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getBlack_list() {
        return black_list;
    }

    public void setBlack_list(int black_list) {
        this.black_list = black_list;
    }

    public int getCount_id() {
        return count_id;
    }

    public void setCount_id(int count_id) {
        this.count_id = count_id;
    }

    public String getFriend_nick() {
        return friend_nick;
    }

    public void setFriend_nick(String friend_nick) {
        this.friend_nick = friend_nick;
    }

    public int getWrite_list() {
        return write_list;
    }

    public void setWrite_list(int write_list) {
        this.write_list = write_list;
    }
}
