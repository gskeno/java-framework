package com.gson.algo.huawei;

import org.junit.Test;

import java.util.Arrays;

public class 华为机考3Test {

    @Test
    public void test(){
        // -8, -20
        System.out.println(华为机考3.run(Arrays.asList(-8, 7, 2, 5, -20)));
        // -8, 7, 30
        System.out.println(华为机考3.run(Arrays.asList(-8, 7, 30, 5, -20)));
        // -8, 7, 30
        System.out.println(华为机考3.run(Arrays.asList(-8, 7, 30, 20, -20)));
        // -8, 7
        System.out.println(华为机考3.run(Arrays.asList(-8, 7, 20, 5, -20)));
        // -5
        System.out.println(华为机考3.run(Arrays.asList(1,2,3,-5)));
        // 5
        System.out.println(华为机考3.run(Arrays.asList(5, -1, -2,-3)));
        // -8, 7, 20, 5
        System.out.println(华为机考3.run(Arrays.asList(-8, 7, 20, 5, -4)));

        System.out.println(华为机考3.run(Arrays.asList(1,2,3,4)));

    }
}
