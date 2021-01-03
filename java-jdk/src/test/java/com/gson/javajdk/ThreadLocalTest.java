package com.gson.javajdk;

import org.junit.Test;

public class ThreadLocalTest {
    @Test
    public void test(){
        ThreadLocal<Object> local = new ThreadLocal<Object>(){
            @Override
            protected Object initialValue() {
                return new String("131");
            }
        };
        Object s = local.get();
        System.out.println(s);
        local.remove();
        System.out.println(s);
    }
}
