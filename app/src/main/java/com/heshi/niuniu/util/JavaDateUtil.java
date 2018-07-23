package com.heshi.niuniu.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import static com.heshi.niuniu.util.DateUtil.calendarToDate;
import static com.heshi.niuniu.util.DateUtil.formatDate;


/**
 * Name: JavaDateUtil
 * Author: FrameJack
 * Email: framejackname@gmail.com
 * Date: 2017-12-06 13:43
 * Desc:
 * Comment: //TODO
 */
public class JavaDateUtil {

    private static JavaDateUtil util;

    public static JavaDateUtil getInstance(){
        if (util==null){
            util=new JavaDateUtil();
        }
        return util;
    }


    /**
     * 获取当前月第一天
     *
     * @return
     */
    public String getCurrentFirstDay() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");// 格式化时间
        Calendar calendar1 = Calendar.getInstance();
        calendar1.add(Calendar.MONTH, -1);
        calendar1.set(Calendar.DAY_OF_MONTH, 1);
        String firstDay = sdf.format(calendar1.getTime());// 按以上格式 将当前时间转换成字符串
        return firstDay;
    }

    /**
     * 获取下一个月第一天
     *
     * @param calendar
     * @param nextDayNum
     * @return
     */
    public String getNextDayStr(Calendar calendar, int nextDayNum) {
        //使用roll方法进行回滚到后一天的时间
        //cl.roll(Calendar.DATE, 1);
        //使用set方法直接设置时间值
        int day = calendar.get(Calendar.DATE);
        calendar.set(Calendar.DATE, day + nextDayNum);
        return calendarToDate(calendar);
    }



    public String getLastMonthDay(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");// 格式化时间
        Calendar calendar2 = Calendar.getInstance();
        calendar2.set(Calendar.DAY_OF_MONTH, 0);
        String lastDay = sdf.format(calendar2.getTime());
        return lastDay;
    }

    public static String getDateOfLastMonth() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");// 格式化时间
        Calendar calendar1 = Calendar.getInstance();
        calendar1.add(Calendar.MONTH, -1);

        Calendar lastDate = (Calendar) calendar1.clone();
        return formatDate(lastDate.getTime(), 7);
    }

}
