package com.gson.algo.huawei;

import org.junit.Test;

public class Hj92Test {
    @Test
    public void test(){
        String line = "a8a72a6a5yy98y65ee1r2";
        String result = HJ92在字符串中找出连续最长的数字串.getStrAndMaxLen(line);
        System.out.println(result);
        line = "abcd12345ed125ss123058789";
        result = HJ92在字符串中找出连续最长的数字串.getStrAndMaxLen(line);
        System.out.println(result);

    }
}
