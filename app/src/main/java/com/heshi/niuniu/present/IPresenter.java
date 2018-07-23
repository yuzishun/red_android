package com.heshi.niuniu.present;

/**
 * Created by min on 2017/3/1.
 */

public interface IPresenter<T extends MModel> {
    void attachView(T model);
    void detachView();
}
