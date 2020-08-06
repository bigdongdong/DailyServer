package com.cxd.daily.controller;

import com.cxd.daily.controller.response.Common;
import com.cxd.daily.dao.StatisticsDao;
import com.cxd.daily.dao.UserDao;
import com.cxd.daily.entity.statistics.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@RestController
@RequestMapping(value = "/statistics")
public class StatisticsController {

    @Autowired
    UserDao mUserDao ;
    @Autowired
    StatisticsDao mStatisticsDao;

    @RequestMapping(value = "/sport7" , method = RequestMethod.GET)
    public Common<SportStatisticsBean> getSport7(@RequestParam(value = "userId")int userId){
        if(mUserDao.getUserSettings(userId) == null){
            new Common<SportStatisticsBean>().create(null,null);
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String now = sdf.format(new Date());
        long ms = System.currentTimeMillis();
        ms -= 1000 * 3600 * 24 * 6 ; //这是7日前的date
        String last7 = sdf.format(new Date(ms));

        SportStatisticsBean ssb = new SportStatisticsBean();
        ssb.setUserId(userId);
        ssb.setTargetSportTime(mUserDao.getUserSettings(userId).getSportTime());
        /*获取7天的数据*/
        List<SportStatisticsDataBean> sqlBeans = mStatisticsDao.getLast7SportTime(userId,last7,now);
        /*补足缺少的天数*/
        Iterator<SportStatisticsDataBean> it ;
        List<SportStatisticsDataBean> beans = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            String curDate = sdf.format(new Date(ms));

            SportStatisticsDataBean b = new SportStatisticsDataBean();
            b.setDate(new Date(ms));
            b.setThisDaySportTime(0);
            beans.add(b);

            it = sqlBeans.iterator();
            while (it.hasNext()){
                SportStatisticsDataBean ib = it.next();
                if(sdf.format(ib.getDate()).equals(curDate)){
                    b.setThisDaySportTime(ib.getThisDaySportTime());
                    it.remove(); //这里remove sqlBeans也remove了
                    break;
                }
            }

            ms += 1000 * 3600 * 24 ; //date+1
        }

        ssb.setBeans(beans);
        return new Common<SportStatisticsBean>().create(ssb,null);
    }

    @RequestMapping(value = "/sit7" ,method = RequestMethod.GET)
    public Common<SitStatisticsBean> getSit7(@RequestParam(value = "userId")int userId){
        if(mUserDao.getUserSettings(userId) == null){
            new Common<SportStatisticsBean>().create(null,null);
        }

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String now = sdf.format(new Date());
        long ms = System.currentTimeMillis();
        ms -= 1000 * 3600 * 24 * 6 ; //这是7日前的date
        String last7 = sdf.format(new Date(ms));

        SitStatisticsBean ssb = new SitStatisticsBean();
        ssb.setUserId(userId);
        ssb.setTargetSitTimes(mUserDao.getUserSettings(userId).getSitBreakTimes());
        /*获取7天的数据*/
        List<SitStatisticsDataBean> sqlBeans = mStatisticsDao.getLast7SitBreakTimes(userId,last7,now);
        /*补足缺少的天数*/
        Iterator<SitStatisticsDataBean> it ;
        List<SitStatisticsDataBean> beans = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            String curDate = sdf.format(new Date(ms));

            SitStatisticsDataBean b = new SitStatisticsDataBean();
            b.setDate(new Date(ms));
            b.setThisDaySitTimes(0);
            beans.add(b);

            it = sqlBeans.iterator();
            while (it.hasNext()){
                SitStatisticsDataBean ib = it.next();
                if(sdf.format(ib.getDate()).equals(curDate)){
                    b.setThisDaySitTimes(ib.getThisDaySitTimes());
                    it.remove(); //这里remove sqlBeans也remove了
                    break;
                }
            }

            ms += 1000 * 3600 * 24 ; //date+1
        }

        ssb.setBeans(beans);
        return new Common<SitStatisticsBean>().create(ssb,null);
    }

    @RequestMapping(value = "/sleep7" ,method = RequestMethod.GET)
    public Common<SleepStatisticsBean> getSleep7(@RequestParam(value = "userId")int userId){
        if(mUserDao.getUserSettings(userId) == null){
            new Common<SportStatisticsBean>().create(null,null);
        }

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String now = sdf.format(new Date());
        long ms = System.currentTimeMillis();
        ms -= 1000 * 3600 * 24 * 6 ; //这是7日前的date
        String last7 = sdf.format(new Date(ms));

        SleepStatisticsBean ssb = new SleepStatisticsBean();
        ssb.setUserId(userId);
        ssb.setTargetSleepTime(mUserDao.getUserSettings(userId).getSleepTime());
        /*获取7天的数据*/
        List<SleepStatisticsDataBean> sqlBeans = mStatisticsDao.getLast7SleepTime(userId,last7,now);
        /*补足缺少的天数*/
        Iterator<SleepStatisticsDataBean> it ;
        List<SleepStatisticsDataBean> beans = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            String curDate = sdf.format(new Date(ms));

            SleepStatisticsDataBean b = new SleepStatisticsDataBean();
            b.setDate(new Date(ms));
            b.setThisDaySleepTime(null);
            beans.add(b);

            it = sqlBeans.iterator();
            while (it.hasNext()){
                SleepStatisticsDataBean ib = it.next();
                if(sdf.format(ib.getDate()).equals(curDate)){
                    b.setThisDaySleepTime(ib.getThisDaySleepTime());
                    it.remove(); //这里remove sqlBeans也remove了
                    break;
                }
            }

            ms += 1000 * 3600 * 24 ; //date+1
        }

        ssb.setBeans(beans);
        return new Common<SleepStatisticsBean>().create(ssb,null);
    }

//    public static void main(String[] args) {
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//        String now = sdf.format(new Date());
//        long ms = System.currentTimeMillis();
//        ms -= 1000 * 3600 * 24 * 6 ;
//        String last7 = sdf.format(new Date(ms));
//
//        System.out.println(last7);
//        System.out.println(now);
//    }
}
