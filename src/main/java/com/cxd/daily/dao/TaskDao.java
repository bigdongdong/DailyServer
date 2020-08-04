package com.cxd.daily.dao;

import java.sql.Time;

public interface TaskDao {

    /*获取用户今日运动时间*/
    Integer getTodaySportTime(int userId);

    /*获取用户今日打破久坐次数*/
    Integer getTodaySitBreakTimes(int userId);

    /*获取用户今日最后一条打破久坐时间*/
    Time getTodayLastSitBreakTime(int userId);

    /*获取用户今日最后一次睡觉时间*/
    Time getTodaySleepDateTime(int userId);

    /*插入一条运动记录*/
    Boolean insertSportTime(int userId,int sportTime);

    /*插入一条打破久坐*/
    Boolean insertSitBreakTime(int userId);

    /*插入一条早睡记录*/
    Boolean insertSleepTime(int userId,Time sleepTime);
}
