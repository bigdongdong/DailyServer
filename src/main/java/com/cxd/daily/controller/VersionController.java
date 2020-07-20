package com.cxd.daily.controller;

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


    @RequestMapping(value = "/get",method = RequestMethod.GET)
    public VersionBean getVersion(){
        return mVersionDao.queryLastestVersion();
    }

    @RequestMapping(value = "/downloadApk",method = RequestMethod.GET)
    public ResponseEntity<FileSystemResource> downloadApk(){
        /*centOS*/
        File file = new File(mVersionDao.queryLastestVersion().getApkUrl());
        /*windows*/
        file = new File("F:\\git_projects\\Daily\\app\\build\\outputs\\apk\\debug\\app-debug.apk");
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
