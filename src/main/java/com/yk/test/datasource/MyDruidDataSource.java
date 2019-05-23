package com.yk.test.datasource;

import com.alibaba.druid.pool.DruidConnectionHolder;
import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidPooledConnection;

public class MyDruidDataSource extends DruidDataSource
{

    private long lastUpdateTimeMillis;

    public long getLastUpdateTimeMillis()
    {
        return lastUpdateTimeMillis;
    }

    public void setLastUpdateTimeMillis(long lastUpdateTimeMillis)
    {
        this.lastUpdateTimeMillis = lastUpdateTimeMillis;
    }
}
