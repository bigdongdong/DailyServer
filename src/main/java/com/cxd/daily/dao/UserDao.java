package com.cxd.daily.dao;

import com.cxd.daily.entity.UserBean;

public interface UserDao {
    /*是否有此人*/
    boolean isExistUser(String name);

    /*验证密码*/
    boolean verifyPassword(String name , String password);

    /*根据name获得id*/
    int getIdByName(String name);

    /*根据id获取个人信息*/
    UserBean getUserInfo(int id);
}
