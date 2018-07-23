package com.heshi.niuniu.model;

/**
 * Created by Administrator on 2018/7/11 0011.
 */

public class ImCusModel {

    private String name;
    private String imgUrl;

    public ImCusModel(String name, String imgUrl) {
        this.name = name;
        this.imgUrl = imgUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }
}
