package com.cxd.daily.dao;

import com.cxd.daily.entity.VersionBean;

public interface VersionDao {
    VersionBean queryLastestVersion();
}
