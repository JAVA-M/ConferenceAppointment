package com.mxf.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author manxingfu
 * @date 2023/1/17
 */
public class DateUtils {
    private static final Logger logger = LoggerFactory.getLogger(DateUtils.class);
    /*private static final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    private static final SimpleDateFormat yearFormat = new SimpleDateFormat("yyyy");*/

    /**
     *
     * @param date
     * @return 返回传入的日期是一年当中的第几天
     */
    public static int daysOfYear(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date); // 将Date对象设置到Calendar中
        int dayOfYear = calendar.get(Calendar.DAY_OF_YEAR);
        // logger.info("该日期是一年中的第{}天", dayOfYear);
        return dayOfYear;
    }

    /*
    public static synchronized int daysOfYear(Date date) {
        String year = yearFormat.format(new Date());
        Date firstDayOfYear = null;
        try {
            // logger.info("年份: {}", year);
            // Bug: 并发访问下该方法报错java.lang.NumberFormatException: For input string: ".200223E4.200223E4"
            firstDayOfYear = format.parse(year + "-01-01 00:00:00");
        } catch (ParseException e) {
            logger.warn("日期解析出错");
            throw new RuntimeException(e);
        }

        // return (int) Math.ceil((date.getTime() - calendar.getTime().getTime()) / (1000 * 60 * 60 * 24)) + 1;
        return (int) ((date.getTime() - firstDayOfYear.getTime()) / (1000 * 60 * 60 * 24)) + 1;
    }*/

    // 使用 Calendar 构造的时间获取的时间戳，会一直增长
    /*public static int daysOfYear(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_YEAR, 1);
        //注意Calendar.HOUR_OF_DAY才是24小时制，Calendar.HOUR是12小时制要配合AM/PM
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);

        // return (int) Math.ceil((date.getTime() - calendar.getTime().getTime()) / (1000 * 60 * 60 * 24)) + 1;
        return (int) ((date.getTime() - calendar.getTime().getTime() - 842) / (1000 * 60 * 60 * 24)) + 1;
    }*/

    /**
     *
     * @param date
     * @return 根据小时划分预约区间
     */
    public static int calculatorPeriod(Date date) {
        SimpleDateFormat hourFormat = new SimpleDateFormat("HH");
        SimpleDateFormat minuteFormat = new SimpleDateFormat("mm");
        int hour = Integer.parseInt(hourFormat.format(date));
        int minute = Integer.parseInt(minuteFormat.format(date));
        if (hour < 9) {
            return 0;
        } else if (hour < 10) { // hour = 9
            return minute == 0 ? 0: 1;
        } else if (hour < 11) { // hour = 10
            return minute == 0 ? 1: 2;
        } else if (hour < 14) { // hour = [11,12,13]
            if (hour == 11) {
                return minute == 0 ? 2: 3;
            }
            return 3;
        } else if (hour < 16) { // hour = [14,15]
            if (hour == 14) {
                return minute == 0 ? 3: 4;
            }
            return 4;
        } else if (hour < 18) { // hour = [16,17]
            if (hour == 16) {
                return minute == 0 ? 4: 5;
            }
            return 5;
        } else {
            if (hour == 18) {
                return minute == 0 ? 5: 6;
            }
            return 6;
        }
    }

    /**
     * 求取用户自定义时间范围的预约下标集合
     * @param start
     * @param end
     * @return
     */
    public static int[] calculatorPeriod(Date start, Date end) {
        int startIndex = calculatorPeriod(start);
        int endIndex = calculatorPeriod(end);
        int[] offsets = new int[endIndex - startIndex + 1];
        for (int i = 0; i < offsets.length; i++) {
            offsets[i] = startIndex++;
        }
        return offsets;
    }
}
