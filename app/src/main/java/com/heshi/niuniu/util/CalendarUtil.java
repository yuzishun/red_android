package com.heshi.niuniu.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * Created by codbking on 2016/6/1.
 */
public class CalendarUtil {

    //获取一月的第一天是星期几
    public static int getDayOfWeek(int y, int m, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(y, m - 1, day);
        return calendar.get(Calendar.DAY_OF_WEEK);
    }

    //获取一月最大天数
    public static int getDayOfMaonth(int y, int m) {
        Calendar cal = Calendar.getInstance();
        cal.set(y, m - 1, 1);
        int dateOfMonth = cal.getActualMaximum(Calendar.DATE);
        return dateOfMonth;
    }

    public static int getMothOfMonth(int y, int m) {
        Calendar cal = Calendar.getInstance();
        cal.set(y, m - 1, 1);
        int dateOfMonth = cal.get(Calendar.MONTH);
        return dateOfMonth + 1;
    }

    public static int[] getYMD(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return new int[]{cal.get(Calendar.YEAR), cal.get(Calendar.MONTH) + 1, cal.get(Calendar.DATE)};
    }

    /**
     * 设置时间
     * @param year
     * @param month
     * @param date
     * @return
     */
    public static Calendar setCalendar(int year, int month, int date) {
        Calendar cl = Calendar.getInstance();
        cl.set(year, month - 1, date);
        return cl;
    }

    /**
     * 获取当前时间的前一天时间
     * @param cl
     * @return
     */
    public static Calendar getBeforeDay(Calendar cl, int n) {
        //使用roll方法进行向前回滚
        //cl.roll(Calendar.DATE, -1);
        //使用set方法直接进行设置
        int day = cl.get(Calendar.DATE);
        cl.set(Calendar.DATE, day - n);
        return cl;
    }

    /**
     * 两日期差几个月
     * @param
     * @return
     */
    public static int getMonthSpace(Date date1, Date date2) {
        int result = 0;

        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        c1.setTime(date1);
        c2.setTime(date2);
        result = c2.get(Calendar.MONTH) - c1.get(Calendar.MONTH);
        return Math.abs(result);
    }

    public static Date getSupportBeginDayofMonth(int year, int monthOfYear) {
        Calendar cal = Calendar.getInstance();
        // 不加下面2行，就是取当前时间前一个月的第一天及最后一天
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, monthOfYear);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);

        cal.add(Calendar.DAY_OF_MONTH, -1);
        Date lastDate = cal.getTime();

        return lastDate;
    }

    public static int[] getIntArray(Date date) {
        return CalendarUtil.getYMD(date);
    }

    /**
     * 获取当前时间的后一天时间
     * @param cl
     * @return
     */
    public static Calendar getAfterDay(Calendar cl) {
        //使用roll方法进行回滚到后一天的时间
        //cl.roll(Calendar.DATE, 1);
        //使用set方法直接设置时间值
        int day = cl.get(Calendar.DATE);
        cl.set(Calendar.DATE, day + 1);
        return cl;
    }

    /**
     * 打印时间
     * @param cl
     */
    public static String printCalendar(Calendar cl) {
        int year = cl.get(Calendar.YEAR);
        int month = cl.get(Calendar.MONTH) + 1;
        int day = cl.get(Calendar.DATE);
        return year + "-" + month + "-" + day;
    }

    /**
     * whenDate 几天
     * after   true 之后/ false 之前
     * @return 今天往前/往后n天时间集合
     */
    public static List<String> getStatetime(int whenDate, boolean after, String time, Date date) {
        List<String> lDate = new ArrayList();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        if (time != null) {
            try {
                date = sdf.parse(time);
            } catch (ParseException e) {
                e.printStackTrace();
                date = new Date();
            }
        }
        c.setTime(date);

        for (int i = 0; i < whenDate; i++) {
            if (i == 0) {
                c.add(Calendar.DATE, 0);
            } else {
                if (after) {
                    c.add(Calendar.DATE, 1);
                } else {
                    c.add(Calendar.DATE, -1);
                }
            }

            Date monday = c.getTime();
            String preMonday = sdf.format(monday);
            lDate.add(preMonday);
        }
        if (!after) {
            Collections.reverse(lDate);
        }

        return lDate;
    }

    public static String getStringTime(int whenDate, boolean after, String time, Date date) {
        String str = "";

        for (String s : getStatetime(whenDate, after, time, date)) {
            try {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                Date parse = sdf.parse(s);
                if (parse.getTime() <= new Date().getTime()) {
                    str += s + ",";
                }

            } catch (ParseException e) {
                e.printStackTrace();
            }


        }
        return str;
    }

    public static List<Date> findDates(Date dBegin, Date dEnd) {
        List lDate = new ArrayList();
        lDate.add(dBegin);
        Calendar calBegin = Calendar.getInstance();
        // 使用给定的 Date 设置此 Calendar 的时间
        calBegin.setTime(dBegin);
        Calendar calEnd = Calendar.getInstance();
        // 使用给定的 Date 设置此 Calendar 的时间
        calEnd.setTime(dEnd);
        // 测试此日期是否在指定日期之后
        while (dEnd.after(calBegin.getTime())) {
            // 根据日历的规则，为给定的日历字段添加或减去指定的时间量
            calBegin.add(Calendar.DAY_OF_MONTH, 1);
            lDate.add(calBegin.getTime());
        }
        return lDate;
    }
}

