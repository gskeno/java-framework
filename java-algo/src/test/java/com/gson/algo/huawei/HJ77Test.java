package com.gson.algo.huawei;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class HJ77Test {
    @Test
    public void test(){
        List<List<Integer>> allPath = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        HJ77火车进站.permutate(new int[]{1,2,3,4}, path, allPath);
        // 4*3*2=24种
        System.out.println("size=" + allPath.size());
        for(List<Integer> singlePath : allPath){
            System.out.println(singlePath);
        }
    }

    @Test
    public void testLegal(){
        System.out.println(HJ77火车进站.isLegal(new int[]{7,9,6}, Arrays.asList(6,9,7)));
        System.out.println(HJ77火车进站.isLegal(new int[]{7,9,6}, Arrays.asList(9,7,6)));
        System.out.println(HJ77火车进站.isLegal(new int[]{7,9,6}, Arrays.asList(6,7,9)));
    }

    @Test
    public void test2(){
        HJ77火车进站.handle(new int[]{6,  5, 4 ,8 ,1 ,3 ,7 ,2, 9 }, 9);
    }

    @Test
    public void testHuisu(){
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> temp = new ArrayList<>();
        int[] arr = new int[]{1,2,3};
        int n = 3;
        Stack<Integer> stack = new Stack<>();
        HJ77火车进站.huisu(result, temp, arr, n, stack, 0, 0);
        for (int i = 0; i < result.size(); i++) {
            System.out.println(result.get(i));
        }
    }
}
