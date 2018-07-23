package com.heshi.niuniu.ui.password.forget;

import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.widget.TextView;

import com.heshi.niuniu.present.IPresenter;
import com.heshi.niuniu.present.MModel;
import com.heshi.niuniu.ui.main.MainContract;

import java.util.List;

/**
 * Created by Administrator on 2018/6/29 0029.
 */

public class ForgetPassContract {

    interface Model extends MModel {


    }

    interface Presenter extends IPresenter<Model> {

        void forgetPass(String userName,TextView tv, CoordinatorLayout container);

        void commitVerCode(String userName, String verCode);
    }

}
