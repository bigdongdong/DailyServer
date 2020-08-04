package com.cxd.daily.controller;


import com.cxd.daily.controller.response.Common;
import com.cxd.daily.dao.UserDao;
import com.cxd.daily.entity.UserBean;
import com.cxd.daily.entity.UserSettingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.sql.Time;
import java.text.SimpleDateFormat;

@RestController
@RequestMapping(value = "/account")
public class AccountController {
    @Autowired
    private UserDao mUserDao;

    /**
     * 登录
     * @param name
     * @param password 传的是密文
     * @return 返回用户的id
     */
    @RequestMapping(value = "/login" , method = {RequestMethod.GET,RequestMethod.POST})
    public Common<UserBean> login(@RequestParam(value = "name")String name ,
                                 @RequestParam(value = "password")String password){
        UserBean userBean = null;
        String message = null ;
        if(!mUserDao.isExistUser(name)){
            message = "查无此人";
        }else if(!mUserDao.verifyPassword(name,password)){
            message = "密码错误";
        }else{
            userBean = mUserDao.getUserInfo(mUserDao.getIdByName(name));
        }
        return new Common<UserBean>().create(userBean,message);
    }

    /**
     * 获取用户信息
     * @param userId 用户id
     * @return
     */
    @RequestMapping(value = "/info" , method = {RequestMethod.GET,RequestMethod.POST})
    public Common<UserBean> info(@RequestParam(value = "userId")int userId){
        String message = null ;
        UserBean userBean = mUserDao.getUserInfo(userId) ;
        if(userBean == null){
            message = "用户信息丢失，请重新登录";
        }
        return new Common<UserBean>().create(userBean,message);
    }


    /**
     * 获取用户设置
     * @param userId
     * @return message===null
     */
    @RequestMapping(value = "/settings" , method = RequestMethod.GET)
    public Common<UserSettingBean> settings(@RequestParam(value = "userId")int userId){
        UserSettingBean usb = mUserDao.getUserSettings(userId);
        return new Common<UserSettingBean>().create(usb,null);
    }

    /**
     * 更新设置
     * @param userId
     * @param sportTime
     * @param sitStartTime
     * @param sitEndTime
     * @param sitSpaceTime
     * @param sitBreakTimes
     * @param sleepTime
     * @return true成功 flase失败 message===null
     */
    @RequestMapping(value = "/updateSettings",method = {RequestMethod.GET,RequestMethod.POST})
    public Common<Boolean> updateSettings(@RequestParam(value = "userId",required = true)int userId,
                                         @RequestParam(value = "sportTime",required = false)Long sportTime,
                                         @RequestParam(value = "sitStartTime",required = false) Date sitStartTime,
                                         @RequestParam(value = "sitEndTime",required = false)Date sitEndTime,
                                         @RequestParam(value = "sitSpaceTime",required = false)Long sitSpaceTime,
                                         @RequestParam(value = "sitBreakTimes",required = false)Integer sitBreakTimes,
                                         @RequestParam(value = "sleepTime",required = false) Time sleepTime
                                        ){
        UserSettingBean usb = mUserDao.getUserSettings(userId);
        if(usb == null){
            mUserDao.addUserSettings(userId);
        }
        boolean count = mUserDao.updateUserSettings(userId,sportTime,sitStartTime,sitEndTime,sitSpaceTime,sitBreakTimes,sleepTime);
        return new Common<Boolean>().create(count,null);
    }

}
