package com.gsonkeno.pattern.flyweight;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by gaosong on 2017-11-15
 */
public class FlyWeightFactory {
    private Map<String,FlyWeight> weights = new HashMap<>();

    FlyWeight createFlyWeight(String key){
        if (weights.containsKey(key)){
            return weights.get(key);
        }else{
            ConcretFlyWeight weight = new ConcretFlyWeight();
            weights.put(key,weight);
            return weight;
        }
    }
}
