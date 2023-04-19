package com.mxf.utils;

import java.util.Date;

/**
 * @author manxingfu
 * @date 2023/1/17
 */
public class RedisUtils {

    public static int calculatorOffset(Date date) {
        // 一年当中的第几天
        int days = DateUtils.daysOfYear(date);
        days = (days - 1) * 7;
        // 预约时段所属的编号
        int offset = DateUtils.calculatorPeriod(date);
        // Offset
        days += offset;
        return days;
    }

    public static int[] calculatorOffset(Date start, Date end) {
        // 一年当中的第几天
        int days = DateUtils.daysOfYear(start);
        days = (days - 1) * 7;
        // 预约时段所属的编号
        int[] offsets = DateUtils.calculatorPeriod(start, end);
        for (int i = 0; i < offsets.length; i++) {
            offsets[i] += days;
        }
        return offsets;
    }
}
