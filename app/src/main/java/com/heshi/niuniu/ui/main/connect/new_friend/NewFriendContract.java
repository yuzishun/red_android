package com.heshi.niuniu.ui.main.connect.new_friend;

import android.support.v7.widget.RecyclerView;

import com.heshi.niuniu.present.IPresenter;
import com.heshi.niuniu.present.MModel;

/**
 * Created by Administrator on 2018/7/17 0017.
 */

public class NewFriendContract {

    interface Model extends MModel {

    }

    interface Presenter extends IPresenter<Model> {
        void initAdapter(RecyclerView recyclerView);

        void getFriendList(String user_id, int type);

        void agreeOperation(String user_id, String friend_id, int position);
    }

}
