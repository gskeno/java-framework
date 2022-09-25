package com.gsonkeno.pattern.abstractfactory;

/**
 * Created by gaosong on 2018-01-18
 */
public class Client {
    public static void main(String[] args) {
        FemaleHumanFactory femaleHumanFactory = new FemaleHumanFactory();
        Human blackHuman = femaleHumanFactory.createBlackHuman();
        blackHuman.getColor();
        blackHuman.getSex();
        blackHuman.talk();
    }
}
