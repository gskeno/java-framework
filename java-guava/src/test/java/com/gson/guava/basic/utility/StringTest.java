package com.gson.guava.basic.utility;

import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class StringTest {
    @Test
    public void testJoiner() throws IOException {
        List<String> fruits = Lists.newArrayList("apple", "banana", "orange");
        // 最常用方式，指定符号连接一个集合中的各个元素
        Assert.assertTrue("apple,banana,orange".equals(Joiner.on(",").join(fruits)));
        StringBuilder sb = Joiner.on(",").appendTo(new StringBuilder("fruits are "), fruits);
        Assert.assertEquals(sb.toString(), "fruits are apple,banana,orange");
    }

    @Test
    public void testSplitter(){
        String sequence = "a,b,c,d,";
        Iterator<String> iterator = Splitter.on(",").split(sequence).iterator();
        Assert.assertEquals(iterator.next(), "a");
        Assert.assertEquals(iterator.next(), "b");
        Assert.assertEquals(iterator.next(), "c");
        Assert.assertEquals(iterator.next(), "d");
        Assert.assertEquals(iterator.next(), "");

        String[] strings = sequence.split(",");
        System.out.println(strings.length);


        // String.split会静默把尾部的分隔符去掉,而Splitter不会
        String s = ",a,,b,,";
        String[] split = s.split(",");
        Assert.assertEquals(split.length, 4);
        Assert.assertArrayEquals(split, new String[]{"", "a", "","b"});

        // trimResults会将分割后的每个子串前后空格去掉
        Iterable<String> iterable = Splitter.on(",").trimResults().split(" a, b , c, d ");
        Lists.newArrayList(iterable).equals(Lists.newArrayList("a,b,c,d"));

        // omitEmptyStrings会将分割后的每个子串进行过滤，剔除掉空串
        Iterable<String> iterable1 = Splitter.on(",").omitEmptyStrings().split(" a, , d ");
        Lists.newArrayList(iterable1).equals(Lists.newArrayList("a,d"));

        // &作为第一道分割符，分割后的每个字串可以再被=分割成key,value，最终可将原字符串转化为一个map
        Splitter.MapSplitter mapSplitter = Splitter.on("&").withKeyValueSeparator("=");
        Map<String, String> map = mapSplitter.split("name=gs&age=20");
        Assert.assertEquals(map.get("name"), "gs");
        Assert.assertEquals(map.get("age"), "20");

        Splitter.MapSplitter mapSplitter1 = Splitter.on("&").withKeyValueSeparator(Splitter.on("="));
        Map<String, String> map1 = mapSplitter1.split("name=gs&age=20");
        Assert.assertEquals(map, map1);
    }
}
