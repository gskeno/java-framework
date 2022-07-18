package com.gson.algo.leetcode.top100;

import java.util.Arrays;

/**
 * https://leetcode.cn/problems/sort-colors/
 */
public class 颜色分类_75 {

    /**
     * 输入：nums = [2,0,2,1,1,0]
     * 输出：[0,0,1,1,2,2]
     * <p>
     * <p>
     * 输入：nums = [2,0,1]
     * 输出：[0,1,2]
     * <p>
     * 设置pos表示已经遍历过的要交换的位置
     *
     * @param nums
     */
    public void sortColors(int[] nums) {
       int zeroCount = 0;
       int oneCount = 0;
       int secondCount = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0){
                zeroCount++;
            }else if (nums[i] == 1){
                oneCount++;
            }else if (nums[i] == 2){
                secondCount++;
            }
        }
        int i = 0;
        while (zeroCount > 0){
            nums[i++] = 0;
            zeroCount--;
        }
        while (oneCount > 0){
            nums[i++] = 1;
            oneCount--;
        }
        while (secondCount > 0){
            nums[i++] = 2;
            secondCount--;
        }
    }

    public static void main(String[] args) {
        颜色分类_75 solution = new 颜色分类_75();
        int[] ans = new int[]{2, 0, 2, 1, 1, 0};
        solution.sortColors(ans);
        System.out.println(Arrays.toString(ans));
    }
}
