package com.heshi.niuniu.adapter.msg;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;

import com.alibaba.mobileim.conversation.YWConversation;
import com.heshi.niuniu.R;
import com.heshi.niuniu.util.TimeUtil;
import com.heshi.niuniu.util.recyclerview.BaseMyRecyclerVIewAdapter;
import com.heshi.niuniu.util.recyclerview.BaseViewHolder;
import com.rey.material.widget.TextView;

import java.util.List;

import cn.bingoogolapple.badgeview.BGABadgeImageView;


/**
 * Created by Administrator on 2018/7/2 0002.
 */

public class MessageAdapter extends BaseMyRecyclerVIewAdapter<YWConversation> {

    public MessageAdapter(Context context, List<YWConversation> list) {
        super(context, list, R.layout.item_message);
    }

    @Override
    protected void covert(YWConversation mode, BaseViewHolder holder, int position) {
        super.covert(mode, holder, position);

        String date = new TimeUtil().getDateToString(mode.getLastestMessage().getTimeInMillisecond());

        if (!TextUtils.isEmpty(mode.getLastestMessage().getAuthorUserName())){
//            holder.setText(R.id.text_title, mode.getLastestMessage().get);

        }else {
            holder.setText(R.id.text_title, "");

        }
        holder.setText(R.id.text_msg, mode.getLastestMessage().getContent());
        holder.setText(R.id.text_item_last_time, date);

        holder.setText(R.id.text_msg_count, mode.getUnreadCount() + "");
        View view=holder.getView(R.id.text_msg_count);
        view.setVisibility( mode.getUnreadCount() == 0 ? View.INVISIBLE : View.VISIBLE);

//        BGABadgeImageView img = holder.getView(R.id.img_msg);
//        img.showTextBadge("23");
    }
}
