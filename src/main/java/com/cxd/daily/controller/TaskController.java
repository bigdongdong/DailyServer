package com.cxd.daily.controller;


import com.cxd.daily.controller.response.Common;
import com.cxd.daily.dao.TaskDao;
import com.cxd.daily.dao.UserDao;
import com.cxd.daily.entity.TodayTaskBean;
import com.cxd.daily.entity.UserSettingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.SpringServletContainerInitializer;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@RequestMapping(value = "/task")
public class TaskController {
    @Autowired
    private TaskDao mTaskDao ;
    @Autowired
    private UserDao mUserDao ;

    private final long oneDayTime = 60*60*24*1000 ;

    /**
     * 获取用户今天的任务进度
     * @param userId
     * @return message===null
     */
    @RequestMapping(value = "/todayProgress",method = RequestMethod.GET)
    public Common<TodayTaskBean> todayProgress(@RequestParam (value = "userId")int userId){
        final long now = System.currentTimeMillis();
        final long todayStartDate= now - (now+60*60*8*1000) % oneDayTime ; //今日0点的时间戳
        final long todayEndDate = todayStartDate + oneDayTime ; //今日24点的时间戳
        final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        final String todayStartTime = sdf.format(todayStartDate);
        final String todayEndTime = sdf.format(todayEndDate);

        TodayTaskBean bean = new TodayTaskBean();
        bean.setUserId(userId);

        UserSettingBean usb = mUserDao.getUserSettings(userId);
        /*目标运动时长、目标打破久坐次数、目标睡觉时间*/
        if(usb != null){
            bean.setTargetSportTime(usb.getSportTime());
            bean.setTargetSitBreakTimes(usb.getSitBreakTimes());
            bean.setTargetSleepTime(usb.getSleepTime());
        }
        /*今日运动时长总计*/
        Long todaySportTime = mTaskDao.getTodaySportTime(userId,todayStartTime,todayEndTime);
        bean.setTodaySportTime(todaySportTime);
        /*今日打破久坐次数*/
        Integer todaySitBreakTimes = mTaskDao.getTodaySitBreakTimes(userId,todayStartTime,todayEndTime);
        bean.setTodaySitBreakTimes(todaySitBreakTimes);
        /*今日睡觉时间*/
        Date todaySleepDateTime = mTaskDao.getTodaySleepDateTime(userId,todayStartTime,todayEndTime);
        bean.setTodaySleepTime(todaySleepDateTime);

        return new Common<TodayTaskBean>().create(bean,null);
    }

//    public static void main(String[] args) {
//            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        System.out.println(sdf.format(new Date(1596038400000l)));  //2020-07-30 00:00:00
//        System.out.println(sdf.format(new Date(1596124800000l)));  //2020-07-31 00:00:00
//
//        System.out.println("当前时间"+sdf.format(new Date(System.currentTimeMillis())));
//    }

}
