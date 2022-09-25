package com.gsonkeno.pattern.abstractfactory;

/**
 * Created by gaosong on 2018-01-18
 */
public interface HumanFactory {

    public Human createBlackHuman();

    public Human createYellowHuman();

    public Human createWhiteHuman();
}
