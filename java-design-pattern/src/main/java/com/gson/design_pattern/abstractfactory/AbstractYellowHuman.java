package com.gson.design_pattern.abstractfactory;

/**
 * Created by gaosong on 2018-01-18
 */
public abstract  class AbstractYellowHuman implements Human {
    @Override
    public void talk() {
        System.out.println("黄种人说双字节");
    }

    @Override
    public void getColor() {
        System.out.println("黄种人肤色是黄色");
    }

}
