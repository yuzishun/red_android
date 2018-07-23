package com.heshi.niuniu.adapter;

import android.content.Context;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RelativeLayout;


import com.heshi.niuniu.R;
import com.heshi.niuniu.app.Constants;
import com.heshi.niuniu.model.ContactModel;
import com.heshi.niuniu.util.GlideUtils;
import com.heshi.niuniu.util.recyclerview.BaseMyRecyclerVIewAdapter;
import com.heshi.niuniu.util.recyclerview.BaseViewHolder;
import com.mikhaellopez.circularimageview.CircularImageView;

import java.util.HashMap;
import java.util.List;

/**
 * Created by min on 2017/5/8.
 * 全部联系
 */

public class ContactAllAdapter extends BaseMyRecyclerVIewAdapter<ContactModel> {
    public HashMap<String, Integer> keyMap;

    public ContactAllAdapter(Context context, List<ContactModel> list) {
        super(context, list, R.layout.item_contact_all_rv);
        keyMap = new HashMap<>();
    }

    @Override
    protected void covert(ContactModel mode, BaseViewHolder holder, int position) {
        super.covert(mode, holder, position);
        Integer exitsPostion = keyMap.get(mode.getSortLetters());

        boolean isFristKey = (exitsPostion == null || exitsPostion.equals(position));
        if (isFristKey) {
            exitsPostion = position;
        }
        keyMap.put(mode.getSortLetters(), exitsPostion);
        holder.getView(R.id.rlHeader).setVisibility(isFristKey ? View.VISIBLE : View.GONE);
        holder.setText(R.id.tvHeader,mode.getSortLetters());

        ImageView mImage = holder.getView(R.id.item_contact_icon,position);
        GlideUtils.getPersonCache(mode.getHard_pic(), mImage, Constants.USER_PORTRAIT);
        //   联系人名称
        holder.setText(R.id.item_contact_all_name, mode.getFriend_nick());
    }

}
