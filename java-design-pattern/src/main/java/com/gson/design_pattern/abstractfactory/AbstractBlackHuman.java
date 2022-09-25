package com.gsonkeno.pattern.abstractfactory;

/**
 * Created by gaosong on 2018-01-18
 */
public abstract  class AbstractBlackHuman implements Human {
    @Override
    public void talk() {
        System.out.println("黑人说话很快，且含糊不清");
    }

    @Override
    public void getColor() {
        System.out.println("黑人肤色是黑色");
    }
    
}
