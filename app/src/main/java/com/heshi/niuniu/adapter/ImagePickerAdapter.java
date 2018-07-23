package com.heshi.niuniu.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


import com.heshi.niuniu.R;
import com.heshi.niuniu.custom.RoundedImageView;
import com.lzy.imagepicker.ImagePicker;
import com.lzy.imagepicker.bean.ImageItem;

import java.util.ArrayList;
import java.util.List;

/**
 * ================================================
 * 作    者：ikkong （ikkong@163.com），修改 jeasonlzy（廖子尧）
 * 版    本：1.0
 * 创建日期：2016/5/19
 * 描    述：
 * 修订历史：微信图片选择的Adapter, 感谢 ikkong 的提交
 * ================================================
 */
public class ImagePickerAdapter extends RecyclerView.Adapter<ImagePickerAdapter.SelectedPicViewHolder> {
    private int itemId = R.layout.list_item_image;
    private int maxImgCount;
    private Context mContext;
    private List<ImageItem> mData;
    private LayoutInflater mInflater;
    private OnRecyclerViewItemClickListener listener;
    private onDeleteListener deleteListener;
    private boolean isAdded;   //是否额外添加了最后一个图片
    private int isDelete;

    public interface OnRecyclerViewItemClickListener {
        void onItemClick(View view, int position);
    }

    public void setOnItemClickListener(OnRecyclerViewItemClickListener listener) {
        this.listener = listener;
    }

    public interface onDeleteListener {
        void deleteListener(int position);
    }

    public void setDeleteListener(onDeleteListener deleteListener) {
        this.deleteListener = deleteListener;
    }

    public void setImages(List<ImageItem> data) {
        mData = new ArrayList<>(data);
        if (getItemCount() < maxImgCount) {
            mData.add(new ImageItem());
            isAdded = true;
        } else {
            isAdded = false;
        }
        notifyDataSetChanged();
    }

    /**
     * @param data
     * @param delete
     */
    public void setImages(List<ImageItem> data, int delete) {
        this.isDelete = delete;

        mData = new ArrayList<>(data);
        if (getItemCount() < maxImgCount) {
            mData.add(new ImageItem());
            isAdded = true;
        } else {
            isAdded = false;
        }
        notifyDataSetChanged();
    }

    public void setImages(List<ImageItem> data, boolean isAdd) {
        mData = new ArrayList<>(data);
        if (isAdd) {
            if (getItemCount() < maxImgCount) {
                mData.add(new ImageItem());
                isAdded = true;
            } else {
                isAdded = false;
            }
        }

        isAdded = isAdd;
        notifyDataSetChanged();
    }

    public List<ImageItem> getImages() {
        //由于图片未选满时，最后一张显示添加图片，因此这个方法返回真正的已选图片
        if (isAdded)
            return new ArrayList<>(mData.subList(0, mData.size() - 1));
        else
            return mData;
    }

    public ImagePickerAdapter(Context mContext, List<ImageItem> data, int maxImgCount) {
        this.mContext = mContext;
        this.maxImgCount = maxImgCount;
        this.mInflater = LayoutInflater.from(mContext);
        setImages(data);
    }

    public ImagePickerAdapter(int isDelete, Context mContext, List<ImageItem> data, int maxImgCount) {
        this.isDelete = isDelete;
        this.mContext = mContext;
        this.maxImgCount = maxImgCount;
        this.mInflater = LayoutInflater.from(mContext);
        setImages(data);
    }

    public ImagePickerAdapter(Context mContext, List<ImageItem> data, int maxImgCount, int id) {
        this.mContext = mContext;
        this.itemId = id;
        this.maxImgCount = maxImgCount;
        this.mInflater = LayoutInflater.from(mContext);
        setImages(data);
    }

    public ImagePickerAdapter(Context mContext) {
        this.mContext = mContext;
    }

    @Override
    public SelectedPicViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new SelectedPicViewHolder(mInflater.inflate(itemId, parent, false));
    }

    @Override
    public void onBindViewHolder(SelectedPicViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public class SelectedPicViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private ImageView iv_img;
        private int clickPosition;

        public SelectedPicViewHolder(View itemView) {
            super(itemView);
            try {
                iv_img = itemView.findViewById(R.id.iv_img);
//                imgDelete = (ImageView) itemView.findViewById(R.id.iv_delete);

            } catch (Exception e) {
                e.printStackTrace();
                iv_img = (ImageView) itemView.findViewById(R.id.iv_img);
//                imgDelete = (ImageView) itemView.findViewById(R.id.iv_delete);
            }
        }

        public void bind(final int position) {
            itemView.setOnClickListener(this);

            //根据条目位置设置图片
            ImageItem item = mData.get(position);
            if (!TextUtils.isEmpty(item.path)) {
                ImagePicker.getInstance().getImageLoader().displayImage((Activity) mContext, item.path, iv_img, 0, 0);
            }
            if (isAdded && position == getItemCount() - 1) {
                clickPosition = -1;
            } else {
                if (!TextUtils.isEmpty(item.path)) {
                    ImagePicker.getInstance().getImageLoader().displayImage((Activity) mContext, item.path, iv_img, 0, 0);
                }
                clickPosition = position;
            }

        }

        @Override
        public void onClick(View v) {
            if (listener != null)
                listener.onItemClick(v, clickPosition);
        }
    }
}