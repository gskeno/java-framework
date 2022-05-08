package com.gson.algo.huawei;

import org.junit.Test;

public class HJ70Test {
    @Test
    public void test(){
        int n = 3;
        int[][] array = new int[][]{
                new int[]{50,10},
                new int[]{10,20},
                new int[]{20,5}
        };
        String expression = "(A(BC))";
        HJ70矩阵乘法计算量估算.printComplexity(n ,array, expression);
    }
}
