package com.cxd.daily.dao;

import com.cxd.daily.entity.statistics.SitStatisticsDataBean;
import com.cxd.daily.entity.statistics.SleepStatisticsDataBean;
import com.cxd.daily.entity.statistics.SportStatisticsDataBean;

import java.util.List;

public interface StatisticsDao {

    /*获取最近7天的运动时长数据*/
    List<SportStatisticsDataBean> getLast7SportTime(int userId , String last7Date , String nowDate);

    /*获取最近7天的打破久坐数据*/
    List<SitStatisticsDataBean> getLast7SitBreakTimes(int userId , String last7Date , String nowDate);

    /*获取最近7天的早睡数据*/
    List<SleepStatisticsDataBean> getLast7SleepTime(int userId , String last7Date , String nowDate);

}
