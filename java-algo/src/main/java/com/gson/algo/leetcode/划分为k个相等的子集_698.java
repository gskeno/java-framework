package com.gson.algo.leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.cn/problems/partition-to-k-equal-sum-subsets/
 */
public class 划分为k个相等的子集_698 {
    /**
     * dfs + 记忆化搜索
     *
     * @param nums
     * @param k
     * @return
     */
    public boolean canPartitionKSubsets(int[] nums, int k) {
        if (k == 1) {
            return true;
        }

        int len = nums.length;
        Arrays.sort(nums);
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        if (sum % k != 0) {
            return false;
        }
        int target = sum / k;
        if (nums[len - 1] > target) {
            return false;
        }


        return dfs(0, 0, nums, len, target);
    }

    /**
     * 能否根据state所表示的未使用数字集合，任意个元素组合，使其和都为target
     *
     * @param state
     * @param summ 当前已经获取到的累计和，不大于target。要尝试从nums中未选择的一个数中，找一个使其和未target
     * <p>
     * 这里加上缓存 state->bool
     */
    Map<Integer, Boolean> cache = new HashMap<>();

    private boolean dfs(int state, int summ, int[] nums, int n, int target) {
        if (cache.get(state) == null) {
            boolean res = false;
            // 表示原始数组中的所有元素都已经使用完
            if (state == (1 << n) - 1) {
                res = true;
            } else {
                // 遍历原始数组中的每个未使用的元素，都可以尝试累加在summ上，只要累加后和不超过target
                for (int i = 0; i < n; i++) {
                    // 表示第i个数未使用(i从0算起)
                    if ((state & (1 << i)) == 0) {
                        //尝试使用
                        if (summ + nums[i] > target) {
                            // 后续的数字比nums[i]更大，更不可能
                            break;
                        } else {
                            int nextState = state | (1 << i);
//                    注意:这种写法不对，这种写法表示选择任意一个未使用的数，达不到目标，目标就无法达成
//                    实际上应该是 选择一个未使用的数(任意选择)，有一种选择方案，能达到目标，就可以
//                    if (summ + nums[i] == target){
//                        return dfs(nextState, 0, nums, n, target);
//                    }else {
//                        return dfs(nextState, summ + nums[i], nums, n, target);
//                    }
                            // 修改如下:
                            // 寻找下一个子集合，使其和未target
                            if (summ + nums[i] == target && dfs(nextState, 0, nums, n, target)) {
                                res = true;
                            }
                            // 寻找下一个未使用元素，使当前子集元素和 + 下一个未使用元素 = target
                            else if (dfs(nextState, summ + nums[i], nums, n, target)) {
                                res = true;
                            }
                        }
                    }
                }
            }
            cache.put(state, res);
        }
        return cache.get(state);

    }


    public boolean canPartitionKSubsets2(int[] nums, int k) {
        if (k == 1) {
            return true;
        }

        int len = nums.length;
        Arrays.sort(nums);
        int sum = 0;
        for (int num : nums) {
            sum += num;
        }
        if (sum % k != 0) {
            return false;
        }
        int target = sum / k;
        if (nums[len - 1] > target) {
            return false;
        }

        // len数组长度不超过16
        int size = 1 << len;
        boolean[] dp = new boolean[size];
        // 表示输入数组元素个数为0时，是可以分割为k个等和子集的
        dp[0] = true;
        int[] currentSum = new int[size];
        for (int i = 0; i < size; i++) {
            // 总是基于 dp[i] = true 的前提下进行状态转移
            if (!dp[i]) {
                continue;
            }

            // 基于当前状态，添加一个数以后
            for (int j = 0; j < len; j++) {
                if ((i & (1 << j)) != 0) {
                    continue;
                }
                int next = i | (1 << j);
                if (dp[next]) {
                    continue;
                }
                if ((currentSum[i] % target) + nums[j] <= target) {
                    currentSum[next] = currentSum[i] + nums[j];
                    dp[next] = true;
                } else {
                    // 由于数组已经排好序，如果 (currentSum[i] % target) + nums[j] > target，剩下的数就没有必要枚举
                    break;
                }
            }
        }
        return dp[size - 1];
    }

    public boolean canPartitionKSubsets1(int[] nums, int k) {
        int sum = 0;
        int n = nums.length;
        for (int num : nums) {
            sum = sum + num;
        }
        if (sum % k != 0) {
            return false;
        }
        int regionSum = sum / k;
        Arrays.sort(nums);
        int start = 0;
        int end = n - 1;
        while (end > start) {
            int p = nums[end];
            while (p < regionSum && end > start) {
                p += nums[start];
                start++;
            }
            if (p > regionSum || start > end) {
                return false;
            }
            end--;
        }
        return true;
    }

    public static void main(String[] args) {
        划分为k个相等的子集_698 solution = new 划分为k个相等的子集_698();
        System.out.println(solution.canPartitionKSubsets(new int[]{4, 3, 2, 3, 5, 2, 1}, 4));
        // System.out.println(solution.canPartitionKSubsets(new int[]{2, 2, 3, 4, 4, 6, 7, 8, 10, 10}, 4));
        //System.out.println(solution.canPartitionKSubsets(new int[]{1,2,3,4}, 3));
    }
}
