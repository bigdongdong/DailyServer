package com.cxd.daily.utils;

import com.cxd.daily.dao.VersionDao;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.io.FileInputStream;
import java.math.BigInteger;
import java.security.MessageDigest;

public class VersionUtils {
    public static void main(String[] args) {
        File file = new File("F:\\git_projects\\Daily\\app\\build\\outputs\\apk\\debug\\Daily-3.0-debug.apk");

        if (!file.isFile()) {
            return ;
        }
        MessageDigest digest = null;
        FileInputStream in = null;
        byte buffer[] = new byte[1024];
        int len;
        try {
            digest = MessageDigest.getInstance("MD5");
            in = new FileInputStream(file);
            while ((len = in.read(buffer, 0, 1024)) != -1) {
                digest.update(buffer, 0, len);
            }
            in.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        BigInteger bigInt = new BigInteger(1, digest.digest());
        System.out.println(bigInt.toString(16));
    }
}
