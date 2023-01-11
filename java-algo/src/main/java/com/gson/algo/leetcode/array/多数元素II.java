package com.gson.algo.leetcode.array;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.cn/problems/majority-element-ii/
 * 
 * 给定一个大小为n的整数数组，找出其中所有出现超过⌊ n/3 ⌋次的元素。
 *
 * 
 *
 * 示例1：
 *
 * 输入：nums = [3,2,3]
 * 输出：[3]
 * 示例 2：
 *
 * 输入：nums = [1]
 * 输出：[1]
 * 示例 3：
 *
 * 输入：nums = [1,2]
 * 输出：[1,2]
 * 
 *
 * 提示：
 *
 * 1 <= nums.length <= 5 * 104
 * -109 <= nums[i] <= 109
 * 
 *
 * 进阶：尝试设计时间复杂度为 O(n)、空间复杂度为 O(1)的算法解决此问题。
 *
 */
public class 多数元素II {

    /**
     * 提示1: 答案最多有两个值，最少有0个值。
     *       比如[1,2,3]中出现次数超过1次的元素没有
     *       [1,2]中出现次数超过0次的元素有[1,2]
     * 提示2: 摩尔投票算法，三个互不相同的元素可以抵消掉。剩下的元素最多只包含两种
     *       (因为如果超过2种，又会出现三个互不相同的元素可以抵消)
     * @param nums
     * @return
     */
    public List<Integer> majorityElement(int[] nums) {
        // 候选答案1，2
        int candidate1 = 0;
        int candidate2 = 0;
        int vote1 = 0;
        int vote2 = 0;
        for(int num : nums){
            // 如果候选值1存在，且当前值等于候选值1，投票+1
            if (vote1 > 0 && num == candidate1){
                vote1++;
            }
            // 如果候选值2存在，且当前值等于候选值2，投票+1
            else if (vote2 > 0 && num == candidate2){
                vote2++;
            }
            // 如果候选值1还没有，生成候选值1
            else if (vote1 == 0){
                vote1 = 1;
                candidate1 = num;
            }
            // 如果候选值2还没有，生成候选值2
            else if (vote2 == 0){
                vote2 = 1;
                candidate2 = num;
            }
            // 最后一种情况，出现三个互不相等的元素。比如nums=[1,2.3]
            else{
                vote1 --;
                vote2 --;
            }
        }
        //此时, candidate1, candidate2,还要检验其是否满足出现次数 > n/3的要求
        List<Integer> candidates = new ArrayList<>();
        int[][] voteAndCandidates = new int[][]{{vote1, candidate1}, {vote2, candidate2}};
        for(int[] voteAndCandidate : voteAndCandidates){
            int vote = voteAndCandidate[0];
            int candidate = voteAndCandidate[1];
            if (vote != 0){
                int cnt = 0;
                for(int num : nums){
                    if (num == candidate){
                        cnt++;
                    }
                }
                if (cnt > nums.length / 3){
                    candidates.add(candidate);
                }
            }
        }
        return candidates;
    }

    public static void main(String[] args) {
        多数元素II solution = new 多数元素II();
        List<Integer> ans;
        ans = solution.majorityElement(new int[]{3,2,3});
        System.out.println(ans);

        ans = solution.majorityElement(new int[]{1});
        System.out.println(ans);

        ans = solution.majorityElement(new int[]{1, 2});
        System.out.println(ans);

        ans = solution.majorityElement(new int[]{1, 2, 3});
        System.out.println(ans);
    }
}
