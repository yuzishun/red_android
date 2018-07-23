package com.heshi.niuniu.ui.password.commit_pass;

import android.support.design.widget.CoordinatorLayout;

import com.heshi.niuniu.present.IPresenter;
import com.heshi.niuniu.present.MModel;

/**
 * Created by Administrator on 2018/6/29 0029.
 */

public class CommitPassContract {

    interface Model extends MModel {
        void onSuccess();

    }

    interface Presenter extends IPresenter<Model> {
        void verPass(String confirmpassword, String newpassword, String token
                , CoordinatorLayout coordinatorLayout);

    }

}
