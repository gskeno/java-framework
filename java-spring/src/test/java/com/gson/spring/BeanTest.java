package com.gson.spring;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class BeanTest {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(BeanConfig.class);
    }
}
