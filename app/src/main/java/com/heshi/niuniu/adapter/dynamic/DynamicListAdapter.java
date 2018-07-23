package com.heshi.niuniu.adapter.dynamic;

import android.content.Context;
import android.opengl.Visibility;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.heshi.niuniu.R;
import com.heshi.niuniu.app.Constants;
import com.heshi.niuniu.model.DynamicListModel;
import com.heshi.niuniu.util.GlideUtils;
import com.heshi.niuniu.util.recyclerview.BaseMyRecyclerVIewAdapter;
import com.heshi.niuniu.util.recyclerview.BaseViewHolder;
import com.lzy.ninegrid.ImageInfo;
import com.lzy.ninegrid.NineGridView;
import com.lzy.ninegrid.preview.NineGridViewClickAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/7/17 0017.
 */

public class DynamicListAdapter extends BaseMyRecyclerVIewAdapter<DynamicListModel> {

    private setPopListener setPopListener;

    public interface setPopListener {
        void setComment(View view, int position);

    }

    public DynamicListAdapter(Context context, List<DynamicListModel> list) {
        super(context, list, R.layout.item_dynamic);
    }

    @Override
    protected void covert(DynamicListModel mode, BaseViewHolder holder, final int position) {
        super.covert(mode, holder, position);
        String imgUrl = Constants.Im_Url + Constants.Dynamic_Head_url + mode.getUser_name();

        ImageView head = holder.getView(R.id.img_item_dynamic, position);

        GlideUtils.loadImg(imgUrl, head, R.mipmap.icon_img_error);

        holder.setText(R.id.text_item_dynamic_name, mode.getFriend_nick());
        View comment = holder.getView(R.id.text_dynamic_comment);
        TextView delete = holder.getView(R.id.text_dynamic_delete);
        delete.setVisibility(Constants.userName.equals(mode.getUser_name()) ? View.VISIBLE : View.GONE);

        TextView content = holder.getView(R.id.text_item_dynamic_content);
        content.setVisibility(TextUtils.isEmpty(mode.getTxt_meta()) ? View.GONE : View.VISIBLE);
        content.setText(mode.getTxt_meta());

        NineGridView nineGridView = holder.getView(R.id.nineGrid);

        ArrayList<ImageInfo> imageInfo = new ArrayList<>();
        imageInfo.clear();

        List<String> imageDetails = mode.getUrlList();


        if (imageDetails != null) {
            for (String imageDetail : imageDetails) {
                ImageInfo info = new ImageInfo();
                info.setThumbnailUrl(imageDetail);
                info.setBigImageUrl(imageDetail);
                imageInfo.add(info);
            }
        }

        nineGridView.setAdapter(new NineGridViewClickAdapter(mContext, imageInfo));
        comment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (setPopListener != null) {
                    setPopListener.setComment(view, position);
                }
            }
        });
    }

    public DynamicListAdapter.setPopListener getSetPopListener() {
        return setPopListener;
    }

    public void setSetPopListener(DynamicListAdapter.setPopListener setPopListener) {
        this.setPopListener = setPopListener;
    }
}
