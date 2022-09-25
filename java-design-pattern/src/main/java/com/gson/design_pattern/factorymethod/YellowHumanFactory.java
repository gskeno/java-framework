package com.gsonkeno.pattern.factorymethod;

/**
 * Created by gaosong on 2018-01-18
 */
public class YellowHumanFactory extends AbstractHumanFactory {
    @Override
    public Human createHuman() {
        return new YellowHuman();
    }
}
