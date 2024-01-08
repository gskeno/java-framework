package com.gson.guava.cache;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ConcurrentHashMapTest {
    public static void main(String[] args) {
        System.out.println("begin");
        Map<String, Integer> map = new ConcurrentHashMap<>(16);
        map.computeIfAbsent("AaAa", key -> map.computeIfAbsent("BBBB", key2 -> 42));
        //map.computeIfPresent("AaAa", (key,value) -> map.computeIfPresent("BBBB", (key2,value2) ->42));
        System.out.println("end");
    }
}
