package com.heshi.niuniu.util.recyclerview;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Build;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Administrator on 2017/2/22 0022.
 */

public class ViewHolderData extends RecyclerView.ViewHolder {

    private SparseArray<View> mViews;

    public ViewHolderData(View itemView) {
        super(itemView);
        this.mViews = new SparseArray<>();
    }

    @SuppressWarnings("unchecked")
    public <T extends View> T getView(int viewId) {
        View view = mViews.get(viewId);
        if (view == null) {
            view = itemView.findViewById(viewId);
            mViews.put(viewId, view);
            view.setTag(viewId);
        }
        return (T) view;
    }

    public <T extends View> T getView(int viewId, int position) {
        View view = mViews.get(viewId);
        if (view == null) {
            view = itemView.findViewById(viewId);
            mViews.put(viewId, view);
            view.setTag(viewId, position);
        }
        return (T) view;
    }

    public ViewHolderData setTextColorRes(int viewId,int resId){
        TextView cache = getView(viewId);
        Context context = cache.getContext();
        cache.setTextColor(getColorByRes(context,resId));
        return this;
    }
    /**
     * 兼容低版本和高版本
     * @param res
     * @return
     */
    public int getColorByRes(Context context,int res) {
        int result = 0;
        if (Build.VERSION.SDK_INT >= 23) {
            result =  context.getColor(res);
        } else {
            result = context.getResources().getColor(res);
        }
        return result;
    }
    /**
     * 设置TextView的值
     *
     * @param viewId
     * @param value
     * @return
     */
    public ViewHolderData setText(int viewId, String value) {
        TextView textView = getView(viewId);
        textView.setText(value);
        return this;
    }

    public ViewHolderData setImageResource(int viewId, int resId) {
        ImageView view = getView(viewId);
        view.setImageResource(resId);
        return this;
    }

    public ViewHolderData setImageBitmap(int viewId, Bitmap bitmap) {
        ImageView view = getView(viewId);
        view.setImageBitmap(bitmap);
        return this;
    }

    public ViewHolderData setBackgroundResource(int viewId, int backgroundRes) {
        View view = getView(viewId);
        view.setBackgroundResource(backgroundRes);
        return this;
    }

    public ViewHolderData setTextColor(int viewId, int textColor) {
        TextView view = getView(viewId);
        view.setTextColor(textColor);
        return this;
    }

}
