package com.gson.algo.huawei;

import org.junit.Test;

public class HJ32Test {
    @Test
    public void test(){
        System.out.println(HJ32密码截取.getPalindromicMaxLength("ABBA"));
        System.out.println(HJ32密码截取.getPalindromicMaxLength("ABBBA"));
        System.out.println(HJ32密码截取.getPalindromicMaxLength("12HHHHA"));
    }
}
