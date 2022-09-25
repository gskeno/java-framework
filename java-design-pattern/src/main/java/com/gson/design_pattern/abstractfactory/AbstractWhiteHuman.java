package com.gsonkeno.pattern.abstractfactory;

/**
 * Created by gaosong on 2018-01-18
 */
public abstract  class AbstractWhiteHuman implements Human {
    @Override
    public void talk() {
        System.out.println("白种人说英语");
    }

    @Override
    public void getColor() {
        System.out.println("白种人肤色是白色");
    }
    
}
