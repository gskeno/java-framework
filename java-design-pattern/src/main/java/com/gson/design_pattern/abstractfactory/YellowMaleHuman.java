package com.gsonkeno.pattern.abstractfactory;

/**
 * Created by gaosong on 2018-01-18
 */
public class YellowMaleHuman extends AbstractYellowHuman {
    @Override
    public void getSex() {
        System.out.println("黄人男性");
    }
}
