package com.heshi.niuniu.rx.data;

/**
 * Created by min on 2017/3/1.
 * 异常处理
 */
public interface ErrorBundle {
    Exception getException();
    String getErrorMessage();
}
