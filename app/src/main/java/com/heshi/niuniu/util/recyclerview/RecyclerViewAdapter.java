package com.heshi.niuniu.util.recyclerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Name: RecyclerViewAdapter
 * Author: FrameJack
 * Email: framejackname@gmail.com
 * Date: 2017-10-17 09:42
 * Desc:
 * Comment: //TODO
 */
public abstract class RecyclerViewAdapter<T> extends RecyclerView.Adapter<BaseViewHolder> {


    protected List<T> mList;
    protected LayoutInflater mInflater;
    protected Context mContext;
    public int mPosition;
    private T t;
    private int type;

    public RecyclerViewAdapter(Context context) {
        this.mContext = context;
    }

    public RecyclerViewAdapter(Context context, T t) {
        this.mContext = context;
        mInflater = LayoutInflater.from(context);
        mList = new ArrayList<>();
        this.t = t;
    }

    public RecyclerViewAdapter(Context context, List<T> list) {
        mInflater = LayoutInflater.from(context);
        this.mContext = context;
        this.mList = list;
    }

    @Override
    public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return CreateViewHolder(parent, viewType);
    }

    @Override
    public void onBindViewHolder(BaseViewHolder holder, int position) {
        holder.bindViewHolder(mList.get(position), mContext, position);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    protected abstract BaseViewHolder CreateViewHolder(ViewGroup parent, int viewType);

    /**
     * 记住一点有head和没有head是反过来的逻辑
     * 因为多了一个head占用了一个position position=0被占用了 所以你做一些操作的时候要注意这一点
     *
     *
     * @param position
     * @param isHead
     * @return
     */
    protected int setViewType(int position, boolean isHead) {
        //TODO  这只是一个例子 具体的业务逻辑要自己实现
//        if (!isHead) {
//            if (position % 2 == 1)
//                return 0;
//            else
//                return 1;
//        } else {
//            if (position % 2 == 1)
//                return 1;
//            else
//                return 0;
//        }
//
        return -1;
    }

    public void setNotifyData(ArrayList<T> list) {
        this.mList = list;
        notifyDataSetChanged();
    }


}
