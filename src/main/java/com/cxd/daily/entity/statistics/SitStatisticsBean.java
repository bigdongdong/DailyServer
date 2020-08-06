package com.cxd.daily.entity.statistics;

import java.util.List;

public class SitStatisticsBean {
    private Integer userId ;
    private Integer targetSitTimes ;
    private List<SitStatisticsDataBean> beans ;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getTargetSitTimes() {
        return targetSitTimes;
    }

    public void setTargetSitTimes(Integer targetSitTimes) {
        this.targetSitTimes = targetSitTimes;
    }

    public List<SitStatisticsDataBean> getBeans() {
        return beans;
    }

    public void setBeans(List<SitStatisticsDataBean> beans) {
        this.beans = beans;
    }
}
