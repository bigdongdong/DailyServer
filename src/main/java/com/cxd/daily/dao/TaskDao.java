package com.cxd.daily.dao;

import com.cxd.daily.entity.TodayTaskBean;

import java.sql.Time;
import java.util.Date;

public interface TaskDao {

    /*获取用户今日运动时间*/
    Integer getTodaySportTime(int userId);

    /*获取用户今日打破久坐次数*/
    Integer getTodaySitBreakTimes(int userId);

    /*获取用户今日最后一次睡觉时间*/
    Time getTodaySleepDateTime(int userId);
}
