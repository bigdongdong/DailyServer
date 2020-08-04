package com.cxd.daily.dao;

import com.cxd.daily.entity.UserSettingBean;
import com.cxd.daily.entity.UserBean;

import java.sql.Date;
import java.sql.Time;

public interface UserDao {
    /*是否有此人*/
    boolean isExistUser(String name);

    /*验证密码*/
    boolean verifyPassword(String name , String password);

    /*根据name获得id*/
    int getIdByName(String name);

    /*根据id获取个人信息*/
    UserBean getUserInfo(int userId);

    /*获取用户设置*/
    UserSettingBean getUserSettings(int userId);

    /*插入一条用户设置*/
    boolean addUserSettings(int userId);

    /*更新用户设置*/
    boolean updateUserSettings(int userId , Long sportTime ,
                               Date sitStartTime , Date sitEndTime,
                               Long sitSpaceTime , Integer sitBreakTimes ,
                               Time sleepTime);
}
