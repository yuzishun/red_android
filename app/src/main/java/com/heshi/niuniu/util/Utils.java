package com.heshi.niuniu.util;

import android.content.Context;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/**
 * Name: Utils
 * Author: FrameJack
 * Email: framejackname@gmail.com
 * Date: 2017-09-06 14:37
 * Desc:
 * Comment: //TODO
 */
public class Utils<T> {
    public static String time;

    public static void setEditEnd(EditText edit) {
        //设置数据
        int length = edit.getText().toString().length();
        edit.setSelection(length);
    }

    /**
     * 软键盘显示和隐藏
     * @param context
     * @param view
     * @param status
     */
    public static void setSoftInputStatus(Context context, EditText view, boolean status) {
        view.requestFocus();
        view.setFocusable(true);
        view.setFocusableInTouchMode(true);

        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (status) {
            imm.showSoftInput(view, InputMethodManager.SHOW_FORCED);
        } else {
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0); //强制隐藏键盘
        }
    }

    public static boolean checkChineseCharacter(String str) {
        if (str.getBytes().length == str.length())
            return false;
        return true;
    }


    //功能去重复的数据
    public List<T> removeDuplicate(
            List<T> list) {
        @SuppressWarnings("rawtypes")
        Set set = new LinkedHashSet<>();
        set.addAll(list);
        list.clear();
        list.addAll(set);
        return list;
    }

    /**
     * list集合变成str
     * @param list
     * @param separator
     * @return
     */
    public static String listToString(List list, char separator) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            sb.append(list.get(i)).append(separator);
        }
        return list.isEmpty() ? "" : sb.toString().substring(0, sb.toString().length() - 1);
    }

    //string转list集合，使用，进行分隔
    public static List<String> stringToList(String strs){
        String str[] = strs.split(",");
        return Arrays.asList(str);
    }


}
