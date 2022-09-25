package com.gsonkeno.pattern.bridge;

/**
 * Created by gaosong on 2018-01-18
 */
public class IpodProduct extends Product {
    @Override
    public void beProduced() {
        System.out.println("ipod被生产了出来");
    }

    @Override
    public void beSelled() {
        System.out.println("ipod被销售了出去");
    }
}
