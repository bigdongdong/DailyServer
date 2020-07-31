package com.cxd.daily.entity;

import java.sql.Time;
import java.util.Date; //这个Date相当于sql里面的datetime

public class UserSettingBean {

    private int userId;
    private long sportTime;
    private Date sitStartTime;
    private Date sitEndTime;
    private long sitSpaceTime;
    private long sitBreakTimes;
    private Time sleepTime;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public long getSportTime() {
        return sportTime;
    }

    public void setSportTime(long sportTime) {
        this.sportTime = sportTime;
    }

    public Date getSitStartTime() {
        return sitStartTime;
    }

    public void setSitStartTime(Date sitStartTime) {
        this.sitStartTime = sitStartTime;
    }

    public Date getSitEndTime() {
        return sitEndTime;
    }

    public void setSitEndTime(Date sitEndTime) {
        this.sitEndTime = sitEndTime;
    }

    public long getSitSpaceTime() {
        return sitSpaceTime;
    }

    public void setSitSpaceTime(long sitSpaceTime) {
        this.sitSpaceTime = sitSpaceTime;
    }

    public long getSitBreakTimes() {
        return sitBreakTimes;
    }

    public void setSitBreakTimes(long sitBreakTimes) {
        this.sitBreakTimes = sitBreakTimes;
    }

    public Time getSleepTime() {
        return sleepTime;
    }

    public void setSleepTime(Time sleepTime) {
        this.sleepTime = sleepTime;
    }
}

