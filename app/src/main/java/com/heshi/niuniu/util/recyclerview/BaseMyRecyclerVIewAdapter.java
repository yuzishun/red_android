package com.heshi.niuniu.util.recyclerview;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.heshi.niuniu.R;

import java.util.List;

/**
 * Name: BaseMyRecyclerVIewAdapter
 * Author: FrameJack
 * Email: framejackname@gmail.com
 * Date: 2017-10-17 09:49
 * Desc: covert 自行实现
 * Comment: //TODO 单一item implements  BaseMyRecyclerVIewAdapter(Context context, List<T> streamList)
 * covert(T mode, BaseViewHolder holder, int position)
 * // TODO 多个item implements BaseMyRecyclerVIewAdapter(Context context, List<T> streamList)
 * implements   getViewHolder(ViewGroup parent, int viewType)
 * implements setViewType(int position, boolean isHead)
 * viewHolder 继承 viewHolder 具体逻辑实现在viewCovert(T mode, BaseViewHolder holder, Context context, int position, int viewType) 实现
 * <p>
 * 1.covert(T mode, BaseViewHolder holder, int position)
 * 单一的逻辑实现
 * <p>
 * 2. viewCovert(T mode, BaseViewHolder holder, Context context, int position, int viewType)
 * 多种逻辑实现 setViewType(int position, boolean isHead)判断viewType的你懂得大福利，嘻嘻！
 */
public abstract class BaseMyRecyclerVIewAdapter<T> extends RecyclerViewAdapter {
    private setOnClickListener onClickListener;
    protected setOnItemClickListener onItemClickListener;//item点击事件
    private setOnLongListener onLongListener;
    private int layoutId;
    private View mHeaderView;
    private View mFootView;
    private int swipePosition = -1;
    private View swipeView;

    public static final int TYPE_HEADER = -3;
    public static final int TYPE_FOOTER = -4;
    public static final int TYPE_NORMAL = -2;
    private boolean isHead;
    protected CardListener cardListener;


    public void setHeaderView(View headerView) {
        mHeaderView = headerView;
        if (headerView != null) {
            isHead = true;
        }
        notifyItemInserted(0);
    }

    public void setFootView(View footView) {
        this.mFootView = footView;
        notifyItemChanged(this.getItemCount());
    }

    public void addEmptyView(View headerView, RecyclerView recyclerView, String str) {
        if (headerView == null) {
            headerView = LayoutInflater.from(mContext).inflate(R.layout.layout_empty_view, recyclerView, false);
            TextView tv = (TextView) headerView.findViewById(R.id.text_empty_view);
            tv.setText(str);
        }

        mHeaderView = headerView;
        if (headerView != null) {
            isHead = true;
        }
        notifyItemInserted(0);
    }

    public void addEmptyView(RecyclerView recyclerView, String str) {
        View headerView = LayoutInflater.from(mContext).inflate(R.layout.layout_empty_view, recyclerView, false);
        TextView tv = (TextView) headerView.findViewById(R.id.text_empty_view);
        tv.setText(String.format("暂无%s列表数据", str));

        mHeaderView = headerView;
        if (headerView != null) {
            isHead = true;
        }
        notifyItemInserted(0);
    }

    public void removeEmptyView(View headerView) {
        mHeaderView = null;
        if (headerView != null) {
            isHead = true;
        } else {
            isHead = false;
        }
//        notifyItemInserted(0);
    }


    /**
     * 多个item使用
     *
     * @param context
     * @param list
     * @param layoutId
     */
    public BaseMyRecyclerVIewAdapter(Context context, List<T> list, int layoutId) {
        super(context, list);
        this.layoutId = layoutId;
    }

    /**
     * 单独只有一个item时候使用的
     *
     * @param context
     * @param list
     */
    public BaseMyRecyclerVIewAdapter(Context context, List<T> list) {
        super(context, list);
    }

    @Override
    protected BaseViewHolder CreateViewHolder(ViewGroup parent, int viewType) {
        if (mFootView != null && viewType == TYPE_FOOTER)
            return new viewHolder(mFootView);
        if (mHeaderView != null && viewType == TYPE_HEADER)
            return new viewHolder(mHeaderView);
        return getViewHolder(parent, viewType) == null ? new viewHolder(setInflater(layoutId, parent)) : getViewHolder(parent, viewType);
    }

    /**
     * position值的控制
     *
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        int cIndex = position;
        boolean isHeadView = getItemViewType(position) == TYPE_HEADER;
        boolean isFootView = getItemViewType(position) == TYPE_FOOTER;

        if (isHeadView)
            return;
        if (isFootView)
            return;

        if (mHeaderView != null) {
            cIndex--;
        }

        if (this.getItemCount() - 1 == position) {
            if (mFootView != null)
                cIndex--;
        }

        ((BaseViewHolder) holder).bindViewHolder(mList.get(cIndex), mContext, cIndex);
    }

    /**
     * 如果是Grid的类型的判断foot和head处理
     *
     * @param recyclerView
     */
    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);

        RecyclerView.LayoutManager manager = recyclerView.getLayoutManager();
        if (manager instanceof GridLayoutManager) {
            final GridLayoutManager gridManager = ((GridLayoutManager) manager);
            gridManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    switch (getItemViewType(position)) {
                        case TYPE_HEADER:
                        case TYPE_FOOTER:
                            return gridManager.getSpanCount();
                        default:
                            return 1;
                    }
                }
            });
        }
    }

    /**
     * 对Grid的宽度进行处理
     *
     * @param holder
     */
    @Override
    public void onViewAttachedToWindow(RecyclerView.ViewHolder holder) {
        super.onViewAttachedToWindow(holder);

        ViewGroup.LayoutParams lp = holder.itemView.getLayoutParams();
        if (lp != null
                && lp instanceof StaggeredGridLayoutManager.LayoutParams) {
            StaggeredGridLayoutManager.LayoutParams p = (StaggeredGridLayoutManager.LayoutParams) lp;
            if (mFootView == null) {
                p.setFullSpan(holder.getLayoutPosition() == 0);
            } else {
                p.setFullSpan(holder.getLayoutPosition() == 0 || holder.getLayoutPosition() == this.getItemCount() - 1);
            }
        }
    }

    protected BaseViewHolder getViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    public View setInflater(int LayoutId, ViewGroup parent) {
        View view = mInflater.inflate(LayoutId, parent, false);
        return view;
    }

    /**
     * 万恶的根源 viewHolder
     */
    public class viewHolder extends BaseViewHolder {

        protected void viewCovert(T mode, BaseViewHolder holder, Context context, int position, int viewType) {

        }

        public viewHolder(View itemView) {
            super(itemView);
        }

        @Override
        public void bindViewHolder(Object mode, Context context, final int position) {
            if (setViewType(position, isHead) == -1) {
                covert((T) mode, this, position);
            } else {
                viewCovert((T) mode, this, mContext, position, getItemViewType());
            }

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onClickListener != null) {
                        onClickListener.onClick(v);
                    }
                    if (onItemClickListener != null) {
                        onItemClickListener.onItemClick(v, position);
                    }
                }
            });

            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    if (onLongListener != null) {
                        onLongListener.onLongListener(v, position);
                    }
                    return false;
                }
            });
        }
    }

    /**
     * 注释哈，这个只能在single Item使用方法
     *
     * @param mode
     * @param holder
     * @param position
     */
    protected void covert(T mode, BaseViewHolder holder, int position) {

    }

    public interface setOnClickListener {
        void onClick(View view);
    }

    public interface setOnItemClickListener {
        void onItemClick(View view, int position);
    }

    public interface setOnLongListener {
        void onLongListener(View view, int position);
    }

    public setOnClickListener getOnClickListener() {
        return onClickListener;
    }

    public void setOnClickListener(setOnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public setOnItemClickListener getOnItemClickListener() {
        return onItemClickListener;
    }

    public void setOnItemClickListener(setOnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public setOnLongListener getOnLongListener() {
        return onLongListener;
    }

    public void setOnLongListener(setOnLongListener onLongListener) {
        this.onLongListener = onLongListener;
    }

    public interface CardListener{
        void onCardListener(View view, int position);
    }

    public CardListener getCardListener() {
        return cardListener;
    }

    public void setCardListener(CardListener cardListener) {
        this.cardListener = cardListener;
    }

    /**
     * 返回不同的类型
     *
     * @param position
     * @return
     */
    @Override
    public int getItemViewType(int position) {

        if (position == 0) {
            if (mHeaderView == null)
                return setViewType(position, isHead) != -1 ? setViewType(position, isHead) : TYPE_NORMAL;
            else
                return TYPE_HEADER;

        } else if (position == getItemCount() - 1) {
            if (mFootView == null)
                return setViewType(position, isHead) != -1 ? setViewType(position, isHead) : TYPE_NORMAL;
            else
                return TYPE_FOOTER;
        } else {
            if (setViewType(position, isHead) != -1)
                return setViewType(position, isHead);
            else
                return TYPE_NORMAL;
        }

    }

    /**
     * 计算总个数
     *
     * @return
     */
    @Override
    public int getItemCount() {
        return mList.size() + (mHeaderView == null ? 0 : 1) + (mFootView == null ? 0 : 1);
    }

    public T getItem(int position) {
        return (T) mList.get(position);
    }


    /**
     * RecyclerView 移动到当前位置，
     * @param manager
     *         设置RecyclerView对应的manager
     * @param n
     *         要跳转的位置
     */
    public static void MoveToPosition(LinearLayoutManager manager, int n) {
        manager.scrollToPositionWithOffset(n, 0);
        manager.setStackFromEnd(true);
    }
}
