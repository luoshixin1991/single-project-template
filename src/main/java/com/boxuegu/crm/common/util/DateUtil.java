package com.boxuegu.crm.common.util;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.time.temporal.WeekFields;
import java.util.Date;
import java.util.LinkedList;
import java.util.Locale;
import java.util.stream.Stream;

/**
 * LocalDateTimeUtils Java8中的时间类 线程安全
 *
 * @author lsx
 * @date 2021/9/27 15:31
 */
public class DateUtil {
    private static final String DEFAULT_LOCAL_DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
    private static final String DEFAULT_LOCAL_DATE_FORMAT = "yyyy-MM-dd";
    private static final String DEFAULT_LOCAL_TIME_FORMAT = "HH:mm:ss";

    /**
     * Date转换为LocalDateTime
     */
    public static LocalDateTime dateToLocalDateTime(Date date) {
        return LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
    }

    /**
     * LocalDateTime转换为Date
     */
    public static Date localDateTimeToDate(LocalDateTime time) {
        return Date.from(time.atZone(ZoneId.systemDefault()).toInstant());
    }

    /**
     * 获取指定日期的毫秒
     */
    public static Long getMilliSecondByTime(LocalDateTime time) {
        return time.atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
    }

    /**
     * 获取指定日期的秒
     */
    public static Long getSecondByTime(LocalDateTime time) {
        return time.atZone(ZoneId.systemDefault()).toInstant().getEpochSecond();
    }

    /**
     * 获取一年中的第几个星期
     */
    public static int getWeekOfYear(LocalDate localDate){
        WeekFields weekFields = WeekFields.of(DayOfWeek.MONDAY,1);
        return localDate.get(weekFields.weekOfYear());
    }

    /**
     * 获取星期几  星期一为1  星期日为7
     */
    public static int getWeek(LocalDateTime dateTime){
        return dateTime.get(WeekFields.of(DayOfWeek.of(1), 1).dayOfWeek());
    }

    /**
     * 获取星期几 星期一为1  星期日为7
     */
    public static int getWeek(LocalDate date){
        return date.get(WeekFields.of(DayOfWeek.of(1), 1).dayOfWeek());
    }

    /**
     * 获取指定时间的指定格式
     */
    public static String formatLocalDateTime(LocalDateTime time) {
        return formatLocalDateTime(time,DEFAULT_LOCAL_DATE_TIME_FORMAT);
    }

    /**
     * 获取指定时间的指定格式
     */
    public static String formatLocalDateTime(LocalDateTime time, String pattern) {
        return time.format(DateTimeFormatter.ofPattern(pattern));
    }

    /**
     * 格式化LocalDate 指定格式
     */
    public static String formatLocalDate(LocalDate localDate,String pattern){
        return localDate.format(DateTimeFormatter.ofPattern(pattern));
    }

    /**
     * 格式化LocalDate 格式为yyyy-MM-dd
     */
    public static String formatLocalDate(LocalDate localDate){
        return localDate.format(DateTimeFormatter.ofPattern(DEFAULT_LOCAL_DATE_FORMAT));
    }

    /**
     * 格式化LocalTime 指定格式
     */
    public static String formatLocalTime(LocalTime localTime, String pattern){
        return localTime.format(DateTimeFormatter.ofPattern(pattern));
    }

    /**
     * 格式化LocalTime 格式为 HH:mm:ss
     */
    public static String formatLocalTime(LocalTime localTime){
        return localTime.format(DateTimeFormatter.ofPattern(DEFAULT_LOCAL_TIME_FORMAT));
    }

    /**
     * 获取当前时间的指定格式
     */
    public static String formatNow(String pattern) {
        return  formatLocalDateTime(LocalDateTime.now(), pattern);
    }

    /**
     * 获取当前时间 格式为：yyyy-MM-dd HH:mm:ss
     */
    public static String nowString() {
        return  formatLocalDateTime(LocalDateTime.now(), DEFAULT_LOCAL_DATE_TIME_FORMAT);
    }

    /**
     * 日期加上一个数
     *
     * @param time
     * @param number 添加数量
     * @param field  单位 参数为ChronoUnit.* (年 月 日 时 分 秒)
     */
    public static LocalDateTime plus(LocalDateTime time, long number, TemporalUnit field) {
        return time.plus(number, field);
    }

    /**
     * 日期减去一个数
     *
     * @param time
     * @param number 减去数量
     * @param field 单位 参数为ChronoUnit.* (年 月 日 时 分 秒)
     */
    public static LocalDateTime minu(LocalDateTime time, long number, TemporalUnit field){
        return time.minus(number,field);
    }

    /**
     * 获取两个日期的差
     *
     * @param startTime
     * @param endTime
     * @param field  单位 参数为ChronoUnit.* (年 月 日 时 分 秒)
     */
    public static long betweenTwoTime(LocalDateTime startTime, LocalDateTime endTime, ChronoUnit field) {
        Period period = Period.between(LocalDate.from(startTime), LocalDate.from(endTime));
        if (field == ChronoUnit.YEARS) {
            return period.getYears();
        }
        if (field == ChronoUnit.MONTHS){
            return period.getYears() * 12 + period.getMonths();
        }
        return field.between(startTime, endTime);
    }

    /**
     * 字符串格式化成LocalDateTime
     */
    public static LocalDateTime parse(String dateString,String pattern){
        DateTimeFormatter df = DateTimeFormatter.ofPattern(pattern);
        return LocalDateTime.parse(dateString,df);
    }

    /**
     * 字符串格式化成LocalDateTime
     */
    public static LocalDateTime parse(String dateString){
        return parse(dateString,DEFAULT_LOCAL_DATE_TIME_FORMAT);
    }

    /**
     * 字符串格式换localDate
     */
    public static LocalDate parseLocalDate(String dateString,String pattern){
        DateTimeFormatter df = DateTimeFormatter.ofPattern(pattern);
        return LocalDate.parse(dateString,df);
    }
    /**
     * 字符串格式换localDate 默认格式 yyyy-MM-dd
     */
    public static LocalDate parseLocalDate(String dateString){
        return parseLocalDate(dateString,DEFAULT_LOCAL_DATE_FORMAT);
    }
    /**
     * 字符串格式换 LocalTime
     */
    public static LocalTime parseLocalTime(String dateString,String pattern){
        DateTimeFormatter df = DateTimeFormatter.ofPattern(pattern);
        return LocalTime.parse(dateString,df);
    }
    /**
     * 字符串格式换 LocalTime 默认格式 HH:mm:ss
     */
    public static LocalTime parseLocalTime(String dateString ){
        return parseLocalTime(dateString,DEFAULT_LOCAL_TIME_FORMAT);
    }

    /**
     * 返回出两个日期中间天数列表 格式 yyyy-MM-dd
     */
    public static LinkedList<String> betweenTwoTimeDayList(String startTime, String endTime){

        LinkedList<String> list = new LinkedList<>();
        LocalDate startDate = LocalDate.parse(startTime);
        LocalDate endDate = LocalDate.parse(endTime);

        long distance = ChronoUnit.DAYS.between(startDate, endDate);
        if (distance < 0) {
            return list;
        }else if(distance == 0){
            if(startTime.equals(endTime)){
                list.add(endTime);
            }
            return list;
        }
        Stream.iterate(startDate, d -> {
            return d.plusDays(1);
        }).limit(distance + 1).forEach(f -> {
            list.add(f.toString());
        });
        return list;
    }

    /**
     * 根据一年中的第几个星期 获取这个星期内天数的时间list
     */
    public static LinkedList<String> getDayListByWeekOfYear(int weekOfYear){
        WeekFields weekFields = WeekFields.of(Locale.getDefault());
        int year = LocalDate.now().getYear();
        if(weekOfYear/53 > 0){
            year += weekOfYear/53;
            weekOfYear =  weekOfYear%53;
        }

        LocalDate first =  LocalDate.now()
                .withYear(year)
                .with(weekFields.weekOfYear(), weekOfYear)
                .with(weekFields.dayOfWeek(), 2);

        LocalDate end = LocalDate.now()
                .withYear(year)
                .with(weekFields.weekOfYear(), weekOfYear+1)
                .with(weekFields.dayOfWeek(), 1);
        return betweenTwoTimeDayList(formatLocalDate(first),formatLocalDate(end));
    }

    /**
     * 获取日期内星期一的日期
     */
    public static LocalDate getMonday(LocalDate localDate){
        return localDate.with(DayOfWeek.MONDAY);
    }

    /**
     * 获取日期内星期日的日期
     */
    public static LocalDate getSunday(LocalDate localDate){
        return localDate.with(DayOfWeek.SUNDAY);
    }
}