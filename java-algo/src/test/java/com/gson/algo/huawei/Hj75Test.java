package com.gson.algo.huawei;

import org.junit.Test;

public class Hj75Test {
    @Test
    public void test(){
        String s1 = "asdfas";
        String s2 = "werasdfaswer";
        int commonLen = HJ75公共子串计算.getCommonLen(s1, s2);
        System.out.println(commonLen);
    }
}
