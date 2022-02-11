package com.gson.java8.stream;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FlatMapTest {

    @Test
    public void testFlatMap(){
        List<Integer> nums = new ArrayList<>();
        nums.add(1);
        nums.add(2);
        nums.add(3);

        Stream<List<String>> listStream = nums.stream().map(num -> {
            List<String> stringList = new ArrayList<>();
            stringList.add(num.toString());
            stringList.add(num + 1 + "");
            return stringList;
        });
        System.out.println(listStream.getClass());
        // Collection::stream <===> collection -> collection.stream() 将集合转成流对象
        // 可以发现平铺了
        Stream<String> stringStream = listStream.flatMap(strings -> strings.stream());
        Set<String> collect = stringStream.collect(Collectors.toSet());
        System.out.println(collect);
    }


}
