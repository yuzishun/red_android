package com.heshi.niuniu.ui.mine.change_photo;

import com.heshi.niuniu.present.IPresenter;
import com.heshi.niuniu.present.MModel;

import java.util.List;

import okhttp3.MultipartBody;

/**
 * Created by Administrator on 2018/7/9 0009.
 */

public class ChangePhotoContract {

    interface Model extends MModel {


    }

    interface Presenter extends IPresenter<Model> {
        void upDataUrl(List<MultipartBody.Part> part,String path);

    }


}
