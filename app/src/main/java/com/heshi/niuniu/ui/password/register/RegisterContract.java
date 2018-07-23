package com.heshi.niuniu.ui.password.register;

import android.widget.TextView;

import com.heshi.niuniu.present.IPresenter;
import com.heshi.niuniu.present.MModel;

/**
 * Created by Administrator on 2018/6/29 0029.
 */

public class RegisterContract {

    interface Model extends MModel {
        void onSuccess();

    }

    interface Presenter extends IPresenter<Model> {
        void getCode(String usrName,TextView btn);

        void registerCou(String userName, String pass, String code);

    }

}
