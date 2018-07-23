package com.heshi.niuniu.util;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by min on 16/10/25.
 */

public class DateUtil {
    /**
     * 获取日期
     *
     * @param date
     * @return
     */
    public static String getTime1(Date date) {
        //        long time=System.currentTimeMillis();//long now = android.os.SystemClock.uptimeMillis();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String t1 = format.format(date);
        return t1;
    }

    /**
     * 获取月份
     *
     * @param date
     * @return
     */
    public static String getMouth(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("MM");
        String t1 = format.format(date);
        return t1;
    }

    /**
     * 获取天
     *
     * @param date
     * @return
     */
    public static String getDay(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("dd");
        String t1 = format.format(date);
        return t1;
    }

    /**
     * 获取上一个月时间
     *
     * @param date
     * @return
     */
    public static String getLastDate(Date date) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        System.out.println("当前时间是：" + dateFormat.format(date));

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date); // 设置为当前时间
        calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH) - 1); // 设置为上一个月
        date = calendar.getTime();
        String lastDate = dateFormat.format(date);
        return lastDate;
    }


    /**
     * 获取年份
     *
     * @param date
     * @return
     */
    public static String getYear(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy");
        String t1 = format.format(date);
        return t1;
    }

    /**
     * 获取 年月
     *
     * @param date
     * @return
     */
    public static String getYearMonth(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy");
        String t1 = format.format(date);
        return t1;
    }

    /**
     * 获取 年月
     *
     * @param date
     * @return
     */
    public static String getMonth(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("MM");
        String t1 = format.format(date);
        return t1;
    }

    /**
     * 获取小时
     *
     * @param date
     * @return
     */
    public static String getHours(Date date) {
        SimpleDateFormat format = new SimpleDateFormat(" HH");
        String t1 = format.format(date);
        return t1;
    }


    /**
     * 获取小时分钟
     *
     * @param date
     * @return
     */
    public static String getHour(Date date) {
        SimpleDateFormat format = new SimpleDateFormat(" HH:mm");
        String t1 = format.format(date);
        return t1;
    }

    /**
     * 获取小时分钟秒
     *
     * @param date
     * @return
     */
    public static String getHourS(Date date) {
        SimpleDateFormat format = new SimpleDateFormat(" HH:mm:ss");
        String t1 = format.format(date);
        return t1;
    }

    /**
     * 获取年月日
     *
     * @param date
     * @return
     */
    public static String getHourY(Date date) {
        SimpleDateFormat format = new SimpleDateFormat(" yyyy-MM-dd HH:mm:ss");
        String t1 = format.format(date);
        return t1;
    }

    /**
     * 获取月日十分
     *
     * @param date
     * @return
     */
    public static String getMonthDHS(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("MM-dd HH:mm");
        String t1 = format.format(date);
        return t1;
    }


    /**
     * 时间转换
     *
     * @param str
     * @param month
     * @return
     */
    public static String dateConvation(String str, int month) {
        SimpleDateFormat sf1 = new SimpleDateFormat("EEE MMM dd hh:mm:ss z yyyy", Locale.ENGLISH);
        Date date = null;
        Date d2 = null;
        try {
            date = sf1.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        SimpleDateFormat sf2 = new SimpleDateFormat("yyyy-MM-dd");
        try {
            //            sf2.parse(str).getMonth();
            //           sf2.parse(str).getTime();

            Date d1 = sf2.parse(sf2.format(date));
            Calendar g = Calendar.getInstance();
            g.setTime(d1);
            g.add(Calendar.MONTH, month);
            d2 = g.getTime();

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return sf2.format(d2);
    }


    /**
     * @param d
     * @param hour
     * @param minute
     * @param second
     * @return
     */
    public static Date SetTime(Date d, int hour, int minute, int second) {
        Calendar c = Calendar.getInstance();
        c.setTime(d);
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);
        return new Date(year - 1900, month, day, hour, minute, second);
    }


    public static int[] getYMD(Date date, int type) {
        SimpleDateFormat sdf = null;
        switch (type) {
            case 0:
                sdf = new SimpleDateFormat("yyyy-MM-dd");

                break;
            case 1:
                sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");

                break;
        }
        Date d1 = null;
        try {
            d1 = sdf.parse(sdf.format(date));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(d1);

        return new int[]{cal.get(Calendar.YEAR), cal.get(Calendar.MONTH) + 1, cal.get(Calendar.DATE), cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE)};
    }


    /**
     * 获取年月
     *
     * @param str
     * @return
     */
    public static Date getYearAndMonthDate(String str) {
        SimpleDateFormat format = new SimpleDateFormat("YY-MM");
        Date date1 = null;
        try {
            date1 = format.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date1;

    }

    /**
     * 获取年月日
     *
     * @param str
     * @return
     */
    public static Date getYearAndMonthDayDate(String str) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = sdf.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;

    }


    public static Date stringToDate(String str, int type) {
        SimpleDateFormat format = null;
        switch (type) {
            case 0:
                format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                break;
            case 1:
                format = new SimpleDateFormat("yyyy-MM-dd");
                break;
        }

        Date date1 = null;
        try {
            date1 = format.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();

        }
        return date1;

    }

    public static Date getMonthDate(String str) {
        SimpleDateFormat format = new SimpleDateFormat("MM");
        Date date1 = null;
        try {
            date1 = format.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date1;

    }

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");//小写的mm表示的是分钟


    /**
     * 年月日 正规格格式
     * 例：2017-05-01
     * 由Data 转换 为标准格式
     *
     * @param timeArr
     */
    public String refreshTime(int[] timeArr) {
        return timeArr[0] + "-" + (timeArr[1] < 10 ? "0" : "") + timeArr[1] + "-" +
                (timeArr[2] < 10 ? "0" : "") + timeArr[2];
    }


    /**
     * 比较两个日期的大小，日期格式为yyyy-MM-dd
     *
     * @param str1 the first date
     * @param str2 the second date
     * @return true <br/>false
     */
    public static boolean isDate2Bigger(String str1, String str2) {
        int[] data = CalendarUtil.getYMD(new Date());
        String time = data[0] + "-" + data[1] + "-" + data[2];

        boolean isBigger = false;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date dt1 = null;
        Date dt2 = null;
        try {
            if (TextUtils.isEmpty(str1)) {
                str1 = time;
            }

            if (TextUtils.isEmpty(str2)) {
                str2 = time;
            }

            dt1 = sdf.parse(str1);
            dt2 = sdf.parse(str2);
        } catch (android.net.ParseException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        if (dt1.getTime() > dt2.getTime()) {
            isBigger = false;
        } else if (dt1.getTime() <= dt2.getTime()) {
            isBigger = true;
        }
        return isBigger;
    }

    public static String getMonthFristDay() {
        // 获取当月第一天和最后一天
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        String firstday, lastday;

        Calendar cale = Calendar.getInstance();
        cale.add(Calendar.MONTH, 0);
        cale.set(Calendar.DAY_OF_MONTH, 1);
        firstday = format.format(cale.getTime());

        return firstday;
    }

    public static Date getStringToDate(String str) {
        Date date = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//小写的mm表示的是分钟
            date = sdf.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
            date = new Date();
        }
        return date;
    }

    /**
     * //  //2017-10-31 17:30:00
     *
     * @param
     * @return
     */

    public static boolean isBeAmLate(Date date, boolean isAm) {
        try {
            SimpleDateFormat hourFormat = new SimpleDateFormat("HH:mm:00");
            String format1 = hourFormat.format(date);
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");//小写的mm表示的是分钟
            if (isAm) {
                if (sdf.parse(format1).getTime() - sdf.parse("09:01:00").getTime() >= 0) {
                    return true;//上午 未迟到   下午 迟到
                } else {
                    return false;
                }
            } else {
                if (sdf.parse(format1).getTime() - sdf.parse("17:30:00").getTime() >= 0) {
                    return false;//上午 未迟到   下午 迟到
                } else {
                    return true;
                }
            }
        } catch (ParseException e) {
            e.printStackTrace();
            return false;
        }


    }


    public static Date getStringToDateCn(String str) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy 年 MM 月 dd 日 HH:mm:ss");//小写的mm表示的是分钟
        Date date = sdf.parse(str);
        return date;
    }

    /**
     * 日期天数判断
     * 动态计算时间长短
     *
     * @param oldTime
     */
    public static int getDyDayUtil(String oldTime) throws ParseException {

        //设置多少分钟之前（别删除还有用）
        SimpleDateFormat d = new SimpleDateFormat("yyyy-MM-dd");// 格式化时间
        String nowTime = d.format(Calendar.getInstance().getTime());// 按以上格式 将当前时间转换成字符串

        long result;
        String newData = null;

        result = d.parse(nowTime).getTime() - d.parse(oldTime).getTime();

        newData = result / 3600000 / 24 + "";
        return Integer.valueOf(newData);
    }

    /**
     * string转Date
     *
     * @param dstr
     * @return
     */
    public static Date strToDate(String dstr) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//小写的mm表示的是分钟
            Date date = sdf.parse(dstr);
            return date;
        } catch (ParseException e) {
            e.printStackTrace();
            return new Date();


        }
    }

    /**
     * string转Date
     *
     * @param dstr
     * @return
     */
    public static Date strToDate(String dstr, int type) {
        try {
            String format = null;
            switch (type) {
                case 1:
                    format = "yyyy-MM-dd";
                    break;
                case 2:
                    format = "MM.dd";
                    break;
                case 3:
                    format = "HH:mm";
                    break;
                case 4:
                    format = "mm:ss";
                    break;
                case 5:
                    format = "MM-dd HH:mm";
                    break;
                case 6:
                    format = "yyyy-MM";
                    break;
                case 7:
                    format = "yyyy年MM月";
                    break;

                case 8:
                    format = "MM";
                    break;
                case 9:
                    format = "yyyy-MM-dd HH:mm";
                    break;
                case 10:
                    format = "MM-dd";
                    break;
                case 0:
                    format = "yyyy/MM/dd";
                    break;
            }
            SimpleDateFormat sdf = new SimpleDateFormat(format);//小写的mm表示的是分钟
            Date date = sdf.parse(dstr);
            return date;
        } catch (ParseException e) {
            e.printStackTrace();
            return new Date();


        }
    }

    /**
     * Date 转str
     *
     * @param date
     * @param type
     * @return
     */
    public static String formatDate(Date date, int type) {
        String format = null;

        switch (type) {
            case 0:
                format = "yyyy/MM/dd";
                break;
            case 1:
                format = "yyyy-MM-dd";
                break;
            case 2:
                format = "MM.dd";
                break;
            case 3:
                format = "HH:mm";
                break;
            case 4:
                format = "mm:ss";
                break;
            case 5:
                format = "MM-dd HH:mm";
                break;
            case 6:
                format = "yyyy-MM";
                break;
            case 7:
                format = "yyyy年MM月";
                break;

            case 8:
                format = "MM";
                break;
            case 9:
                format = "yyyy-MM-dd HH:mm";
                break;
            case 10:
                format = "MM-dd";
                break;
            case 11:
                format = "yyyy年MM月dd日";
                break;
        }
        SimpleDateFormat d = new SimpleDateFormat(format);// 格式化时间
        String nowtime = d.format(date);// 按以上格式 将当前时间转换成字符串
        return nowtime;
    }

    /**
     * 获取月日十分
     *
     * @param date
     * @return
     */
    public static String getMonthDHSCn(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
        String t1 = format.format(date);
        return t1;
    }

    public static Calendar strToCalendar(String str) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = sdf.parse(str);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar;
    }

    public static String getNextDayStr(Calendar calendar, int nextDayNum) {
        //使用roll方法进行回滚到后一天的时间
        //cl.roll(Calendar.DATE, 1);
        //使用set方法直接设置时间值
        int day = calendar.get(Calendar.DATE);
        calendar.set(Calendar.DATE, day + nextDayNum);
        return calendarToDate(calendar);
    }

    /**
     * @param calendar
     * @return
     */
    public static String calendarToDate(Calendar calendar) {
        Date date = calendar.getTime();
        return formatDate(date, 1);
    }

    public static String getLastDate(String date) throws ParseException {
        Calendar calendar = strToCalendar(date);
        calendar.add(Calendar.MONTH, -1);
        SimpleDateFormat format = new SimpleDateFormat("yyyy年MM");
        String time = format.format(calendar.getTime());
        return time;
    }

    public static int compareDate(String DATE1, String DATE2) {

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date dt1 = df.parse(DATE1);
            Date dt2 = df.parse(DATE2);
            if (dt1.getTime() > dt2.getTime()) {
                System.out.println("dt1 在dt2前");
                return 1;
            } else if (dt1.getTime() < dt2.getTime()) {
                System.out.println("dt1在dt2后");
                return -1;
            } else {
                return 0;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return 0;
    }

    public static boolean isDataBagger(String data1, String data2) {

        DateFormat df = new SimpleDateFormat("HH:mm:ss");//创建日期转换对象HH:mm:ss为时分秒，年月日为yyyy-MM-dd
        try {
            Date dt1 = df.parse(data1);//将字符串转换为date类型
            Date dt2 = df.parse(data2);
            if (dt1.getTime() > dt2.getTime())//比较时间大小,如果dt1大于dt2
            {
                return true;
            } else {
                return false;
            }
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return false;

    }

    public static boolean isCompreDate(String clickDate) {
        String nowDate = DateUtil.getNowDate();

        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");//创建日期转换对象HH:mm:ss为时分秒，年月日为yyyy-MM-dd
        try {
            Date dt1 = df.parse(nowDate);//将字符串转换为date类型
            Date dt2 = df.parse(clickDate);
            if (dt1.getTime() > dt2.getTime())//比较时间大小,如果dt1大于dt2
            {
                return true;
            } else {
                return false;
            }
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return false;

    }

    /**
     * 访问上一天日志
     *
     * @param second
     * @return
     * @throws ParseException
     */
    public static String getPreDate(long second, int n, String time) {

        try {
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");//创建日期转换对象HH:mm:ss为时分秒，年月日为yyyy-MM-dd
            if (time != null) {
                Date parse = df.parse(time);
                second = parse.getTime();
            }
            long millisecond = second - n * 24 * 60 * 60 * 1000;
            Date date = null;
            date = new Date(millisecond);
            String format = df.format(date);

            return format;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }

    }

    /**
     * 访问上一天日志
     *
     * @param second
     * @return
     * @throws ParseException
     */
    public static String getPreListDate(long second, int n, String time) {
        String list = "";
        try {
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");//创建日期转换对象HH:mm:ss为时分秒，年月日为yyyy-MM-dd
            if (time != null) {
                Date parse = df.parse(time);
                second = parse.getTime();
            }
            for (int i = n; i >= 0; i--) {
                long millisecond = second - i * 24 * 60 * 60 * 1000;
                Date date = null;
                date = new Date(millisecond);
                String format = df.format(date);
                if (i != 0) {
                    list += format + ",";
                } else {
                    list += format;
                }
            }

            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return list;
        }

    }

    /**
     * 访问上一天日志
     *
     * @param second
     * @return
     * @throws ParseException
     */
    public static List<String> getListDate(long second, int n, String time) {
        List<String> list = new ArrayList<>();
        try {
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");//创建日期转换对象HH:mm:ss为时分秒，年月日为yyyy-MM-dd
            if (time != null) {
                Date parse = df.parse(time);
                second = parse.getTime();
            }
           /* for (int i = 0; i < n; i++) {
                long millisecond = second - i * 24 * 60 * 60 * 1000;
                Date date = new Date(millisecond);
                String format = df.format(date);
                list.add(format);
            }*/
            for (int i = n; i >= 0; i--) {
                long millisecond = second - i * 24 * 60 * 60 * 1000;
                Date date = new Date(millisecond);
                String format = df.format(date);
                list.add(format);
                Log.e("format", format);
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;

    }

    /**
     * 设置时间
     *
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
     *
     * @param cl
     * @return
     */
    public static Calendar getBeforeDay(Calendar cl) {
        //使用roll方法进行向前回滚
        //cl.roll(Calendar.DATE, -1);
        //使用set方法直接进行设置
        int day = cl.get(Calendar.DATE);
        cl.set(Calendar.DATE, day - 1);
        return cl;
    }

    /**
     * 获取当前时间的后一天时间
     *
     * @param cl
     * @return
     */
    public static Calendar getAfterDay(Calendar cl) {
        //使用roll方法进行回滚到后一天的时间
        //cl.roll(Calendar.DATE, 1);
        //使用set方法直接设置时间值
        int day = cl.get(Calendar.DAY_OF_MONTH);
        cl.set(Calendar.DAY_OF_MONTH, day + 1);
        return cl;
    }

    /**
     * 打印时间
     *
     * @param cl
     */
    public static String printCalendar(Calendar cl) {
        int year = cl.get(Calendar.YEAR);
        int month = cl.get(Calendar.MONTH) + 1;
        int day = cl.get(Calendar.DAY_OF_MONTH);
        return year + "-" + month + "-" + day;
    }


    /**
     * 访问下一天日志
     *
     * @param second
     * @return
     * @throws ParseException
     */
    public static String getNextListDate(long second, int n, String time) {
        String list = "";
        try {
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");//创建日期转换对象HH:mm:ss为时分秒，年月日为yyyy-MM-dd
            if (time != null) {
                Date parse = df.parse(time);
                second = parse.getTime();
            }
            for (int i = 0; i < n; i++) {

                long millisecond = second + i * 24 * 60 * 60 * 1000;
                if (millisecond <= new Date().getTime()) {
                    Date date = null;
                    date = new Date(millisecond);
                    String format = df.format(date);
                    if (i != n - 1) {
                        list += format + ",";
                    } else {
                        list += format;
                    }
                }


            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
            return list;
        }


    }

    /**
     * 访问下一天日志
     *
     * @param second
     * @return
     * @throws ParseException
     */
    public static String getNextDate(long second, int n, Context context) {

        try {
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");//创建日期转换对象HH:mm:ss为时分秒，年月日为yyyy-MM-dd
            long millisecond = second + n * 24 * 60 * 60 * 1000;
            if (millisecond > new Date().getTime()) {
                millisecond = new Date().getTime();
                ToashUtils.show(context, "不能访问未来日志");
            }
            Date date = null;
            date = new Date(millisecond);
            String format = df.format(date);

            return format;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }

    }

    /**
     * 访问下一天日志
     *
     * @param
     * @return
     * @throws ParseException
     */
    public static String getNowDate() {

        try {
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");//创建日期转换对象HH:mm:ss为时分秒，年月日为yyyy-MM-dd
            Date date = new Date();

            String format = df.format(date);

            return format;
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }

    }

    public static long getNowYMDDate() {

        try {
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");//创建日期转换对象HH:mm:ss为时分秒，年月日为yyyy-MM-dd
            Date date = new Date();

            String format = df.format(date);
            Date parse = df.parse(format);

            return parse.getTime();
        } catch (Exception e) {
            e.printStackTrace();
            return new Date().getTime();
        }

    }

    public static long getMilliSecond(Date second) throws ParseException {
        return second.getTime();
    }

    public static long getMilliSecond(String second) {
        try {
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");//创建日期转换对象HH:mm:ss为时分秒，年月日为yyyy-MM-dd
            Date date = df.parse(second);
            long millisecond = date.getTime();
            return millisecond;
        } catch (ParseException e) {
            e.printStackTrace();
            return new Date().getTime();
        }
    }

    /**
     * 判断当前日期是星期几
     *
     * @param pTime 设置的需要判断的时间  //格式如2012-09-08
     * @return dayForWeek 判断结果
     * @Exception 发生异常
     */

//  String pTime = "2012-03-12";
    public static String getWeek(String pTime) {

        String Week = "周";

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        try {

            c.setTime(format.parse(pTime));

        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        if (c.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY) {
            Week += "日";
        }
        if (c.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY) {
            Week += "一";
        }
        if (c.get(Calendar.DAY_OF_WEEK) == Calendar.TUESDAY) {
            Week += "二";
        }
        if (c.get(Calendar.DAY_OF_WEEK) == Calendar.WEDNESDAY) {
            Week += "三";
        }
        if (c.get(Calendar.DAY_OF_WEEK) == Calendar.THURSDAY) {
            Week += "四";
        }
        if (c.get(Calendar.DAY_OF_WEEK) == Calendar.FRIDAY) {
            Week += "五";
        }
        if (c.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) {
            Week += "六";
        }

        return Week;
    }

}
