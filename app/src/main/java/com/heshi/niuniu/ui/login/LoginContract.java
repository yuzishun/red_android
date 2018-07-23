package com.heshi.niuniu.ui.login;

import com.heshi.niuniu.present.IPresenter;
import com.heshi.niuniu.present.MModel;

/**
 * Created by Administrator on 2018/6/28 0028.
 */

public class LoginContract {

    interface Model extends MModel {

    }

    interface Presenter extends IPresenter<Model> {
        void loginAction(String name,String password);
        void getImPass(String name);
        void loginIm(String userId, String password, String appKey);

    }

}
