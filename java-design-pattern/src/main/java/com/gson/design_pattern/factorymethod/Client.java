package com.gsonkeno.pattern.factorymethod;

/**
 * Created by gaosong on 2018-01-18
 */
public class Client {

    public static void main(String[] args) {
        YellowHumanFactory yellowHumanFactory = new YellowHumanFactory();
        Human human = yellowHumanFactory.createHuman();
        human.getColor();
        human.talk();
    }
}
