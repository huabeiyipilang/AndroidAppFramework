package com.penghaonan.appframework.utils;

import java.util.Calendar;
import java.util.TimeZone;

public class CalendarUtils {
    public static final long ONE_MINUTE = 60 * 1000;
    public static final long ONE_HOUR = ONE_MINUTE * 60;
    public static final long ONE_DAY = ONE_HOUR * 24;

    /**
     * 当前时间
     *
     * @return
     */
    public static Calendar getCalendarNow() {
        return Calendar.getInstance(TimeZone.getDefault());
    }

    /**
     * 指定时间
     *
     * @param time
     * @return
     */
    public static Calendar getCalendar(long time) {
        Calendar calendar = getCalendarNow();
        calendar.setTimeInMillis(time);
        return calendar;
    }

    /**
     * 克隆
     *
     * @param calendar
     * @return
     */
    public static Calendar clone(Calendar calendar) {
        if (calendar == null) {
            return null;
        } else {
            Calendar newCalendar = getCalendarNow();
            newCalendar.setTime(calendar.getTime());
            return newCalendar;
        }
    }

    /**
     * 获取一天的0时整
     *
     * @param calendar
     * @return
     */
    public static long getCalendarDayBeginning(Calendar calendar) {
        Calendar newCalendar = clone(calendar);
        if (newCalendar == null) {
            newCalendar = getCalendarNow();
        }
        newCalendar.set(Calendar.MILLISECOND, 0);
        newCalendar.set(Calendar.SECOND, 0);
        newCalendar.set(Calendar.MINUTE, 0);
        newCalendar.set(Calendar.HOUR_OF_DAY, 0);
        return newCalendar.getTimeInMillis();
    }

    /**
     * 获取一天最后一毫秒
     *
     * @param calendar
     * @return
     */
    public static long getCalendarDayEnding(Calendar calendar) {
        Calendar newCalendar = clone(calendar);
        if (newCalendar == null) {
            newCalendar = getCalendarNow();
        }
        newCalendar.set(Calendar.MILLISECOND, 999);
        newCalendar.set(Calendar.SECOND, 59);
        newCalendar.set(Calendar.MINUTE, 59);
        newCalendar.set(Calendar.HOUR_OF_DAY, 23);
        return newCalendar.getTimeInMillis();
    }

    /**
     * 获取一个时间的前N天，当前天是0
     *
     * @param calendar
     * @return
     */
    public static Calendar getCalendarLastNDay(Calendar calendar, int count) {
        Calendar newCalendar = clone(calendar);
        if (newCalendar == null) {
            newCalendar = getCalendarNow();
        }
        int dayOfYear = newCalendar.get(Calendar.DAY_OF_YEAR);
        newCalendar.set(Calendar.DAY_OF_YEAR, dayOfYear - count);
        return newCalendar;
    }

    /**
     * 是否是同一天
     *
     * @param time1
     * @param time2
     * @return
     */
    public static boolean isSameDay(long time1, long time2) {
        if (Math.abs(time1 - time2) > ONE_DAY) {
            return false;
        } else {
            Calendar calendar1 = getCalendar(time1);
            Calendar calendar2 = getCalendar(time2);
            return calendar1.get(Calendar.DAY_OF_YEAR) == calendar2.get(Calendar.DAY_OF_YEAR);
        }
    }
}
