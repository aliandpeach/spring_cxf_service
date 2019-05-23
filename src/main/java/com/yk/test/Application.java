package com.yk.test;

import com.yk.test.restful.RestfulPublish;
import com.yk.test.service.TestService;
import com.yk.test.util.ContextUtil;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Application
{
    public static void main(String args[])
    {
        new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
        Executors.newFixedThreadPool(1).submit(() -> {
            RestfulPublish.getInstance().publish();
        });

        TestService testService = ContextUtil.getInstance().getBean(TestService.class);
        testService.testQuery("1");
        testService.testQuery("2");
        List<Map<String, String>> list = testService.testInterfaceMybatisQuery();
        System.out.println("testInterfaceMybatisQuery");
        System.out.println(list);
    }
}
