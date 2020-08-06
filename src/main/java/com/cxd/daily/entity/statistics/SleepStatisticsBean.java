package com.cxd.daily.entity.statistics;

import java.sql.Time;
import java.util.List;

public class SleepStatisticsBean {
    private Integer userId ;
    private Time targetSleepTime ;
    private List<SleepStatisticsDataBean> beans ;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Time getTargetSleepTime() {
        return targetSleepTime;
    }

    public void setTargetSleepTime(Time targetSleepTime) {
        this.targetSleepTime = targetSleepTime;
    }

    public List<SleepStatisticsDataBean> getBeans() {
        return beans;
    }

    public void setBeans(List<SleepStatisticsDataBean> beans) {
        this.beans = beans;
    }
}
