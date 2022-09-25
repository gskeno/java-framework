package com.gsonkeno.pattern.factorymethod;

/**
 * Created by gaosong on 2018-01-18
 */
public class WhiteHuman implements Human{
    @Override
    public void talk() {
        System.out.println("白种人大多说英语");
    }

    @Override
    public void getColor() {
        System.out.println("白种人的肤色很白");
    }
}
