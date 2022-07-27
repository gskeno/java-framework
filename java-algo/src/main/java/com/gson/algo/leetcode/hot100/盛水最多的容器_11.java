package com.gson.algo.leetcode.hot100;

/**
 * https://leetcode.cn/problems/container-with-most-water/
 * <p>
 * 双指针
 * 左右指针初始时指向数组的左右边界，
 * <p>
 * 容水量 = 两个指针指向的数字的较小值 * 两个指针之间的距离。
 * <p>
 * 计算下当前指针位置下的容水量，下一次要考虑移动指针，获取可能的更大的容水量。
 * 考虑到如果移动指向数值大的那个指针往数组中间靠拢，则容水量必然会减小，所以忽略它。
 * 只能移动指向数值小的那个指针往数组中间靠拢。
 * <p>
 * 这样考虑的问题的数据规模就-1了。
 * <p>
 * 各种数据规模下的容水量的最大值就是最终的答案了。
 */
public class 盛水最多的容器_11 {

    public int maxArea(int[] height) {
        int left = 0;
        int right = height.length - 1;

        int max = 0;
        while (left < right) {
            max = Math.max(max, Math.min(height[left], height[right]) * (right - left ));
            if (height[left] <= height[right]) {
                left++;
            } else {
                right--;
            }
        }
        return max;
    }

    public static void main(String[] args) {
        盛水最多的容器_11 solution = new 盛水最多的容器_11();
        System.out.println(solution.maxArea(new int[]{1, 8, 6, 2, 5, 4, 8, 3, 7}));
        System.out.println(solution.maxArea(new int[]{1, 1}));
        System.out.println(solution.maxArea(new int[]{2, 3, 4, 5, 18, 17, 6}));
    }
}
