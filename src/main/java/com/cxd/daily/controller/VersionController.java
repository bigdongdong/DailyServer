package com.cxd.daily.controller;

import com.cxd.daily.controller.response.Common;
import com.cxd.daily.dao.VersionDao;
import com.cxd.daily.entity.VersionBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.util.Date;

@RestController
@RequestMapping(value = "/version")
public class VersionController {
//    @Value("${version.code}")
//    private String versionCode;
//
//    @Value("${version.name}")
//    private String versionName ;

    @Autowired
    private VersionDao mVersionDao ;

    /**
     * 获取服务器最新版本信息
     * @return
     */
    @RequestMapping(value = "/get",method = RequestMethod.GET)
    public Common<VersionBean> getVersion(){
        VersionBean vb = null ;
        String message = null ;
        vb = mVersionDao.queryLastestVersion();
        if(vb == null){
            message = "服务器异常";
        }
        return new Common<VersionBean>().create(vb,message);
    }

    /**
     * 下载最新版本的APK
     * @return
     */
    @RequestMapping(value = "/downloadApk",method = RequestMethod.GET)
    public ResponseEntity<FileSystemResource> downloadApk(){
        /*centOS*/
        File file = new File(mVersionDao.queryLastestVersion().getApkUrl());
        HttpHeaders headers = new HttpHeaders();
        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
        headers.add("Content-Disposition", "attachment; filename=" + file.getName());
        headers.add("Pragma", "no-cache");
        headers.add("Expires", "0");
        headers.add("Last-Modified", new Date().toString());
        headers.add("ETag", String.valueOf(System.currentTimeMillis()));
        headers.add("Content-Length", String.valueOf(file.length()));
        return ResponseEntity
                .ok()
                .headers(headers)
                .contentLength(file.length())
                .contentType(MediaType.parseMediaType("application/octet-stream"))
                .body(new FileSystemResource(file));
    }
}
