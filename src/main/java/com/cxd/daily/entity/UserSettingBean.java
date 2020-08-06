package com.cxd.daily.entity;

import java.sql.Time;
import java.util.Date; //这个Date相当于sql里面的datetime

public class UserSettingBean {

    private Integer userId;
    private Integer sportTime;  //目标运动时长
    private Time sitStartTime;
    private Time sitEndTime;
    private Integer sitSpaceTime;
    private Integer sitBreakTimes;
    private Time sleepTime; //目标睡觉时间

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getSportTime() {
        return sportTime;
    }

    public void setSportTime(Integer sportTime) {
        this.sportTime = sportTime;
    }

    public Time getSitStartTime() {
        return sitStartTime;
    }

    public void setSitStartTime(Time sitStartTime) {
        this.sitStartTime = sitStartTime;
    }

    public Time getSitEndTime() {
        return sitEndTime;
    }

    public void setSitEndTime(Time sitEndTime) {
        this.sitEndTime = sitEndTime;
    }

    public Integer getSitSpaceTime() {
        return sitSpaceTime;
    }

    public void setSitSpaceTime(Integer sitSpaceTime) {
        this.sitSpaceTime = sitSpaceTime;
    }

    public Integer getSitBreakTimes() {
        return sitBreakTimes;
    }

    public void setSitBreakTimes(Integer sitBreakTimes) {
        this.sitBreakTimes = sitBreakTimes;
    }

    public Time getSleepTime() {
        return sleepTime;
    }

    public void setSleepTime(Time sleepTime) {
        this.sleepTime = sleepTime;
    }
}

