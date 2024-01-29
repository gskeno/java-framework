package com.gson.spring;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class BeanTest {

    public static void main(String[] args) throws InterruptedException {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(BeanConfig.class);
        PersonEventListener personEventListener = new PersonEventListener();
        context.addApplicationListener(personEventListener);

        Person person = new Person();
        BasicEvent<Person> personBasicEvent = new BasicEvent<>(person);
        context.publishEvent(personBasicEvent);

        Thread.sleep(3000);
    }
}
