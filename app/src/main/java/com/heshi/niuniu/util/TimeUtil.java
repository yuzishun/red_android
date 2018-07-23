package com.heshi.niuniu.util;

/**
 * Created by min on 2017/4/24.
 *  时间工具类
 */

import android.text.format.Time;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class TimeUtil {

    private SimpleDateFormat sf = null;
    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

    /**
     * 根据提供的年月日获取该月份的第一天
     * @Description: (这里用一句话描述这个方法的作用)
     * @param year
     * @param monthOfYear
     * @return
     */
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

        cal.set(Calendar.DAY_OF_MONTH, 1);
        Date firstDate = cal.getTime();
        return firstDate;
    }

    public static Date getSupportBeginDayofMonths(String year, String monthOfYear) {
        Calendar cal = Calendar.getInstance();
        // 不加下面2行，就是取当前时间前一个月的第一天及最后一天
        cal.set(Calendar.YEAR, Integer.parseInt(year));
        cal.set(Calendar.MONTH, Integer.parseInt(monthOfYear));
        cal.set(Calendar.DAY_OF_MONTH, 1);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);

        cal.add(Calendar.DAY_OF_MONTH, -1);
        Date lastDate = cal.getTime();

        cal.set(Calendar.DAY_OF_MONTH, 1);
        Date firstDate = cal.getTime();
        return firstDate;
    }


    /**
     * 根据提供的年月获取该月份的最后一天
     * @Description: (这里用一句话描述这个方法的作用)
     * @param year
     * @param monthOfYear
     * @return
     */
    public static Date getSupportEndDayofMonth(int year, int monthOfYear) {
        Calendar cal = Calendar.getInstance();
        // 不加下面2行，就是取当前时间前一个月的第一天及最后一天
        cal.set(Calendar.YEAR, year);
        cal.set(Calendar.MONTH, monthOfYear);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);


        cal.add(Calendar.DAY_OF_MONTH, -1);
        Date lastDate = cal.getTime();

        cal.set(Calendar.DAY_OF_MONTH, 1);
        Date firstDate = cal.getTime();
        return lastDate;
    }

    /**
     *  获取月头
     * @return
     */
    public String TheFirstMonth(){
        //获取当前月第一天：
        Calendar c = Calendar.getInstance();
        c.add(Calendar.MONTH, 0);
        c.set(Calendar.DAY_OF_MONTH,1);//设置为1号,当前日期既为本月第一天
        String first = format.format(c.getTime());
        System.out.println("===============first:"+first);
        return first;
//
//        //获取前月的第一天
//        Calendar cal_1=Calendar.getInstance();//获取当前日期
//        cal_1.add(Calendar.MONTH, -1);
//        cal_1.set(Calendar.DAY_OF_MONTH,1);//设置为1号,当前日期既为本月第一天
//        firstDay = format.format(cal_1.getTime());
//        System.out.println("-----1------firstDay:"+firstDay);
//        //获取前月的最后一天
//        Calendar cale = Calendar.getInstance();
//        cale.set(Calendar.DAY_OF_MONTH,0);//设置为1号,当前日期既为本月第一天
//        lastDay = format.format(cale.getTime());
//        System.out.println("-----2------lastDay:"+lastDay);

    }



    /**
     *  获取月尾
     * @return
     */
    public String TheLastMonth(){
        //获取当前月最后一天
        Calendar ca = Calendar.getInstance();
        ca.set(Calendar.DAY_OF_MONTH, ca.getActualMaximum(Calendar.DAY_OF_MONTH));
        String last = format.format(ca.getTime());
        System.out.println("===============last:"+last);
        return last;
    }



    /**
     * 获取当前时间
     *
     * @return
     */
    public String getNowTime() {
        String timeString = null;
        Time time = new Time();
        time.setToNow();
        String year = thanTen(time.year);
        String month = thanTen(time.month + 1);
        String monthDay = thanTen(time.monthDay);
        String hour = thanTen(time.hour);
        String minute = thanTen(time.minute);

        timeString = year + "-" + month + "-" + monthDay + " " + hour + ":"
                + minute;
        // System.out.println("-------timeString----------" + timeString);
        return timeString;
    }

    /**
     * 获取当前时间
     *
     * @return
     */
    public String getCurTime() {
        String timeString = null;
        Time time = new Time();
        time.setToNow();
        String year = thanTen(time.year);
        String month = thanTen(time.month + 1);
        String monthDay = thanTen(time.monthDay);
        String hour = thanTen(time.hour);
        String minute = thanTen(time.minute);

        timeString = year + month+ monthDay+ hour + minute;
        // System.out.println("-------timeString----------" + timeString);
        return timeString;
    }

    public int calculate(int year, int month) {

        boolean yearleap = judge(year);
        int day;
        if (yearleap && month == 2) {
            day = 29;
        } else if (!yearleap && month == 2) {
            day = 28;
        } else if (month == 4 || month == 6 || month == 9 || month == 11) {
            day = 30;
        } else {
            day = 31;
        }
        return day;
    }

    public boolean judge(int year) {
        boolean yearleap = (year % 400 == 0) || (year % 4 == 0)
                && (year % 100 != 0);// 采用布尔数据计算判断是否能整除
        return yearleap;
    }

    /**
     * 十一下加零
     *
     * @param str
     * @return
     */
    public String thanTen(int str) {

        String string = null;

        if (str < 10) {
            string = "0" + str;
        } else {

            string = "" + str;

        }
        return string;
    }

    /**
     * 计算时间差
     *
     * @param starTime
     *            开始时间
     * @param endTime
     *            结束时间
     *            返回类型 ==1----天，时，分。 ==2----时
     * @return 返回时间差
     */
    public static String getTimeDifference(String starTime, String endTime) {
        String timeString = "";
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");//yyyy-MM-dd HH:mm

        try {
            Date parse = dateFormat.parse(starTime);
            Date parse1 = dateFormat.parse(endTime);

            long diff = parse1.getTime() - parse.getTime();

            long day = diff / (24 * 60 * 60 * 1000);
            long hour = (diff / (60 * 60 * 1000) - day * 24);
            long min = ((diff / (60 * 1000)) - day * 24 * 60 - hour * 60);
            long s = (diff / 1000 - day * 24 * 60 * 60 - hour * 60 * 60 - min * 60);
            long ms = (diff - day * 24 * 60 * 60 * 1000 - hour * 60 * 60 * 1000
                    - min * 60 * 1000 - s * 1000);
            // System.out.println(day + "天" + hour + "小时" + min + "分" + s +
            // "秒");
            long hour1 = diff / (60 * 60 * 1000);
            String hourString = hour1 + "";
            long min1 = ((diff / (60 * 1000)) - hour1 * 60);
//            timeString = hour1 + "小时" + min1 + "分";
            timeString = day+"";
            // System.out.println(day + "天" + hour + "小时" + min + "分" + s +
            // "秒");

        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return timeString;

    }

    /**
     * 计算相差的小时
     *
     * @param starTime
     * @param endTime
     * @return
     */
    public String getTimeDifferenceHour(String starTime, String endTime) {
        String timeString = "";
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");

        try {
            Date parse = dateFormat.parse(starTime);
            Date parse1 = dateFormat.parse(endTime);

            long diff = parse1.getTime() - parse.getTime();
            String string = Long.toString(diff);

            float parseFloat = Float.parseFloat(string);

            float hour1 = parseFloat / (60 * 60 * 1000);

            timeString = Float.toString(hour1);
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return timeString;

    }

    /**
     * 获取时间中的某一个时间点
     *
     * @param str
     * @param type
     * @return
     */
    public String getJsonParseShiJian(String str, int type) {
        String shijanString = null;
        String nian = str.substring(0, str.indexOf("-"));
        String yue = str.substring(str.indexOf("-") + 1, str.lastIndexOf("-"));
        String tian = str.substring(str.lastIndexOf("-") + 1, str.indexOf(" "));
        String shi = str.substring(str.indexOf(" ") + 1, str.lastIndexOf(":"));
        String fen = str.substring(str.lastIndexOf(":") + 1, str.length());

        switch (type) {
            case 1:
                shijanString = nian;
                break;
            case 2:
                shijanString = yue;
                break;
            case 3:
                shijanString = tian;
                break;
            case 4:
                shijanString = shi;
                break;
            case 5:
                shijanString = fen;
                break;

        }
        return shijanString;
    }

    /**
     * Sring变int
     *
     * @param str
     * @return
     */
    public int strToInt(String str) {
        int value = 0;
        value = Integer.parseInt(str);
        return value;
    }

    /**
     * 与当前时间比较早晚
     *
     * @param time
     *            需要比较的时间
     * @return 输入的时间比现在时间晚则返回true
     */
    public boolean compareNowTime(String time) {
        boolean isDayu = false;

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");

        try {
            Date parse = dateFormat.parse(time);
            Date parse1 = dateFormat.parse(getNowTime());

            long diff = parse1.getTime() - parse.getTime();
            if (diff <= 0) {
                isDayu = true;
            } else {
                isDayu = false;
            }
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return isDayu;
    }

    /**
     * 把时间戳变yyyy-MM-dd HH:mm格式时间
     *
     * @param time
     * @return
     */
    public  String getDateToString(long time) {
        Date d = new Date(time);
        sf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        return sf.format(d);
    }

    /**
     * 返回时间戳
     *
     * @param time
     * @return
     */
    public long dataOne(String time) {
        SimpleDateFormat sdr = new SimpleDateFormat("yyyy-MM-dd HH:mm",
                Locale.CHINA);
        Date date;
        long l = 0;
        try {
            date = sdr.parse(time);
            l = date.getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return l;
    }

    /**
     * 比较两个时间
     *
     * @param starTime
     *            开始时间
     * @param endString
     *            结束时间
     * @return 结束时间大于开始时间返回true，否则反之֮
     */
    public boolean compareTwoTime(String starTime, String endString) {
        boolean isDayu = false;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

        try {
            Date parse = dateFormat.parse(starTime);
            Date parse1 = dateFormat.parse(endString);

            long diff = parse1.getTime() - parse.getTime();
            if (diff >= 0) {
                isDayu = true;
            } else {
                isDayu = false;
            }
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return isDayu;

    }

    public boolean compareTwoTime2(String starTime, String endString) {
        boolean isDayu = false;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");

        try {
            Date parse = dateFormat.parse(starTime);
            Date parse1 = dateFormat.parse(endString);

            long diff = parse1.getTime() - parse.getTime();
            if (diff >= 0) {
                isDayu = true;
            } else {
                isDayu = false;
            }
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return isDayu;

    }

    /**
     * 获取年
     *
     * @param time
     * @return
     */
    public String getTimeYear(String time) {

        String substring = time.substring(0, time.lastIndexOf(" "));
        return substring;

    }

    /**
     * 换算小时，0.5小时-->0小时30分
     *
     * @param hour
     * @return
     */
    private String convertTime(String hour) {

        String substring = hour.substring(0, hour.lastIndexOf("."));
        String substring2 = hour.substring(hour.lastIndexOf(".") + 1,
                hour.length());
        substring2 = "0." + substring2;
        float f2 = Float.parseFloat(substring2);
        f2 = f2 * 60;
        String string = Float.toString(f2);
        String min = string.substring(0, string.lastIndexOf("."));
        return substring + "小时" + min + "分";

    }

}
