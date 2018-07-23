package com.heshi.niuniu.eventbus;

/**
 * Created by Administrator on 2018/7/9 0009.
 */

public class PhotoMsgEvent {

    public String url;

    public PhotoMsgEvent(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }
}
