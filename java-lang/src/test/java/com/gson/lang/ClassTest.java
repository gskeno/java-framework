package com.gson.lang;

import org.junit.Assert;
import org.junit.Test;

public class ClassTest {

    /**
     * 测试 classA.isAssignableFrom(classB)的用法
     */
    @Test
    public void testAssignableFrom(){
        // Number是Float的父类
        Assert.assertTrue(Number.class.isAssignableFrom(Float.class));

        // Comparable是Float的接口
        Assert.assertTrue(Comparable.class.isAssignableFrom(Float.class));

        // 同类也是true
        Assert.assertTrue(Float.class.isAssignableFrom(Float.class));

        // 其他情况返回false
        Assert.assertFalse(Float.class.isAssignableFrom(Number.class));
    }
}
