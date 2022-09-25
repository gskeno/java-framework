package com.gsonkeno.pattern.factorymethod;

/**
 * Created by gaosong on 2018-01-18
 */
public class BlackHuman implements Human{
    @Override
    public void talk() {
        System.out.println("黑人大多说的很快，听不清");
    }

    @Override
    public void getColor() {
        System.out.println("黑人的肤色是黑色");
    }
}
