package com.heshi.niuniu.adapter.contact;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.heshi.niuniu.R;
import com.heshi.niuniu.model.NewFriendModel;
import com.heshi.niuniu.util.recyclerview.BaseMyRecyclerVIewAdapter;
import com.heshi.niuniu.util.recyclerview.BaseViewHolder;

import java.util.List;

/**
 * Created by Administrator on 2018/7/17 0017.
 */

public class NewFriendAdapter extends BaseMyRecyclerVIewAdapter<NewFriendModel> {
    private operationLister lister;

    public interface operationLister {
        void agree(int position);
    }

    public NewFriendAdapter(Context context, List<NewFriendModel> list) {
        super(context, list, R.layout.item_new_friend);
    }

    @Override
    protected void covert(NewFriendModel mode, BaseViewHolder holder, final int position) {
        super.covert(mode, holder, position);

        holder.setText(R.id.text_item_name, mode.getFriend_nick());
        TextView msg = holder.getView(R.id.text_item_msg);
        TextView commit = holder.getView(R.id.text_item_commit);
        msg.setVisibility(View.GONE);
        commit.setVisibility(View.GONE);

        if (mode.getType() == 0) {
            commit.setVisibility(View.VISIBLE);
        } else if (mode.getType() == 1) {
            msg.setVisibility(View.VISIBLE);
        } else {
            msg.setVisibility(View.VISIBLE);
            msg.setText("已添加");
        }

        commit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (lister != null) {
                    lister.agree(position);
                }
            }
        });
    }

    public operationLister getLister() {
        return lister;
    }

    public void setLister(operationLister lister) {
        this.lister = lister;
    }
}
