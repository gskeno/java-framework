package com.gson.javajdk.lang;

import java.util.HashMap;
import java.util.Map;

public class MapTest {

    public static void main(String[] args) {
        Map<Long, Long> map = new HashMap<>();

        map.put(null, 10L);

        System.out.println(map);
    }
}
