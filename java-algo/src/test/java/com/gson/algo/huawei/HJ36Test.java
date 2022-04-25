package com.gson.algo.huawei;

import org.junit.Test;

public class HJ36Test {
    @Test
    public void test(){
        String s1 = "TRAILBLAZERS";
        String s2 = "Attack AT DAWN";

        String mix = HJ36字符串加密.mix(s1, s2);
        System.out.println(mix);
    }
}
