package com.gson.algo.backtrack;


import java.util.ArrayList;
import java.util.List;

/**
 * 给定一个没有重复数字的正整数集合
 * 请列出所有元素之和 等于某个 给定值的所有组合。
 * 同一个数字可以在组合中出现任意次
 * 例如, 输入整数集合[2,3,5], 元素之和等于8的组合有3个，
 * 分别是[2,2,2,2], [2,3,3], [3,5]
 */
public class SumCertain {

    public List<List<Integer>> getResult(int[] nums, int sum){
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> subSet = new ArrayList<>();
        help(nums, 0, result, subSet, sum);
        return result;
    }

    /**
     *
     * @param nums
     * @param index 要处置的元素所在nums中的位置
     * @param result
     * @param subSet 当前可能的一个结果
     * @param left 离给定值距离
     */
    private void help(int[] nums, int index, List<List<Integer>> result, List<Integer> subSet, int left){
        if (left < 0){
            return;
        }
        if (left == 0){
            result.add(new ArrayList<>(subSet));
            return;
        }

        // 当前index位置不是最后一个元素，index位置元素不添加到结果集中，则接着处理下一个元素
        if (index < nums.length - 1){
            help(nums, index + 1, result, subSet, left);
        }

        // 当前index位置元素添加到结果集中去,下次还从当前位置处理是否要将index位置元素加入到结果集中去
        subSet.add(nums[index]);
        help(nums, index, result, subSet, left - nums[index]);
        // 恢复状态
        subSet.remove(subSet.size() - 1);
    }

    public static void main(String[] args) {
        SumCertain sumCertain = new SumCertain();
        int[] nums = {2,3,5};
        int sum = 8;
        List<List<Integer>> result = sumCertain.getResult(nums, sum);
        System.out.println(result);
    }
}
