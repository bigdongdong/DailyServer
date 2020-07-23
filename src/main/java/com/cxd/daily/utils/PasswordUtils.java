package com.cxd.daily.utils;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

//123456 - e10adc3949ba59abbe56e057f20f883e

public class PasswordUtils {
    public static void main(String[] args) {
        /*生成MD5加密*/

        final String password = "123456";
        byte[] digest = null;
        try {
            MessageDigest md5 = MessageDigest.getInstance("md5");
            digest  = md5.digest(password.getBytes("utf-8"));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        //16是表示转换为16进制数
        String md5Str = new BigInteger(1, digest).toString(16);
        System.out.println(md5Str);


        rate(0);
        rate(7);
        rate(17);
        rate(27);
        rate(37);
        rate(47);
        rate(57);

    }

    public static void rate(int cur){
        float rate = 0 ;
        final int total = 50;
        int level = total / 5 ;
        if(cur >= total){
            rate = 1.0f;
        }else if(cur >= level){
            rate = Math.max(0, cur / level -1)* 0.25f + (cur % level * 0.25f) / (level * 1.0f);
        }else{
            rate = 0 ;
        }
        System.out.println(rate);
    }
}
