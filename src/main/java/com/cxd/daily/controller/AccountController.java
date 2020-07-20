package com.cxd.daily.controller;


import com.cxd.daily.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/account")
public class AccountController {
    @Autowired
    private UserDao mUserDao;

    /**
     * 登录
     * @param name
     * @param password
     * @return 返回用户的id
     */
    @RequestMapping(value = "/login" , method = RequestMethod.POST)
    public int login(@RequestParam(value = "name")String name ,
                     @RequestParam(value = "password")String password){
        return mUserDao.hasUser(name,password);
    }
}
