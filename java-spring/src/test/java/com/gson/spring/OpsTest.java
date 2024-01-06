package com.gson.spring;
import com.gson.spring.ops.OpsService;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class OpsTest {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath*:ops-test.xml");
        OpsService opsService =(OpsService) context.getBean("opsService");

//        KeyCenterWrapper keyCenterWrapper =(KeyCenterWrapper) context.getBean("keyCenterWrapper");
//
    }
}
