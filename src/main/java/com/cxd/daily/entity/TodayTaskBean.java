package com.cxd.daily.entity;

import java.sql.Time;
import java.util.Date;

public class TodayTaskBean {

    private Integer userId ;
    private Integer targetSportTime ; //目标运动时长
    private Integer todaySportTime ; //今日运动时长
    private Integer targetSitBreakTimes ; //目标打破久坐次数
    private Integer todaySitBreakTimes ;//今日打破久坐次数
    private Time targetSleepTime ;//目标早睡时间
    private Time todaySleepTime ; //今日早睡时间

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getTargetSportTime() {
        return targetSportTime;
    }

    public void setTargetSportTime(Integer targetSportTime) {
        this.targetSportTime = targetSportTime;
    }

    public Integer getTodaySportTime() {
        return todaySportTime;
    }

    public void setTodaySportTime(Integer todaySportTime) {
        this.todaySportTime = todaySportTime;
    }

    public Integer getTargetSitBreakTimes() {
        return targetSitBreakTimes;
    }

    public void setTargetSitBreakTimes(Integer targetSitBreakTimes) {
        this.targetSitBreakTimes = targetSitBreakTimes;
    }

    public Integer getTodaySitBreakTimes() {
        return todaySitBreakTimes;
    }

    public void setTodaySitBreakTimes(Integer todaySitBreakTimes) {
        this.todaySitBreakTimes = todaySitBreakTimes;
    }

    public Time getTargetSleepTime() {
        return targetSleepTime;
    }

    public void setTargetSleepTime(Time targetSleepTime) {
        this.targetSleepTime = targetSleepTime;
    }

    public Time getTodaySleepTime() {
        return todaySleepTime;
    }

    public void setTodaySleepTime(Time todaySleepTime) {
        this.todaySleepTime = todaySleepTime;
    }
}
