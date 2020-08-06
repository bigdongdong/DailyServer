package com.cxd.daily.entity.statistics;

import java.sql.Time;
import java.util.Date;

public class SleepStatisticsDataBean {
    private Date date ;
    private Time thisDaySleepTime ;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Time getThisDaySleepTime() {
        return thisDaySleepTime;
    }

    public void setThisDaySleepTime(Time thisDaySleepTime) {
        this.thisDaySleepTime = thisDaySleepTime;
    }
}
