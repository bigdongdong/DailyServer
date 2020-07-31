package com.cxd.daily.dao;

import com.cxd.daily.entity.TodayTaskBean;

import java.util.Date;

public interface TaskDao {

    /*获取用户今日运动时间*/
    Long getTodaySportTime(int userId,String startTime , String endTime);

    /*获取用户今日打破久坐次数*/
    Integer getTodaySitBreakTimes(int userId,String startTime , String endTime);

    /*获取用户今日最后一次睡觉时间*/
    Date getTodaySleepDateTime(int userId,String startTime , String endTime);
}
