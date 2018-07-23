package com.heshi.niuniu.ui.main.connect.friend_remark;

import com.heshi.niuniu.present.IPresenter;
import com.heshi.niuniu.present.MModel;

/**
 * Created by Administrator on 2018/7/13 0013.
 */

public class FriendInfoMarkContract {

    interface Model extends MModel {
        void onSuccess(int data);

    }

    interface Presenter extends IPresenter<Model> {
        void setInfoMark(String user_id, String friend_id, String friend_text);

    }

}
