package com.gson.java8.stream;

import org.junit.Assert;
import org.junit.Test;

import java.util.*;
import java.util.function.BinaryOperator;
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

    /**
     * findFirst.orElse
     */
    @Test
    public void testFindFirstOrElse(){
        List<Integer> list = Arrays.asList(1,2,3,4,5);
        // 获取大于5的第一个数，拿到了就返回；如果没有，返回0
        Integer integer = list.stream().filter(o -> o>5).findFirst().orElse(0);
        System.out.println(integer);
    }

    /**
     * Optional.ofNullable().orElse()
     */
    @Test
    public void testOptionalOrElse(){
        List<String> list = null;
        // 如果list为空，返回一个空集合
        List<String> list1 = Optional.ofNullable(list).orElse(new ArrayList<>());
        System.out.println(list1);
    }

    @Test
    public void testMapReduce(){
        List<Point> points = new ArrayList<>();
        points.add(new Point(1L, 10L));
        points.add(new Point(2L, 11L));

        Long reduce = points.stream().map(o -> o.getValue()).
                reduce(26L, (aLong, aLong2) -> aLong + aLong2);

        Assert.assertTrue(reduce == 26 + 10 + 11);
    }
}
