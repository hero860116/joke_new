package com.kelepi.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.apache.commons.lang.time.DateUtils;

/**
 * 字符时间工具类 ,相对于一天的时间(11:20)
 * @author Administrator
 *
 */
public class TimeUtils {

    /**
     * 获取今日时间秒数
     * @param time "HH:mm:ss"
     * @return
     */
    public static long getSecondsFromStrTime(String time) {
        int minu = 0;

        String[] tis = time.split(":");

        int hour = Integer.parseInt(tis[0]);
        int minute = Integer.parseInt(tis[1]);

        int secend = 0;
        if (tis.length > 2) {
            secend = Integer.parseInt(tis[2]);
        }


        minu = hour * 3600 + minute * 60 + secend;

        return minu;
    }

    /**
     * 获取今日时间秒数
     * @param time "HH:mm:ss"
     * @return
     */
    public static long getSecondsFromTime(Date dateTime) {
        int minu = 0;

        String time = new SimpleDateFormat("HH:mm:ss").format(dateTime);

        String[] tis = time.split(":");

        int hour = Integer.parseInt(tis[0]);
        int minute = Integer.parseInt(tis[1]);

        int secend = 0;
        if (tis.length > 2) {
            secend = Integer.parseInt(tis[2]);
        }


        minu = hour * 3600 + minute * 60 + secend;

        return minu;
    }

    public static long getHours(long time) {
        if (time <= 0) {
            return 0l;
        }
        return time / 3600;
    }

    public static long getMinutes(long time) {
        if (time <= 0) {
            return 0l;
        }

        return (time % 3600) / 60;
    }

    public static long getSeconds(long time) {
        if (time <= 0) {
            return 0;
        }

        return time % 60;
    }

    public static int getHours(String time) {
        return (int)getHours(getSecondsFromStrTime(time));
    }

    public static int getMinutes(String time) {
        return (int)getMinutes(getSecondsFromStrTime(time));
    }

    public static int getSeconds(String time) {
        return (int)getSeconds(getSecondsFromStrTime(time));
    }

    public static String getStrHours(long time) {
        long hours = getHours(time);

        String strHours = "";
        if (hours < 10) {
            strHours += "0";
        }

        strHours += hours;

        return strHours;
    }

    public static String getStrMinutes(long time) {
        long minutes = getMinutes(time);

        String strHours = "";
        if (minutes < 10) {
            strHours += "0";
        }

        strHours += minutes;

        return strHours;
    }

    public static String getStrSeconds(long time) {
        long seconds = getSeconds(time);

        String strHours = "";
        if (seconds < 10) {
            strHours += "0";
        }

        strHours += seconds;

        return strHours;
    }

    /**
     * date1 - date2
     * @param date1
     * @param date2
     * @return
     */
    public static long getIntervalSeconds(Date date1, Date date2) {
        return getSecondsFromTime(date1) - getSecondsFromTime(date2);
    }

    /**
     * time1 - time2
     * @param time1
     * @param time2
     * @return
     */
    public static long getIntervalSeconds(String time1, String time2) {
        return getSecondsFromStrTime(time1) - getSecondsFromStrTime(time2);
    }

    /**
     * 已过多长时间 （秒）
     * 当前时间 - 传入时间
     * @param date
     * @return
     */
    public static long getIntervalSeconds(Date date) {
        return getSecondsFromTime(new Date()) - getSecondsFromTime(date);
    }

    /**
     * 已过多长时间 （秒）
     * 当前时间 - 传入时间
     * @param date
     * @return
     */
    public static long getIntervalSeconds(String strTime) {
        return getSecondsFromTime(new Date()) - getSecondsFromStrTime(strTime);
    }

    /**
     * 剩余多少时间（秒）
     * 传入时间 - 当前时间
     * @param date
     * @return
     */
    public static long getRemainIntervalSeconds(Date date) {
        return getSecondsFromTime(date) - getSecondsFromTime(new Date());
    }

    /**
     * 剩余多少时间（秒）
     * 传入时间 - 当前时间
     * @param date
     * @return
     */
    public static long getRemainIntervalSeconds(String strTime) {
        return getSecondsFromStrTime(strTime) - getSecondsFromTime(new Date());
    }

    /**
     * 除去时分秒的影响，获取两个日期的相差的天数
     * data1 - date2
     * @param date1
     * @param date2
     * @return
     */
    public static long getDays(Date date1, Date date2) {
        Date toDate1 = DateUtils.truncate(date1, Calendar.DAY_OF_MONTH);
        Date toDate2 = DateUtils.truncate(date2, Calendar.DAY_OF_MONTH);

        long days = (toDate1.getTime() - toDate2.getTime()) / (24 * 3600 * 1000);

        return days;
    }

    /**
     * 获得今天，不含时分秒
     * @return
     */
    public static Date getToday() {
        return DateUtils.truncate(new Date(), Calendar.DAY_OF_MONTH);
    }

    public static String getTodayStr(String pattern) {
        return new SimpleDateFormat(pattern).format(getToday());
    }

    /**
     * 获得明天，不含时分秒
     * @return
     */
    public static Date getTomorrow() {
        return DateUtils.addDays(getToday(), 1);
    }

    public static String getTomorrowStr(String pattern) {
        return new SimpleDateFormat(pattern).format(getTomorrow());
    }

    /**
     * 获得昨天
     * @return
     */
    public static Date getYesterday() {
        return DateUtils.addDays(getToday(), -1);
    }

    public static String getYesterdayStr(String pattern) {
        return new SimpleDateFormat(pattern).format(getYesterday());
    }
}
