package com.heshi.niuniu.fragment.main.mine;

import android.widget.TextView;

import com.heshi.niuniu.model.BaseInfoModel;
import com.heshi.niuniu.present.IPresenter;
import com.heshi.niuniu.present.MModel;

/**
 * Created by Administrator on 2018/6/29 0029.
 */

public class MineContract {

    interface Model extends MModel {
        void setBaseInfo(BaseInfoModel model);

    }

    interface Presenter extends IPresenter<Model> {
        void getBaseInfo(String name);

        void getWeiBoNum(String user_name, TextView tv);

        void getDynamic(String user_name, TextView tv);

        void getFan(String user_id, TextView tv);

        void getRedPacket(String user_id, TextView tv);

    }

}
