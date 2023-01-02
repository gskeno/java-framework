package com.gson.algo.leetcode.slidingwindow;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * https://leetcode.cn/problems/jump-game-vi/
 * 给你一个下标从 0 开始的整数数组 nums和一个整数 k。
 *
 * 一开始你在下标0处。每一步，你最多可以往前跳k步，但你不能跳出数组的边界。也就是说，
 * 你可以从下标i跳到[i + 1， min(n - 1, i + k)]包含 两个端点的任意位置。
 *
 * 你的目标是到达数组最后一个位置（下标为 n - 1），你的 得分为经过的所有数字之和。
 *
 * 请你返回你能得到的 最大得分。
 *
 */
public class 跳跃游戏VI {

    public int maxResult(int[] nums, int k) {
        // 设置函数f(i)表示以从下标0跳到下标i 的最高分，i变大的过程中，将所有的{f(i),i}结果保存到优先队列中
        // 优先队列, （maxScore, maxScoreIdx) 最高分，最高分对应的元素下标
        PriorityQueue<int[]> queue = new PriorityQueue<>((o1, o2) -> o2[0] - o1[0]);
        queue.offer(new int[]{nums[0], 0});
        int n = nums.length;
        int res = nums[0];
        for (int i = 1; i < n; i++) {
            // 目前的最高分的索引可能不再 [i-k, i]范围内了，不可取，以后也不可取
            while ( i - queue.peek()[1] > k){
                queue.poll();
            }
            // res不一定是优先队列中最大的
            res = queue.peek()[0] + nums[i];
            queue.offer(new int[]{res, i});
        }
        return res;
    }
}
