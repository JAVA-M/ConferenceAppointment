package com.mxf;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;

/**
 * Unit test for simple App.
 */
public class AppTest {
    public static void main(String[] args) throws ParseException {
       /* Calendar calendar = Calendar.getInstance();
        int dayOfYear = calendar.get(Calendar.DAY_OF_YEAR);
        System.out.println("今天是一年中的第" + dayOfYear + "天");*/

        SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
        Date date1 = format.parse("20230101");
        Date date2 = format.parse("20230201");
        // Date date = new Date(); // 获取当前时间
        Calendar calendar = Calendar.getInstance();

        calendar.setTime(date1); // 将Date对象设置到Calendar中
        System.out.println("该日期是一年中的第" + calendar.get(Calendar.DAY_OF_YEAR) + "天");

        calendar.setTime(date2); // 将Date对象设置到Calendar中
        System.out.println("该日期是一年中的第" + calendar.get(Calendar.DAY_OF_YEAR) + "天");
    }
}
