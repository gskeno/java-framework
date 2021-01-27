package com.gson.javajdk.lang;

import org.junit.Test;

import java.lang.reflect.Method;

public class MethodTest {

    /**
     * 桥接方法测试
     */
    @Test
    public void testIsBridge(){
        // Long实现了Comparable<T> 范型接口
        // Long的方法中
        // int java.lang.Long.compareTo(java.lang.Object) 是桥接方法
        // int java.lang.Long.compareTo(java.lang.Long)自己实现的方法不是桥接方法
        Method[] methods = Long.class.getDeclaredMethods();
        for(Method method : methods){
            if (method.getName().equals("compareTo")){
                System.out.println(method + ":" + method.isBridge());
            }

        }

        // getDeclaredMethods方法是获取本类中的所有方法，
        // 包括私有的(private、protected、默认以及public)的方法。
        Method[] methods1 = ChildDo.class.getDeclaredMethods();
        for(Method method : methods1){
            System.out.println(method + ":" + method.isBridge());
        }


    }
}
