package com.gsonkeno.pattern.flyweight;

/**
 * Created by gaosong on 2017-11-15
 */
public class ConcretFlyWeight implements FlyWeight {
    @Override
    public void createBigObj() {
        String[] str = new String[10];//创建大对象
    }
}
