package com.gson.algo.leetcode.top100;

/**
 * https://leetcode.cn/problems/find-the-duplicate-number/
 */
public class 寻找重复数_287 {

    public int findDuplicate(int[] nums) {
        int slow = 0, fast = 0;
        do{
            slow = nums[slow];
            fast = nums[nums[fast]];
        }while (slow != fast);

        slow = 0;
        while (slow != fast){
            slow = nums[slow];
            fast = nums[fast];
        }

        return slow;
    }
}
