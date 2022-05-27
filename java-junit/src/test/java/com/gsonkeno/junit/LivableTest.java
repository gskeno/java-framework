package com.gsonkeno.junit;

import org.junit.Test;

public class LivableTest {

    /**
     * test package中有 main package中的同名类，则加载测试包中的类
     */
    @Test
    public void test(){
        Livable livable = new HongKong();
        livable.live();
    }
}
