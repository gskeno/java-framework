package com.gson.javajdk.util;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

public class SetTest {

    @Test
    public void test(){
        Set<String> set = new HashSet<>();
        Assert.assertFalse(set.contains(null));
        Assert.assertFalse(set.contains(""));

        set.add("1");
        Assert.assertFalse(set.contains(null));
        Assert.assertFalse(set.contains(""));
    }
}
