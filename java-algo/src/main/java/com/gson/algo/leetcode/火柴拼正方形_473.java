package com.gson.algo.leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

/**
 * https://leetcode.cn/problems/matchsticks-to-square/
 */
public class 火柴拼正方形_473 {
    private int state;

    public boolean makesquare(int[] matchsticks) {
        Arrays.sort(matchsticks);
        int sum = IntStream.of(matchsticks).sum();
        if (sum % 4 != 0) {
            return false;
        }
        int sideLen = sum / 4;
        // 全部元素都选择完后的选择状态用一个状态压缩后的值state来标记
        state = (1 << matchsticks.length) - 1;
        cache.clear();
        return dfs(0, matchsticks, 0, sideLen);
    }

    private Map<Integer, Boolean> cache = new HashMap<>();

    private boolean dfs(int curState, int[] nums, int summ, int sideLen) {
        // 所有边正好选完，拼成正方形
        if (cache.get(curState) == null){
            boolean res = false;
            if (curState == state) {
                res = true;
            } else {
                for (int i = 0; i < nums.length; i++) {
                    // nums[i]未选择，现在尝试选择
                    if ((curState & (1 << i)) == 0) {
                        if (summ + nums[i] > sideLen) {
                            break;
                        } else {
                            int nextState = curState | (1 << i);
                            if (summ + nums[i] == sideLen && dfs(nextState, nums, 0, sideLen)) {
                                res = true;
                                break;
                            } else if (dfs(nextState, nums, summ + nums[i], sideLen)) {
                                res = true;
                                break;
                            }
                        }
                    }
                }
            }
            cache.put(curState, res);
        }
        return cache.get(curState);
    }

    public static void main(String[] args) {
        火柴拼正方形_473 solution = new 火柴拼正方形_473();
        System.out.println(solution.makesquare(new int[]{1, 1, 2, 2, 2}));
        System.out.println(solution.makesquare(new int[]{3, 3, 3, 3, 4}));
    }
}
