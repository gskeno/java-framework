package com.gsonkeno.pattern.bridge;

/**
 * Created by gaosong on 2018-01-18
 */
public class IPhoneProduct extends Product {
    @Override
    public void beProduced() {
        System.out.println("苹果手机被生产出来了");
    }

    @Override
    public void beSelled() {
        System.out.println("苹果手机被卖了出去");
    }
}
