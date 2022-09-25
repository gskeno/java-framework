package com.gsonkeno.pattern.abstractfactory;

/**
 * Created by gaosong on 2018-01-18
 */
public class MaleHumanFactory implements HumanFactory {
    @Override
    public Human createBlackHuman() {
       return new BlackMaleHuman();
    }

    @Override
    public Human createYellowHuman() {
        return new YellowMaleHuman();
    }

    @Override
    public Human createWhiteHuman() {
        return new WhilteMaleHuman();
    }
}
