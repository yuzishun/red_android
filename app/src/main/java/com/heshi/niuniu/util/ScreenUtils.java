package com.heshi.niuniu.util;

import android.app.Dialog;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.List;


/**
 * Name: ScreenUtils
 * Author: FrameJack
 * Email: framejackname@gmail.com
 * Date: 2017-12-29 10:52
 * Desc:
 * Comment: //TODO
 */
public class ScreenUtils {

    /**
     * 开始时间
     */
    private TextView mTextPendingStartTimeiew;
    private RelativeLayout mRelPendingStartTimeiew;
    /**
     * 结束时间
     */
    private TextView mTextPendingEndTimeiew;
    private RelativeLayout mRelPendingEndTimeiew;
    /**
     * 申请人
     */
    private EditText mEditPendingProposeriew;
    /**
     * 项目申请单
     */
    private TextView mTextPendingItemTypeiew;
    private RelativeLayout mRelPendingItemTypeiew;
    /**
     * 确定
     */
    private TextView mTextPendingConfirmiew;
    /**
     * 重置
     */
    private TextView mTextPendingResetiew;

    private String oneStartTime = "";//开始时间
    private String oneEndTime = "";//结束时间
    private String oneApplicant = "";//申请人
    private int oneProjectItem = -1;//项目类型
    private String fristDay;
    private String time;
    private String companyName;

    private PopupWindow popupWindow;
    private AppCompatActivity mContext;
    private List<String> list;
    public Dialog myDialog;
    private int START_TYPE = 1;
    private int END_TYPE = 2;
    public static ScreenUtils utils;
    private EditText companyEdit;
    private Listener listener;

    public interface Listener {
        void setData(String startTime, String endTime, String applicant, int item, String company);

        void setDialog(Dialog myDialog);
    }


    public static ScreenUtils getInstance() {
        if (utils == null) {
            utils = new ScreenUtils();
        }
        return utils;
    }



}
