package com.gsonkeno.pattern.factorymethod;

/**
 * Created by gaosong on 2018-01-18
 */
public class YellowHuman implements Human {
    @Override
    public void talk() {
        System.out.println("黄种人说双字节");
    }

    @Override
    public void getColor() {
        System.out.println("黄种人的肤色是黄色");
    }
}
