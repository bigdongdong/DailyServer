package com.cxd.daily.controller;


import com.cxd.daily.controller.response.Common;
import com.cxd.daily.dao.TaskDao;
import com.cxd.daily.dao.UserDao;
import com.cxd.daily.entity.TodayTaskBean;
import com.cxd.daily.entity.UserSettingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.SpringServletContainerInitializer;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
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
    public Common<TodayTaskBean> todayProgress(@RequestParam (value = "userId")int userId) throws ParseException {
//        final long now = System.currentTimeMillis();
//        final long todayStartDate= now - (now+60*60*8*1000) % oneDayTime ; //今日0点的时间戳
//        final long todayEndDate = todayStartDate + oneDayTime ; //今日24点的时间戳
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//        final String todayStartTime = sdf.format(todayStartDate);
//        final String todayEndTime = sdf.format(todayEndDate);

        TodayTaskBean bean = new TodayTaskBean();
        bean.setUserId(userId);

        UserSettingBean usb = mUserDao.getUserSettings(userId);
        /*目标运动时长、目标打破久坐次数、目标睡觉时间*/
        if(usb != null){
            bean.setTargetSportTime(usb.getSportTime()); //目标运动时长
            bean.setTargetSitBreakTimes(usb.getSitBreakTimes()); //目标打破久坐次数
//            Time sleepTime = usb.getSleepTime() ;
            bean.setTargetSleepTime(usb.getSleepTime());
//            if(sleepTime != null){
//                bean.setTargetSleepTime(usb.getSleepTime().getTime());//目标早睡时间
//            }
        }
        /*今日运动时长总计*/
        Integer todaySportTime = mTaskDao.getTodaySportTime(userId);
        bean.setTodaySportTime(todaySportTime);
        /*今日打破久坐次数，且需要在规定时间内*/
        Integer todaySitBreakTimes = mTaskDao.getTodaySitBreakTimes(userId);
        bean.setTodaySitBreakTimes(todaySitBreakTimes);
        /*今日睡觉时间*/
        Time todaySleepTime = mTaskDao.getTodaySleepDateTime(userId);
        bean.setTodaySleepTime(todaySleepTime);
//        if(todaySleepTime != null){
//            bean.setTodaySleepTime(todaySleepTime.getTime());
//        }
        return new Common<TodayTaskBean>().create(bean,null);
    }

    /**
     * 插入一条运动记录
     * @param userId
     * @param sportTime
     * @return
     */
    @RequestMapping(value = "/insertSportTime",method = RequestMethod.GET)
    public Common<Boolean> insertSportTime(@RequestParam(value = "userId")int userId,@RequestParam(value = "sportTime")int sportTime){
        String message = null ;
        UserSettingBean usb = mUserDao.getUserSettings(userId);
        if(usb == null || usb.getSportTime() == null){
            message = "请先设置每日运动时间！";
            return new Common<Boolean>().create(null,message);
        }
        /*最多一天只能运动600分钟*/
        Integer todaySportTimeInt =  mTaskDao.getTodaySportTime(userId); //判空
        int todaySportTime = todaySportTimeInt == null ? 0 : todaySportTimeInt ;
        if(todaySportTime < 600){
            int still = 600 - todaySportTime ;
            sportTime = Math.min(sportTime,still);
        }else{
            message = "今天运动时间已达上限600分钟！";
            return new Common<Boolean>().create(null,message);
        }

        Boolean b = mTaskDao.insertSportTime(userId,sportTime);
        return new Common<Boolean>().create(b,null);
    }

    /**
     * 插入一条打破久坐记录
     * @param userId
     * @return
     */
    @RequestMapping(value = "/insertSitBreakTime",method = RequestMethod.GET)
    public Common<Boolean> insertSitBreakTime(@RequestParam(value = "userId")int userId){

        UserSettingBean usb = mUserDao.getUserSettings(userId);
        if(usb == null){
            return new Common<Boolean>().create(null,"请先设置打破久坐时间间隔！");
        }
        int spaceTime = usb.getSitSpaceTime();
        /*查询今日最新一条打破久坐记录*/
        Time lastTime = mTaskDao.getTodayLastSitBreakTime(userId);
        if(spaceTime <= 0){
            return new Common<Boolean>().create(null,"请先设置打破久坐时间间隔！");
        }

        Boolean b = null ;
        if(lastTime != null){
            /*计算两次间隔时间*/
            String lastTimeStr = lastTime.toString();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            lastTimeStr = sdf.format(new Date())+" "+ lastTimeStr ;
            long last = 0; //上一次的时间
            try {
                sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                last = sdf.parse(lastTimeStr).getTime();
            } catch (ParseException e) {
                e.printStackTrace();
            }
            long now = System.currentTimeMillis(); //当前时间
            /*时间过近*/
            if(now < last || now - last < spaceTime*1000*60){
                return new Common<Boolean>().create(null,"距离上次打破久坐时间过近，请稍后重试！");
            }
        }
        /*正常情况，执行插入*/
        b = mTaskDao.insertSitBreakTime(userId);

        return new Common<Boolean>().create(b,null);
    }

    /**
     * 插入一条早睡记录
     * @param userId
     * @param sleepTime
     * @return 即使逾期也可以插入
     */
    @RequestMapping(value = "/insertSleepTime",method = RequestMethod.GET)
    public Common<Boolean> insertSleepTime(@RequestParam(value = "userId")int userId,@RequestParam(value = "sleepTime")Time sleepTime){
        String message = null ;
        UserSettingBean usb = mUserDao.getUserSettings(userId);
        if(usb == null || usb.getSleepTime() == null){
            message = "请先设置早睡时间！" ;
            return new Common<Boolean>().create(null,message);
        }
        /*TODO 判断当前是否18:00之前*/
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        final String today = sdf.format(new Date());
        String earliest = today+" 18:00:00";
        sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long earliestTime = 0;
        try {
            earliestTime = sdf.parse(earliest).getTime();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        if(earliestTime != 0){
            sdf = new SimpleDateFormat("HH:mm:ss");
            String sleep = today + " "+sdf.format(sleepTime);
            sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            long sleepTimes = 0;
            try {
                sleepTimes = sdf.parse(sleep).getTime();
            } catch (ParseException e) {
                e.printStackTrace();
            }

            if(sleepTimes < earliestTime){
                message = "现在太早了，请18:00以后再试" ;
                return new Common<Boolean>().create(null,message);
            }
        }

        Boolean b = mTaskDao.insertSleepTime(userId,sleepTime);
        return new Common<Boolean>().create(b,null);
    }


//    public static void main(String[] args) {
////            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
////        System.out.println(sdf.format(new Date(1596038400000l)));  //2020-07-30 00:00:00
////        System.out.println(sdf.format(new Date(1596124800000l)));  //2020-07-31 00:00:00
////
////        System.out.println("当前时间"+sdf.format(new Date(System.currentTimeMillis())));
//        String str = "15:47:45";
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//        String today = sdf.format(new Date());
//        today = today+" "+ str ;
//        System.out.println(today);
//        sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        Date date = null;
//        try {
//            date = sdf.parse(today);
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
//        System.out.println(date.getTime());
//        System.out.println(new Date().getTime());
//    }

}
