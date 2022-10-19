package com.gson.reflections;

import org.junit.Test;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

/**
 * https://www.yisu.com/zixun/581493.html
 */
public class BasicTest {

    @Test
    public void test(){
        FooList foos = new FooList();
        Type superClass = foos.getClass().getGenericSuperclass();
        Type type = ((ParameterizedType) superClass).getActualTypeArguments()[0];
        System.out.println(type);
    }
}
