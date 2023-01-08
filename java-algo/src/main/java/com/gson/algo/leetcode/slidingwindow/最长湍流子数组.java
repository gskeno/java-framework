package com.gson.algo.leetcode.slidingwindow;

/**
 * 给定一个整数数组 arr，返回 arr的最大湍流子数组的长度。
 *
 * 如果比较符号在子数组中的每个相邻元素对之间翻转，则该子数组是湍流子数组。
 *
 * 更正式地来说，当 arr的子数组A[i], A[i+1], ..., A[j]满足仅满足下列条件时，我们称其为湍流子数组：
 *
 * 若i <= k < j：
 * 当 k为奇数时，A[k] > A[k+1]，且
 * 当 k 为偶数时，A[k] < A[k+1]；
 * 或 若i <= k < j：
 * 当 k 为偶数时，A[k] > A[k+1]，且
 * 当 k为奇数时，A[k] < A[k+1]。
 * 
 *
 * 示例 1：
 *
 * 输入：arr = [9,4,2,10,7,8,8,1,9]
 * 输出：5
 * 解释：arr[1] > arr[2] < arr[3] > arr[4] < arr[5]
 * 示例 2：
 *
 * 输入：arr = [4,8,12,16]
 * 输出：2
 * 示例 3：
 *
 * 输入：arr = [100]
 * 输出：1
 * 
 *
 * 提示：
 *
 * 1 <= arr.length <= 4 * 104
 * 0 <= arr[i] <= 109
 *
 
 */
public class 最长湍流子数组 {
    /**
     * 提示1: 数组长度为1时，直接返回1。
     * 提示2: 维护一个初始宽度为2的窗口，遍历到 i (1<i<n),
     *        如果nums[i] > nums[i-1] < nums[i-2],  可以扩展窗口右边界；
     *        如果nums[i] < nums[i-1] > nums[i-2], 也可以扩展窗口右边界；
     *        如果nums[i] = nums[i-1]，则left要设置为i；
     *        否则，左边界left = i -1
     * @param arr
     * @return
     */
    public int maxTurbulenceSize(int[] arr) {
        int n = arr.length;
        if ( n == 1){
            return 1;
        }
        // 设置初始值
        int ans = (arr[1] == arr[0] ? 1 : 2);
        int left = 0;
        int right = 2;
        while(right < n) {
            if (arr[right] == arr[right - 1]){
                left = right;
            }else if (arr[right] > arr[right - 1] && arr[right -1] < arr[right - 2]){
                ans = Math.max(ans, right - left + 1);
            }else if (arr[right] < arr[right - 1] && arr[right - 1] > arr[right - 2]){
                ans = Math.max(ans, right - left + 1);
            }else {
                left = right - 1;
                ans = Math.max(ans, arr[right] != arr[left] ? 2 : 1);
            }
            right++;
        }
        return ans;
    }

    public static void main(String[] args) {
        最长湍流子数组 solution = new 最长湍流子数组();
        int ans = 0;

        ans = solution.maxTurbulenceSize(new int[]{2,0,2,4,2,5,0,1,2,3});
        System.out.println(ans);

        ans = solution.maxTurbulenceSize(new int[]{0,1,1,0,1,0,1,1,0,0});
        System.out.println(ans);

        ans  = solution.maxTurbulenceSize(new int[]{9, 4, 2, 10, 7, 8, 8, 1, 9});
        System.out.println(ans);

        ans  = solution.maxTurbulenceSize(new int[]{4, 8, 12, 16});
        System.out.println(ans);

        ans  = solution.maxTurbulenceSize(new int[]{100});
        System.out.println(ans);

    }
}
