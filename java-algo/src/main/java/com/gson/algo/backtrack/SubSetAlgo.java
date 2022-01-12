package com.gson.algo.backtrack;

import java.util.ArrayList;
import java.util.List;

/**
 * 寻找所有的子集集合
 */
public class SubSetAlgo {

    private List<List<Integer>> getAllSubSet(int[] nums){
        if (nums.length == 0){
            return new ArrayList<>();
        }
        // 所有的可能子集集合
        List<List<Integer>> result = new ArrayList<>();

        // 某个子集
        List<Integer> subSet = new ArrayList<>();

        // 对nums的第index个元素进行处理
        int index = 0;
        help(nums, index, subSet, result);
        return  result;
    }

    private void help(int[] nums, int index, List<Integer> subSet, List<List<Integer>> result){
        // nums最后一个元素已经处理完毕了
        if (index == nums.length){
            result.add(new ArrayList<>(subSet));
        }
        // 处理nums中的第index个元素
        else {
            // 采取方式1: 第index元素不添加到子集 集合中,递归处理下一个元素
            help(nums, index + 1, subSet, result);

            // 采取方式2: 第index元素添加到子集 集合中
            subSet.add(nums[index]); // A处
            help(nums, index + 1, subSet, result);
            // 回溯，恢复当前状态，消除A处代码的影响
            subSet.remove(subSet.size() -1 );
        }
    }

    public static void main(String[] args) {
        SubSetAlgo subSetAlgo = new SubSetAlgo();

        List<List<Integer>> allSubSet = subSetAlgo.getAllSubSet(new int[]{1, 2, 3});
        System.out.println(allSubSet);
    }
}
