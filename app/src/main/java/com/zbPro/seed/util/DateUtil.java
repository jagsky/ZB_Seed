package com.zbPro.seed.util;

import java.util.Calendar;

/*
   * 时间：2016/5/26
   * 名称：时间工具
   * 作用：获取日期
   * 实现功能：通过Calendar获取系统的日期。
   * */
public class DateUtil {
    //获取系统年份
    public static int getDateYear() {
        Calendar cc = Calendar.getInstance();
        int year = cc.get(Calendar.YEAR);
        return year;
    }

    //获取系统月份
    public static int getDateMoth() {
        Calendar cc = Calendar.getInstance();
        int month = cc.get(Calendar.MONTH);
        return month;
    }

    //获取系统日
    public static int getDateDay() {
        Calendar cc = Calendar.getInstance();
        int day = cc.get(Calendar.DAY_OF_MONTH);
        return day;
    }
}
