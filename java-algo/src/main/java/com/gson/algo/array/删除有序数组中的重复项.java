package com.gson.algo.array;

/**
 * https://leetcode.cn/problems/remove-duplicates-from-sorted-array/
 */
public class 删除有序数组中的重复项 {
    /**
     * 输入：nums = [0,0,1,1,1,2,2,3,3,4]
     * 输出：5, nums = [0,1,2,3,4]
     * @param nums
     * @return
     */
    public int removeDuplicates(int[] nums) {
        int len = 1;
        for (int i = 1; i < nums.length; i++) {
            // 遍历原来数组中每个元素，与有序数组的最后一个元素相同，则不处理
            if (nums[i] == nums[len-1]){
                continue;
            }
            nums[len] = nums[i];
            len++;
        }
        return len;
    }

    public static void main(String[] args) {
        删除有序数组中的重复项 solution = new 删除有序数组中的重复项();
        int i = solution.removeDuplicates(new int[]{0, 0, 1, 1, 1, 2, 2, 3, 3, 4});
        System.out.println(i);
    }
}
