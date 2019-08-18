package com.gson.java8.stream;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class StreamTests {

    /**
     * 一种Map<K1,V1>转化为另一种Map<K2,V2>
     */
    @Test
    public void mapToMap() {
        Map<Integer, Integer> intMap = new HashMap<>();
        intMap.put(1, 1);
        intMap.put(2, 2);
        intMap.put(3, 3);

        Map<String, String> collect = intMap.entrySet().stream().map(entry ->
                new HashMap.SimpleEntry<>(
                        entry.getKey() + 1 + "",
                        entry.getValue() + 1 + "")).
                collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        System.out.println(collect);
    }
}
