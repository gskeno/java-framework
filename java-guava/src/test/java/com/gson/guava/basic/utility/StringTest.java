package com.gson.guava.basic.utility;

import com.google.common.base.Joiner;
import com.google.common.collect.Lists;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class StringTest {
    @Test
    public void testJoiner(){
        List<String> fruits = Lists.newArrayList("apple", "banana", "orange");
        // 最常用方式
        Assert.assertTrue("apple,banana,orange".equals(Joiner.on(",").join(fruits)));
    }
}
