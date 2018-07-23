package com.heshi.niuniu.rx.data.exception;

/**
 * Created by wali on 2016/9/18.
 */
public class ApiException extends Exception {
    public int code;
    public String message;

    public ApiException(Throwable throwable, int code) {
        super(throwable);
        this.code = code;

    }
}

