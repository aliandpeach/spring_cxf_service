package com.yk.test.datasource;

public class ParamHolder
{
    private ThreadLocal<String> threadLocal = new ThreadLocal<>();

    public static ParamHolder getInstance()
    {
        return ParamHolderInner.instance;
    }

    public String getThreadLocalParam()
    {
        return threadLocal.get();
    }

    public void setThreadLocalParam(String id)
    {
        threadLocal.set(id);
    }

    private static class ParamHolderInner
    {
        private static ParamHolder instance = new ParamHolder();
    }
}
