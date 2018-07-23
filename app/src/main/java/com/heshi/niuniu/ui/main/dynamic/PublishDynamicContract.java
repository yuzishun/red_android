package com.heshi.niuniu.ui.main.dynamic;

import com.heshi.niuniu.present.IPresenter;
import com.heshi.niuniu.present.MModel;

import java.util.List;

import okhttp3.MultipartBody;

/**
 * Created by Administrator on 2018/7/19 0019.
 */

public class PublishDynamicContract {

    interface Model extends MModel {

    }

    interface Presenter extends IPresenter<Model> {
        void publishDynamic(List<MultipartBody.Part> part);

    }

}
