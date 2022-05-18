package com.gson.algo.backtrack;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.cn/problems/permutations/
 * <p>
 * 输入：nums = [1,2,3]
 * 输出：[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
 */
public class LC46全排列 {

    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> tempPath = new ArrayList<>();
        helper(result, nums, tempPath);
        return result;
    }

    /**
     *
     * 难点在于递归时怎么确定哪些元素是已经选择过的。官方题解中，在待选择元素列表中每选择一个元素后，
     * 就将被选择元素与待选择元素列表的第一个元素互换，则下一次递归时待选择元素列表将从当前待选择列表的
     * 下一索引位置算起。
     * @param result   最终的结果集合
     * @param nums     可选择的元素集合
     * @param tempPath 当前的一个选择中的排列
     */
    private void helper(List<List<Integer>> result, int[] nums, List<Integer> tempPath) {
        // 没有可选择元素，则结束
        if (nums.length == 0) {
            result.add(new ArrayList<>(tempPath));
            return;
        }

        for (int selectNum : nums) {
            // 选择
            tempPath.add(selectNum);
            int[] optionalNums = new int[nums.length - 1];
            int index = 0;
            for (int num : nums) {
                if (num != selectNum) {
                    optionalNums[index++] = num;
                }
            }
            // 递归
            helper(result, optionalNums, tempPath);
            // 恢复状态
            tempPath.remove(tempPath.size() - 1);
        }
    }

    public static void main(String[] args) {
        LC46全排列 solution = new LC46全排列();
        List<List<Integer>> list;
        list = solution.permute(new int[]{1, 2, 3});
        System.out.println(list);

        list = solution.permute(new int[]{0,1});
        System.out.println(list);

        list = solution.permute(new int[]{1});
        System.out.println(list);

    }
}
