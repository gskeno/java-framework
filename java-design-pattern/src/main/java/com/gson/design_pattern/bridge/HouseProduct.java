package com.gsonkeno.pattern.bridge;

/**
 * Created by gaosong on 2018-01-18
 */
public class HouseProduct  extends Product{
    @Override
    public void beProduced() {
        System.out.println("房子被建造好了");
    }

    @Override
    public void beSelled() {
        System.out.println("房子被卖了出去");
    }
}
