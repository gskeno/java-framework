package com.gson.javajdk.clazz;

import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

public class ClazzTest {
    @Test
    public void test(){
        Class<?> clazz = LinkedList.class;
        Class<? extends List> aClass = clazz.asSubclass(List.class);

        System.out.println(aClass);
    }

    @Test
    public void testCast(){
        List<String> a= new LinkedList<>();
        a.add("1");

        Object b = a;
        /**
         * 将对象转化为某特定类
         */
        List cast = List.class.cast(b);
        System.out.println(cast);

        cast = (List)a;
        System.out.println(cast);
    }
}
