package com.gson.guava;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import org.junit.Assert;
import org.junit.Test;

public class BiMapTest {
    @Test
    public void test(){
        HashBiMap<String, String> hbm = HashBiMap.<String, String>create();
        hbm.put("taobao","1");
        hbm.put("eleme","2");

        BiMap<String, String> inverse1 = hbm.inverse();
        BiMap<String, String> inverse2 = hbm.inverse();

        Assert.assertEquals(inverse1, inverse2);
        Assert.assertEquals(inverse1.get("1"), "taobao");
    }
}
