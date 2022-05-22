package com.gson.algo.bit;

import org.junit.Test;

public class 只出现一次的数字Test {

    @Test
    public void test(){
        只出现一次的数字 solution = new 只出现一次的数字();
        System.out.println(solution.singleNumber(new int[]{1391,1391,1391,342}));
    }

    @Test
    public void test1(){

        int m = 0x7fffffff;
        System.out.println(Integer.toHexString(m));
        System.out.println(m);
        System.out.println(Integer.toHexString(m <<1));
        System.out.println(m <<1);
    }
}
