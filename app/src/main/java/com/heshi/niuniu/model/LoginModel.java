package com.heshi.niuniu.model;

/**
 * Created by Administrator on 2018/6/29 0029.
 */

public class LoginModel {


    /**
     * access_token : 0b78865146d946f0939267af493097f8
     * expires_in : 1209600
     */

    private String access_token;
    private int expires_in;

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public int getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(int expires_in) {
        this.expires_in = expires_in;
    }
}
