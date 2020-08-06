package com.cxd.daily.entity.statistics;

import java.util.Date;

public class SitStatisticsDataBean {
    private Date date ;
    private Integer thisDaySitTimes ;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getThisDaySitTimes() {
        return thisDaySitTimes;
    }

    public void setThisDaySitTimes(Integer thisDaySitTimes) {
        this.thisDaySitTimes = thisDaySitTimes;
    }
}
