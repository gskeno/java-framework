package com.gson.algo.leetcode.slidingwindow;

/**
 * https://leetcode.cn/problems/max-consecutive-ones-iii/
 * 
 * 给定一个二进制数组nums和一个整数 k，如果可以翻转最多 k 个 0 ，则返回 数组中连续 1 的最大个数 。
 *
 * 
 *
 * 示例 1：
 *
 * 输入：nums = [1,1,1,0,0,0,1,1,1,1,0], K = 2
 * 输出：6
 * 解释：[1,1,1,0,0,1,1,1,1,1,1]
 * 粗体数字从 0 翻转到 1，最长的子数组长度为 6。
 * 示例 2：
 *
 * 输入：nums = [0,0,1,1,0,0,1,1,1,0,1,1,0,0,0,1,1,1,1], K = 3
 * 输出：10
 * 解释：       [0,0,1,1,1,1,1,1,1,1,1,1,0,0,0,1,1,1,1]
 * 粗体数字从 0 翻转到 1，最长的子数组长度为 10。
 * 
 *
 * 提示：
 *
 * 1 <= nums.length <= 105
 * nums[i]不是0就是1
 * 0 <= k <= nums.length
 *
 */
public class 最大连续1的个数III {

    /**
     * 提示1: 固定左边界，尝试扩大右边界，记录窗口的左右边界，直到不满足要求时，左边界右移，再直至满足条件，循环往复。
     *        直至右边界到达数组末尾，整个个过程中记录 max(窗口大小)
     * @param nums
     * @param k
     * @return
     */
    public int longestOnes(int[] nums, int k) {
        int left = 0;
        int right = 0;
        int n = nums.length;
        int ans = 0;
        // 窗口内0的个数
        int countOf0 = 0;
        while (right < n){
            if (nums[right] == 0){
                // 0变多
                countOf0++;
            }
            // 0的个数超出限制，不断缩小左边界,直至符合要求
            while (countOf0 > k){
                if (nums[left] == 0){
                    countOf0--;
                }
                left++;
            }
            ans = Math.max(right - left + 1, ans);
            right++;
        }
        return ans;
    }

    public static void main(String[] args) {
        最大连续1的个数III solution = new 最大连续1的个数III();
        int ans = 0;
        ans = solution.longestOnes(new int[]{1,1,0,0,0,1,1,1,1,0}, 2);
        System.out.println(ans);

        ans = solution.longestOnes(new int[]{0,0,1,1,0,0,1,1,1,0,1,1,0,0,0,1,1,1,1},3);
        System.out.println(ans);
    }
}
