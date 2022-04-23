package com.gson.algo.huawei;

import org.junit.Test;

public class HJ20Test {
    @Test
    public void test(){
        HJ20密码验证合格程序 hj20Test = new HJ20密码验证合格程序();
        System.out.println(hj20Test.verify("021Abc9000"));
        System.out.println(hj20Test.verify("021Abc9Abc1"));
        System.out.println(hj20Test.verify("021ABC9000"));
        System.out.println(hj20Test.verify("021$bc9000"));
    }
}
