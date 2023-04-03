package com.gson.algo.leetcode.array;

import java.util.Arrays;

public class 向数组中追加K个整数 {

    public long minimalKSum(int[] nums, int k) {
        Arrays.sort(nums);
        long ans = 0L;
        for(int i=0; i < nums.length; i ++){
            if(i == 0){
                int pre = 1;
                while(k > 0 && pre < nums[0]){
                    ans += pre;
                    pre += 1;
                    k--;
                }
            }

            if( i == nums.length - 1){
                int next = nums[i] + 1;
                while(k > 0){
                    ans += next;
                    next += 1;
                    k--;
                }
            }else{
                int cur = nums[i];
                int next = nums[i+1];
                while(cur + 1 < next && k > 0){
                    cur = cur + 1;
                    ans += cur;
                    k--;
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        向数组中追加K个整数 solution = new 向数组中追加K个整数();
        solution.minimalKSum(new int[]{5,6}, 6);
        System.out.println(solution.minimalKSum(new int[]{3}, 1));
    }
}
