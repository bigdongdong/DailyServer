package com.cxd.daily.entity;

import java.sql.Time;
import java.util.Date;

public class TodayTaskBean {

    private Integer userId ;
    private Long targetSportTime ; //目标运动时长
    private Long todaySportTime ; //今日运动时长
    private Long targetSitBreakTimes ; //目标打破久坐次数
    private Integer todaySitBreakTimes ;//今日打破久坐次数
    private Time targetSleepTime ;//目标早睡时间
    private Date todaySleepTime ; //今日早睡时间

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Long getTargetSportTime() {
        return targetSportTime;
    }

    public void setTargetSportTime(Long targetSportTime) {
        this.targetSportTime = targetSportTime;
    }

    public Long getTodaySportTime() {
        return todaySportTime;
    }

    public void setTodaySportTime(Long todaySportTime) {
        this.todaySportTime = todaySportTime;
    }

    public Long getTargetSitBreakTimes() {
        return targetSitBreakTimes;
    }

    public void setTargetSitBreakTimes(Long targetSitBreakTimes) {
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

    public Date getTodaySleepTime() {
        return todaySleepTime;
    }

    public void setTodaySleepTime(Date todaySleepTime) {
        this.todaySleepTime = todaySleepTime;
    }
}
