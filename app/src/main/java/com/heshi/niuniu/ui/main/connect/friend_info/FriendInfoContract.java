package com.heshi.niuniu.ui.main.connect.friend_info;

import com.heshi.niuniu.model.BaseInfoModel;
import com.heshi.niuniu.present.IPresenter;
import com.heshi.niuniu.present.MModel;

/**
 * Created by Administrator on 2018/7/13 0013.
 */

public class FriendInfoContract {

    interface Model extends MModel {
        void setBaseInfo(BaseInfoModel model);
        void addSuccess();
    }

    interface Presenter extends IPresenter<Model> {
        void getBaseInfo(String name);

        void addFriendAction(String name, String id);

    }

}
