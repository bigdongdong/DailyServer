package com.cxd.daily.controller;


import com.cxd.daily.controller.response.Common;
import com.cxd.daily.dao.UserDao;
import com.cxd.daily.entity.UserBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
     * @param id 用户id
     * @return
     */
    @RequestMapping(value = "/info" , method = {RequestMethod.GET,RequestMethod.POST})
    public Common<UserBean> info(@RequestParam(value = "id")int id){
        String message = null ;
        UserBean userBean = mUserDao.getUserInfo(id) ;
        if(userBean == null){
            message = "用户信息丢失，请重新登录";
        }
        return new Common<UserBean>().create(userBean,message);
    }


}
