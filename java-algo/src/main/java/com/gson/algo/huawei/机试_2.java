package com.gson.algo.huawei;

import java.util.Scanner;

/**
 * 删除一个元素使数组有序
 */
public class 机试_2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int line1 = sc.nextInt();
        int[] nums = new int[line1];
        for (int i = 0; i < line1; i++) {
            int ele = sc.nextInt();
            nums[i] = ele;
        }
        int ans = isAddOrMinus(nums);
        // 递增的
        if (ans == 1) {
            System.out.println(nums[0]);
            return;
        }
        // 递减的
        else if (ans == 2) {
            System.out.println(nums[nums.length - 1]);
            return;
        }


        // 非递增非递减的,尝试删除delIdx元素
        int resp = Integer.MAX_VALUE;
        for (int delIdx = 0; delIdx < nums.length; delIdx++) {
            int tmp = nums[delIdx];
            if (delIdx != nums.length - 1) {
                nums[delIdx] = nums[delIdx + 1];
            } else {
                nums[delIdx] = nums[delIdx - 1];
            }
            int res = isAddOrMinus(nums);
            if (res == 1 || res == 2) {
                resp = Math.min(resp, tmp);
            }
            nums[delIdx] = tmp;
        }

        if (resp == Integer.MAX_VALUE) {
            System.out.println("-1");
            return;
        }

        System.out.println(resp);
    }

    // nums是递增或者递减的
    // 1是递增
    // 2是递减
    // 0不是递增也不是递减
    public static int isAddOrMinus(int[] nums) {
        // 递增的
        boolean add = true;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] < nums[i - 1]) {
                add = false;
            }
        }
        if (add) {
            return 1;
        }
        // 递减的
        boolean minus = true;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > nums[i - 1]) {
                minus = false;
            }
        }
        if (minus) {
            return 2;
        }

        return 0;
    }
}
