package com.gson.algo.array;

/**
 * https://leetcode.cn/problems/non-decreasing-array/
 */
public class 非递减数列 {
    public boolean checkPossibility(int[] nums) {
        int usedCount = 0;
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] < nums[i-1]){
                if (usedCount > 0){
                    return false;
                }
                //     .
                //          .
                //               .
                //     i-1  i   i+1
                if (i + 1 < nums.length && nums[i+1] < nums[i]){
                    return false;
                }

                // 只能尝试使nums[i-1]变小
                if (i + 1 < nums.length && nums[i+1] == nums[i]){
                    if (i - 2 >= 0 && nums[i-1] == nums[i-2]){
                        return false;
                    }
                }
                // 只能尝试使nums[i-1]变小,其最多只能减小到nums[i-2]
                if ( i + 1 < nums.length && nums[i+1] < nums[i-1]){
                    if (i - 2 >= 0 && nums[i-1] == nums[i-2]){
                        return false;
                    }
                    if (i - 2 >= 0 && nums[i] < nums[i-2]){
                        return false;
                    }
                }
                usedCount++;
            }
        }
        return usedCount <= 1;
    }

    public static void main(String[] args) {
        非递减数列 solution = new 非递减数列();
        boolean ans = false;
        ans = solution.checkPossibility(new int[]{4,2,3});
        System.out.println(ans);
        ans = solution.checkPossibility(new int[]{4,2,1});
        System.out.println(ans);
    }
}
