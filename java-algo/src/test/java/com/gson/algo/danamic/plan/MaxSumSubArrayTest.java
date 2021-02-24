package com.gson.algo.danamic.plan;

import com.gson.algo.dynamic.plan.MaxSumSubArray;
import org.junit.Test;

import java.util.Arrays;

public class MaxSumSubArrayTest {
    @Test
    public void test2(){
        MaxSumSubArray maxSumSubArray = new MaxSumSubArray();
        int[] a = new int[]{1,-2,3,10,-4,7,2,-5};
        int max = maxSumSubArray.FindGreatestSumOfSubArray2(a);
        System.out.println(max);
    }
}
