package com.gson.spring;

import com.gson.spring.bean.Forest;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ForestTest {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath*:forest.xml");
        Forest forest = context.getBean(Forest.class);
        System.out.println(forest);
    }
}
