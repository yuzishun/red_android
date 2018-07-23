package com.heshi.niuniu.util.recyclerview;

import android.content.Context;
import android.view.View;

/**
 * Created by Administrator on 2017/1/9 0009.
 */

public abstract class BaseViewHolder<T> extends ViewHolderData {

    public BaseViewHolder(View itemView) {
        super(itemView);
    }

    public abstract void bindViewHolder(T mode, Context context, int position);


}
