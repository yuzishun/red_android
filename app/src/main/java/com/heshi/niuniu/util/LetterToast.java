package com.heshi.niuniu.util;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.heshi.niuniu.R;


/**
 * Created by Min on 2017/5/10.
 * 字母吐司
 */

public class LetterToast extends Toast {

    private TextView textView;

    /**
     * Construct an empty Toast object.  You must call {@link #setView} before you
     * can call {@link #show}.
     *
     * @param context The context to use.  Usually your {@link Application}
     *                or {@link Activity} object.
     */

    public LetterToast(Context context) {
        super(context);
        init(context);
    }

    public LetterToast setText(String str) {
        textView.setText(str);
        return this;
    }

    public void init(Context context) {
        // 获取LayoutInflater对象
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        // 由layout文件创建一个View对象
        View layout = inflater.inflate(R.layout.toast_letter, null);

        // 实例化ImageView和TextView对象
        textView = (TextView) layout.findViewById(R.id.toa_letter);

        setView(layout);
        setGravity(Gravity.CENTER_VERTICAL, 0, 0);
        setDuration(Toast.LENGTH_SHORT);
    }

    public static LetterToast builderToast(Context context, String content) {
        LetterToast mToast = new LetterToast(context).setText(content);
        return mToast;
    }

}

