package com.gson.algo.backtrack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * https://leetcode.cn/problems/combination-sum-ii/
 * 每个元素只能使用一次
 *
 * 先排序
 */
public class LeetCode40组合总和II {

    /**
     * candidates中选择若干个元素，总和达到target,每个元素
     * @param candidates
     * @param target
     * @return
     */
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> tempCombination = new ArrayList<>();
        helper(result, candidates, target, tempCombination, 0);
        return result;
    }

    /**
     * 递归
     * @param result 最终的方案集合
     * @param candidates 候选元素
     * @param remaining  从候选元素中挑出一些元素，使其总和等于remaining
     * @param tempCombination 当前的一个选择方案
     * @param selectableIndex 可选择的元素在candidates中的位置索引，因为要返回组合，而不是排列，所以只能从selectableIndex及其后面
     *                    挑选元素
     */
    private void helper(List<List<Integer>> result, int[] candidates, int remaining, List<Integer> tempCombination, int selectableIndex){
        // 跳出递归，表示当前的选择方案是ok的，总和达到了target
        if (remaining == 0){
            if (result.contains(tempCombination)){
                return;
            }
            result.add(new ArrayList<>(tempCombination));
            return;
        }
        // 表示当前的选择方案使元素总和大于了target，弃用
        if (remaining < 0){
            return;
        }
        // 对于从selectableIndex位置开始的每一个候选元素，我们都可以尝试使用它，递归下去
        for (int i = selectableIndex; i <candidates.length ; i++) {
            // 选择
            tempCombination.add(candidates[i]);
            // 递归,下次只能从candidates的i+1位置开始选择元素
            helper(result, candidates, remaining- candidates[i], tempCombination, i+1);
            // 回溯，保持状态
            tempCombination.remove(tempCombination.size()-1);
        }
    }

    public static void main(String[] args) {
        LeetCode40组合总和II solution = new LeetCode40组合总和II();
        List<List<Integer>> lists;
        lists = solution.combinationSum2(new int[]{10,1,2,7,6,1,5}, 8);
        System.out.println(lists);

        lists = solution.combinationSum2(new int[]{2,5,2,1,2}, 5);
        System.out.println(lists);

    }
}
