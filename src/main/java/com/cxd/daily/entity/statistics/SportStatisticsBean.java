package com.cxd.daily.entity.statistics;

import java.sql.Time;
import java.util.Date;
import java.util.List;

public class SportStatisticsBean {
    private Integer userId ;
    private Integer targetSportTime ;
    private List<SportStatisticsDataBean> beans ;

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

    public List<SportStatisticsDataBean> getBeans() {
        return beans;
    }

    public void setBeans(List<SportStatisticsDataBean> beans) {
        this.beans = beans;
    }
}
