package com.gson.algo.leetcode.top100;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * https://leetcode.cn/problems/3sum/
 * <p>
 * 给你一个包含 n 个整数的数组nums，
 * 判断nums中是否存在三个元素 a，b，c ，使得a + b + c = 0 ？请你找出所有和为 0 且不重复的三元组。
 * <p>
 * 注意：答案中不可以包含重复的三元组。
 */
public class 三数之和_15 {

    /**
     * 1. 因为三元组要求不重复，我们第一感觉就是要对数组进行升序排序，否则分析会产生困难。
     * 比如 -4...-1...-4...-1...5。不排序的话，会有多种方式选择到-4，-1，而且去重也不好想办法。
     * <p>
     * 2. 排序后的数组又要如何分析呢? 比如数组[-1,-1,-1,-1,2]，和为0的三元组只有一个[-1,-1,2]。
     * 但是这两个-1，选择的是四个-1中的哪两个呢？
     * 理论上任意两个-1就可以了，只要不出现重复。
     * <p>
     * 但是写代码还是要确定一个选取规则的。
     * <p>
     * 当出现连续的同一个数字时，在枚举过程中，这两个相同数字作为一个整体只能出现一次，再出现是无意义的，我们选取最先出现的两个数字。
     * <p>
     * 要确保做到上述要求，要满足以下两点。
     * 1. 每层循环的起始位置都是上(外)层循环选中元素的下一个位置
     * 2. 每层循环中 当前出现的数字如果与前一个数字相同，则忽略掉，保证每层循环中一个数字只会被选择一次。
     * <p>
     * 特别地，需要注意到，当第一个元素被选中后，第二个元素在不断向右选择的过程中逐渐变大，第三个元素就需要不断向左选择的过程
     * 中逐渐变小，这个特点正好可以使用双指针来描述。
     *
     * @param nums
     * @return
     */
    public List<List<Integer>> threeSum(int[] nums) {
        if (nums.length < 3) {
            return new ArrayList<>();
        }
        List<List<Integer>> ans = new ArrayList<>();
        Arrays.sort(nums);
        // first表示第一个选中的元素对应的位置
        for (int first = 0; first < nums.length - 2; first++) {
            if (first > 0 && nums[first] == nums[first - 1]) {
                continue;
            }
            int target = -nums[first];
            // second表示第二个选中的元素对应的位置
            for (int second = first + 1; second < nums.length - 1; second++) {
                if (second > first + 1 && nums[second] == nums[second - 1]) {
                    continue;
                }
                // third表示第三个选中的元素对应的位置
                int third = nums.length - 1;
                if (nums[second] + nums[third] < target) {
                    continue;
                }

                while (nums[second] + nums[third] > target && third > second) {
                    third--;
                }

                if (nums[second] + nums[third] == target && third > second) {
                    List<Integer> list = new ArrayList<>();
                    list.add(nums[first]);
                    list.add(nums[second]);
                    list.add(nums[third]);
                    ans.add(list);
                    // 别忘了这里的continue
                    continue;
                }
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        三数之和_15 solution = new 三数之和_15();
        System.out.println(solution.threeSum(new int[]{-1, 0, 1, 2, -1, -4}));
    }
}
