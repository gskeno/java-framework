package com.gson.javajdk;

import org.junit.Assert;
import org.junit.Test;

public class ThreadLocalTest {
    /**
     * 线程缓存开启的时间
     */
    private ThreadLocal<Long> beginTimeTL = new ThreadLocal<>();

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

    @Test
    public void test2(){
        // 由于没有设置初始化函数, beginTime is NULL
        Long beginTime = beginTimeTL.get();
        Assert.assertNull(beginTime);
    }
}
