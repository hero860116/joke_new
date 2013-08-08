package com.kelepi.util;

import java.util.Calendar;
import java.util.Date;

/**
 * User: liWeiLin
 * Date: 13-8-8 下午11:08
 */
public class DateUtil {

    /**
     * 给指定日志加上一段时间
     * @param date
     * @param field
     * @param duration
     * @return
     */
    public static Date addDuration(Date date, int field, int duration) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        calendar.add(field, duration);

        return  calendar.getTime();
    }
}
