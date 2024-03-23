package com.gson;

import static org.junit.Assert.assertTrue;

import com.gson.springaop.LogService;
import com.gson.springaop.services.ReadService;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Unit test for simple App.
 */
public class AppTest 
{

    @Test
    public void test(){
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("springaop.xml");
        LogService bean = context.getBean(LogService.class);
        System.out.println(bean.getClass());
        bean.getA();
    }

}
