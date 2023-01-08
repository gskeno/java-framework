package com.gson.algo.leetcode.slidingwindow;

import java.util.Arrays;

/**
 * https://leetcode.cn/problems/minimum-operations-to-reduce-x-to-zero/
 * 给你一个整数数组 nums 和一个整数 x 。每一次操作时，你应当移除数组 nums 最左边或最右边的元素，然后从 x 中减去该元素的值。请注意，需要 修改 数组以供接下来的操作使用。
 *
 * 如果可以将 x恰好 减到0 ，返回 最小操作数 ；否则，返回 -1 。
 *
 * 
 *
 * 示例 1：
 *
 * 输入：nums = [1,1,4,2,3], x = 5
 * 输出：2
 * 解释：最佳解决方案是移除后两个元素，将 x 减到 0 。
 * 示例 2：
 *
 * 输入：nums = [5,6,7,8,9], x = 4
 * 输出：-1
 * 示例 3：
 *
 * 输入：nums = [3,2,20,1,1,3], x = 10
 * 输出：5
 * 解释：最佳解决方案是移除后三个元素和前两个元素（总共 5 次操作），将 x 减到 0 。
 * 
 *
 * 提示：
 *
 * 1 <= nums.length <= 105
 * 1 <= nums[i] <= 104
 * 1 <= x <= 109
 *
 */
public class 将X减到0的最小操作数 {

    /**
     * 提示1: 找到一个最大窗口，使元素值之和 等于 taget = sumAll - x
     * 提示2: 如果找不到，返回-1; 如果找的到，返回 nums.length - windowSize
     * 提示3: 特别地，如果target < 0, 表示所有元素之和 小于 x，无法操作
     * @param nums
     * @param x
     * @return
     */
    public int minOperations(int[] nums, int x) {
        int n = nums.length;
        int target = Arrays.stream(nums).sum() - x;
        if (target < 0){
            return  - 1;
        }
        int left = 0;
        int right = 0;
        int region = 0;
        int maxSize = Integer.MIN_VALUE;
        // 也可以使用前缀和数组辅助
        while (right < n){
            // 每次都扩大右边界
            region += nums[right];

            // 尝试滑动左边界
            while (region > target){
                region -= nums[left];
                left++;
            }
            if (region == target){
                maxSize = Math.max(maxSize, right - left + 1);
            }
            right++;
        }
        return maxSize == Integer.MIN_VALUE ? -1 : (n - maxSize);
    }

    public static void main(String[] args) {
        将X减到0的最小操作数 solution = new 将X减到0的最小操作数();
        int ans = 0;
        ans = solution.minOperations(new int[]{1,1,4,2,3}, 5);
        System.out.println(ans);

        ans = solution.minOperations(new int[]{5,6,7,8,9}, 4);
        System.out.println(ans);

        ans = solution.minOperations(new int[]{3,2,20,1,1,3}, 10);
        System.out.println(ans);

        ans = solution.minOperations(new int[]{8828,9581,49,9818,9974,9869,9991,10000,10000,10000,9999,9993,9904,8819,1231,6309 },134365);
        System.out.println(ans);

        ans = solution.minOperations(new int[]{500, 1,4,2,3},500);
        System.out.println(ans);

        ans = solution.minOperations(new int[]{1,1},3);
        System.out.println(ans);
    }
}
