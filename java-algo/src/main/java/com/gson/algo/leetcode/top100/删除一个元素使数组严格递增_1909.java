package com.gson.algo.leetcode.top100;

/**
 *  删除一个元素使数组严格递增
 *
 *  https://leetcode.cn/problems/remove-one-element-to-make-the-array-strictly-increasing/
 */
public class 删除一个元素使数组严格递增_1909 {

    public boolean canBeIncreasing(int[] nums) {
        // last表示遍历nums数组到当前(不算当前遍历值),严格递增数组的最大值
        int last = nums[0];
        int delCount = 0;
        for (int i = 1; i < nums.length; i++) {
            // 找到第一个不符合条件的元素
            // 如  1, 9 , 7, 10
            // 遍历到7时，7是不符合的，因为 7 > 9不成立
            // 这时可以尝试删除7或者9。但是当条件更严苛时，
            // 只能删除7，不能删除9，比如
            // 1, 8, 9, 7, 10
            // 当i遍历到7时， nums[i] <= nums[i-1]且 nums[i] <= nums[i-2]
            // 所以只能删除nums[i], 删除nums[i-1]是解决不了问题的

            if (nums[i] <= last){
                delCount++;
                if (delCount > 1){
                    break;
                }
                // 只能删除nums[i], 比如 1,8,9,7 只能删除7,不能删除9
                if (i - 2 >= 0 && nums[i] <= nums[i-2]){
                    last = nums[i-1];
                }
                // 删除nums[i-1]
                else {
                    last = nums[i];
                }
            }else {
                last = Math.max(nums[i], last);
            }
        }
        return delCount <= 1;

    }

    public static void main(String[] args) {
        删除一个元素使数组严格递增_1909 solution = new 删除一个元素使数组严格递增_1909();
        System.out.println(solution.canBeIncreasing(new int[]{1,2,10,5,7}));
        System.out.println(solution.canBeIncreasing(new int[]{2,3,1,2}));
        System.out.println(solution.canBeIncreasing(new int[]{1,1,1}));
        System.out.println(solution.canBeIncreasing(new int[]{1,2,3}));
    }
}
