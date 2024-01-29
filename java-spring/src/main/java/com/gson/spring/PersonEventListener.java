package com.gson.spring;

import org.springframework.context.ApplicationListener;

public class PersonEventListener implements ApplicationListener<BasicEvent<Person>> {
    @Override
    public void onApplicationEvent(BasicEvent<Person> basicEvent) {
        System.out.println("收到" + basicEvent);
    }
}
