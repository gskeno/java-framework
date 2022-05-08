package com.gson.algo.huawei;

import java.util.Scanner;

/**
 * https://www.nowcoder.com/practice/24e6243b9f0446b081b1d6d32f2aa3aa
 */
public class HJ103走梅花桩 {
    // 递归
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        while(scanner.hasNextInt()){
            int n = scanner.nextInt();
            int[] nums = new int[n];
            for(int i = 0; i<n; i++){
                nums[i] = scanner.nextInt();
            }
            int max = 1;
            for(int i=0; i< n; i++){
                // 从i位置开始走梅花桩
                max = Math.max(max, getMaxSteps(nums, i, nums[i]));
            }
            System.out.println(max);

        }
    }

    /**
     * 从begin位置开始走
     */
    public static int getMaxSteps(int[] nums, int begin, int compareObject){
        int max = 1;
        for(int i = begin; i< nums.length; i++){
            if(nums[i] > compareObject){
                max = Math.max(max, 1+getMaxSteps(nums, i+1, nums[i]));
            }
        }
        return max;
    }

    /**
     * 从begin位置开始走
     */
    public static int getMaxSteps(int[] nums, int begin){
        int max = 1;
        for(int i = begin; i< nums.length; i++){
            if(nums[i] > nums[begin]){
                max = Math.max(max, 1+getMaxSteps(nums, i));
            }
        }
        return max;
    }
}
