package com.gsonkeno.pattern.abstractfactory;

/**
 * Created by gaosong on 2018-01-18
 */
public class FemaleHumanFactory implements HumanFactory {
    @Override
    public Human createBlackHuman() {
       return new BlackFemaleHuman();
    }

    @Override
    public Human createYellowHuman() {
        return new YellowFemaleHuman();
    }

    @Override
    public Human createWhiteHuman() {
        return new WhilteFemaleHuman();
    }
}
