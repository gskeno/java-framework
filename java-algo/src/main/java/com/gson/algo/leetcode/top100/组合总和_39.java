package com.gson.algo.leetcode.top100;

import java.util.ArrayList;
import java.util.List;

/**
 * 给你一个 无重复元素 的整数数组candidates 和一个目标整数target，
 * 找出candidates中可以使数字和为目标数target 的 所有不同组合 ，并以列表形式返回。你可以按 任意顺序 返回这些组合。
 * <p>
 * candidates 中的 同一个 数字可以 无限制重复被选取 。如果至少一个数字的被选数量不同，则两种组合是不同的。
 * <p>
 * 对于给定的输入，保证和为target 的不同组合数少于 150 个。
 * <p>
 * <p>
 * 1 <= candidates.length <= 30
 * 1 <= candidates[i] <= 200
 * candidate 中的每个元素都 互不相同
 * 1 <= target <= 500
 * <p>
 * https://leetcode.cn/problems/combination-sum/
 */
public class 组合总和_39 {

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<Integer> path = new ArrayList<>();
        List<List<Integer>> ans = new ArrayList<>();
        dfs(candidates, 0, target, ans, path);
        return ans;
    }

    /**
     * 从candidates的k位置开始考虑选择数组元素，使其和等于target
     */
    public void dfs(int[] candidates, int k, int target, List<List<Integer>> ans, List<Integer> path) {
        if (target == 0) {
            ans.add(new ArrayList<>(path));
            return;
        }



        if (target < 0 || k >= candidates.length) {
            return;
        }

        // 选择k位置元素,下次还可以从k位置选择
        path.add(candidates[k]);
        dfs(candidates, k, target - candidates[k], ans, path);
        // 恢复状态
        path.remove(path.size() - 1);

        // 不选择k位置元素
        dfs(candidates, k + 1, target, ans, path);
    }

    public static void main(String[] args) {
        组合总和_39 solution = new 组合总和_39();
        System.out.println(solution.combinationSum(new int[]{2,3,5}, 8));
        System.out.println(solution.combinationSum(new int[]{2,3,5}, 1));
    }
}
