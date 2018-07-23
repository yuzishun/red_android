package com.heshi.niuniu.fragment.main.dynamic;

import android.support.v7.widget.RecyclerView;

import com.heshi.niuniu.model.DynamicInfoModel;
import com.heshi.niuniu.present.IPresenter;
import com.heshi.niuniu.present.MModel;

/**
 * Created by Administrator on 2018/6/29 0029.
 */

public class DynamicContract {

    interface Model extends MModel {
        void complete();

        void close();

        void noLoadMode();

        void defaultMode();
    }

    interface Presenter extends IPresenter<Model> {
        void initAdapter(RecyclerView recyclerView);

        void getDynamicList(String user_name, int page);

        void getDynamicInfo(String username);

        void setDetailInfo(DynamicInfoModel dynamicInfoModel);

        void getZanData(String circle_id);

        void getOneStepComment(String circle_id);
    }

}
