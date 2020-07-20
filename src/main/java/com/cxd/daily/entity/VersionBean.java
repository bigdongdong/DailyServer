package com.cxd.daily.entity;

import java.util.Date;

public class VersionBean {
    private String versionCode ;
    private String versionName ;
    private String md5Code ;
    private String apkUrl ;
    private String versionNotice ;
    private Date uploadDate ;

    public String getVersionCode() {
        return versionCode;
    }

    public void setVersionCode(String versionCode) {
        this.versionCode = versionCode;
    }

    public String getVersionName() {
        return versionName;
    }

    public void setVersionName(String versionName) {
        this.versionName = versionName;
    }

    public String getMd5Code() {
        return md5Code;
    }

    public void setMd5Code(String md5Code) {
        this.md5Code = md5Code;
    }

    public String getApkUrl() {
        return apkUrl;
    }

    public void setApkUrl(String apkUrl) {
        this.apkUrl = apkUrl;
    }

    public String getVersionNotice() {
        return versionNotice;
    }

    public void setVersionNotice(String versionNotice) {
        this.versionNotice = versionNotice;
    }

    public Date getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(Date uploadDate) {
        this.uploadDate = uploadDate;
    }
}
