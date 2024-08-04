package com.gson.javajdk.reflect;

import org.junit.Assert;
import org.junit.Test;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;

public class MethodHandleUsage {
    @Test
    public void test() throws Throwable {
        // 1. 创建查找对象(只支持公共方法)
        MethodHandles.Lookup publicLookup = MethodHandles.publicLookup();
        // 创建查找对象(支持公共方法，私有方法，受保护方法)
        MethodHandles.Lookup lookup = MethodHandles.lookup();

        // 查找当前运行类, 一般在使用日志系统定义log时，可用该方法返回结果作为参数
        Class<?> thisClass = lookup.lookupClass();
        Assert.assertEquals(thisClass.getSimpleName(), "MethodHandleUsage");

        // 2. 获取String的concat方法句柄，第三个参数要求传入一个MethodType, concat方法的返回类型是string, 参数类型也是string
        MethodHandle concatMethod = lookup.findVirtual(String.class, "concat", MethodType.methodType(String.class, String.class));

        // 3. 执行方法调用, "beijing".concat("university")
        Object result = concatMethod.invoke("beijing", "-university");
        Assert.assertEquals("beijing-university", result);
    }

}
