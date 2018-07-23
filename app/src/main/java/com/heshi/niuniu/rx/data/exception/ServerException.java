package com.heshi.niuniu.rx.data.exception;

/**
 * Created by wali on 2016/9/18.
 */
public class ServerException extends Exception {
    public boolean success;
    public String message;

    public ServerException(boolean success, String message) {
        this.success = success;
        this.message = message;
    }
}