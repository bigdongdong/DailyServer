package com.cxd.daily.entity.statistics;

import java.util.Date;

public class SportStatisticsDataBean {

    private Date date ;
    private Integer thisDaySportTime ;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getThisDaySportTime() {
        return thisDaySportTime;
    }

    public void setThisDaySportTime(Integer thisDaySportTime) {
        this.thisDaySportTime = thisDaySportTime;
    }
}
