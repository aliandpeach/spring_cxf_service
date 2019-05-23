package com.yk.test.datasource;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidPooledConnection;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.sql.SQLException;

public class MyDruidConnectionHolder
{
    public static DruidPooledConnection getConnection(DruidDataSource druidDataSource)
            throws IllegalAccessException, SQLException
    {
        MyDruidDataSource myDruidDataSource = new MyDruidDataSource();
        Field[] fields = druidDataSource.getClass().getSuperclass().getDeclaredFields();
        Field[] fields2 = druidDataSource.getClass().getDeclaredFields();
        Field[] fields3 = druidDataSource.getClass().getSuperclass().getSuperclass().getDeclaredFields();
        copy(druidDataSource, myDruidDataSource, fields);
        copy(druidDataSource, myDruidDataSource, fields2);
        copy(druidDataSource, myDruidDataSource, fields3);
        String orginalUrl = druidDataSource.getUrl();
        myDruidDataSource.setUrl(String.format(orginalUrl, ParamHolder.getInstance().getThreadLocalParam()));
        myDruidDataSource.setLastUpdateTimeMillis(System.currentTimeMillis());
        return myDruidDataSource.getConnection();
    }

    private static void copy(DruidDataSource druidDataSource, MyDruidDataSource myDruidDataSource, Field[] fields3) throws IllegalAccessException
    {
        for (Field field : fields3)
        {
            if (field == null)
            {
                continue;
            }
            if (!Modifier.isFinal(field.getModifiers()))
            {
                boolean accessible = field.isAccessible();
                field.setAccessible(true);
                Object value = field.get(druidDataSource);
                field.set(myDruidDataSource, value);
                field.setAccessible(accessible);
            }
        }
    }
}
