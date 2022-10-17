package com.gson.guava.basic.utility;

import com.google.common.base.CaseFormat;
import com.google.common.base.CharMatcher;
import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
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

    @Test
    public void testCharMatcher(){
        // 分成两部分 1.满足筛选条件的子串 2.满足筛选条件的子串如何处理
        // collapse有折叠的意思
        // 任意的字符都能匹配，被-替代
        Assert.assertEquals(CharMatcher.any().collapseFrom("bookstore", '-'), "-");
        // 字符串中任意的k,o,e都能匹配，被-替代
        Assert.assertEquals(CharMatcher.anyOf("koe").collapseFrom("bookstore", '-'),
                "b-st-r-");
        //'r'到't'之间的字符都能被-替代
        Assert.assertEquals(CharMatcher.inRange('r','t').collapseFrom("bookstore", '-'),
                "book-o-e");

        Assert.assertEquals(CharMatcher.anyOf("o").replaceFrom("bookstore", "-"),
                "b--kst-re");

    }

    @Test
    public void testCharset(){
        String student = new String("student");
        student.getBytes(StandardCharsets.UTF_8);
    }

    /**
     * 字符格式转换
     */
    @Test
    public void testCaseFormat(){
        // 小写驼峰转小写下划线
        String s = CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, "myScore");
        Assert.assertEquals(s, "my_score");
        // 小写驼峰转小写连字符'-'
        s = CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_HYPHEN, "myScore");
        Assert.assertEquals(s, "my-score");
        // 小写驼峰转大写驼峰(首字母大写)
        s = CaseFormat.LOWER_CAMEL.to(CaseFormat.UPPER_CAMEL, "myScore");
        Assert.assertEquals(s, "MyScore");
        // 小写驼峰转大写下划线
        s = CaseFormat.LOWER_CAMEL.to(CaseFormat.UPPER_UNDERSCORE, "myScore");
        Assert.assertEquals(s, "MY_SCORE");
    }
}
