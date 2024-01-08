package com.gson.guava.cache;

import java.util.HashMap;
import java.util.Map;

public class HashMapTest {
    public static void main(String[] args) {
        Map<String,Integer> map = new HashMap<>();
        map.put(null, null);
//        System.out.println(map.get(null));
        System.out.println(map.containsKey(null));
    }
}
