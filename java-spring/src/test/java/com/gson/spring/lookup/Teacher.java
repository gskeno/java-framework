package com.gsonkeno.spring.bean;

/**
 * Created by gaosong on 2018-04-03
 */
public class Teacher extends User {

    @Override
    public void showMe() {
        System.out.println("i am a teacher");
    }
}
