package com.heshi.niuniu.ui.main.connect.search;

import com.heshi.niuniu.model.ConnectFriendModel;
import com.heshi.niuniu.present.IPresenter;
import com.heshi.niuniu.present.MModel;

/**
 * Created by Administrator on 2018/7/13 0013.
 */

public class ConnectSearchContract {

    interface Model extends MModel {
        void onSuccess(ConnectFriendModel contactModels);

    }

    interface Presenter extends IPresenter<Model> {
        void searchFriend(String name);
    }
}
