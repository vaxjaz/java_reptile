package com.jay.li.reptile.util;

import java.time.*;
import java.time.format.DateTimeFormatter;

/**
 * @author jay
 * @date 2019/4/10 11:26
 */
public final class DateTimeUtils {

    public static final String YYYY_MM_DD = "yyyy-MM-dd";

    public static final String YYYYMMDD = "yyyyMMdd";

    public static final String YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";

    public static final String YYYY_MM_DD_HH_MM_SS_SSS = "yyyyMMddHHmmssSSS";

    public static final String HH_MM = "HH:mm";

    private DateTimeUtils() {

    }

    public static LocalDate nowDate() {
        return LocalDate.now();
    }

    public static LocalDateTime nowDateTime() {
        return LocalDateTime.now();
    }

    public static LocalDate dateTime2Date(LocalDateTime nowTime) {
        return nowTime.toLocalDate();
    }


    public static int toDayOfWeek(LocalDateTime nowTime) {
        DayOfWeek dayOfWeek = nowTime.getDayOfWeek();
        return dayOfWeek.getValue();
    }

    public static boolean isDual(LocalDateTime nowTime) {
        String str = nowTime.toLocalDate().format(DateTimeFormatter.ofPattern(YYYYMMDD));
        return Integer.valueOf(str) % 2 == 0;
    }

    public static LocalTime toTime(LocalDateTime nowTime) {
        return nowTime.toLocalTime();
    }


    /**
     * 07:00 字符串转localTIme
     *
     * @param begin
     * @return
     */
    public static LocalTime toTime(String begin) {
        return LocalTime.parse(begin, DateTimeFormatter.ofPattern(HH_MM));
    }

    public static String millsToString(long currentTime) {
        LocalDateTime localDateTime = Instant.ofEpochMilli(currentTime).atZone(ZoneId.systemDefault()).toLocalDateTime();
        return localDateTime.format(DateTimeFormatter.ofPattern(YYYY_MM_DD_HH_MM_SS));
    }

    public static String millsToString(long currentTime, String pattern) {
        LocalDateTime localDateTime = Instant.ofEpochMilli(currentTime).atZone(ZoneId.systemDefault()).toLocalDateTime();
        return localDateTime.format(DateTimeFormatter.ofPattern(pattern));
    }

    public static LocalDateTime millsToLocalDate(long mills) {
        return Instant.ofEpochMilli(mills).atZone(ZoneId.systemDefault()).toLocalDateTime();
    }

    public static String nowStr() {
        return DateTimeUtils.nowDateTime().format(DateTimeFormatter.ofPattern(YYYY_MM_DD_HH_MM_SS));
    }

    public static String nowKeyStr() {
        return DateTimeUtils.nowDateTime().format(DateTimeFormatter.ofPattern(YYYY_MM_DD_HH_MM_SS_SSS));
    }

    public static String nowDayStr() {
        return DateTimeUtils.nowDateTime().format(DateTimeFormatter.ofPattern(YYYY_MM_DD));
    }


    public static String anyDaysBefore(int offset) {
        LocalDateTime localDateTime = nowDateTime().minusDays(offset);
        return localDateTime.format(DateTimeFormatter.ofPattern(YYYY_MM_DD));
    }

}
