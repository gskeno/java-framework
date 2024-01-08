package com.gson.spring;

import com.gson.spring.bean.House;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class HouseTest {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath*:olympic_beans_test.xml");
//        House house = context.getBean(House.class);
//        System.out.println(house);
    }
}
