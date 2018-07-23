package com.heshi.niuniu.fragment.main.connect;

import android.support.v7.widget.RecyclerView;

import com.heshi.niuniu.model.ContactModel;
import com.heshi.niuniu.present.IPresenter;
import com.heshi.niuniu.present.MModel;
import com.heshi.niuniu.ui.main.MainContract;
import com.yanzhenjie.recyclerview.swipe.SwipeMenuRecyclerView;

import java.util.List;

/**
 * Created by Administrator on 2018/6/29 0029.
 */

public class ConnectingContract {

    interface Model extends MModel {
        void setEmptyView(int size);

    }

    interface Presenter extends IPresenter<Model> {
        void upCurrListIndex(String key);
        void initAdapter(SwipeMenuRecyclerView recyclerView, int type);
        void getList(String name);
    }

}
