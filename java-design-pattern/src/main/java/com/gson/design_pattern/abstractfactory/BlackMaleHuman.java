package com.gsonkeno.pattern.abstractfactory;

/**
 * Created by gaosong on 2018-01-18
 */
public class BlackMaleHuman extends AbstractBlackHuman {
    @Override
    public void getSex() {
        System.out.println("黑人男性");
    }
}
