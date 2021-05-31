package com.gson.javajdk.lang;

import org.junit.Assert;
import org.junit.Test;

import java.util.Objects;

public class ObjectsTest {

    @Test
    public void testEquals(){
        Integer a = 1978;
        Long b = 1978L;

        //不同类型Number，比较返回false
        Assert.assertFalse(Objects.equals(a, b));
    }
}
