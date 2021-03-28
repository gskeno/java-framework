package com.gson.algo.array;

import java.util.ArrayList;
import java.util.List;

public class SubSet {

    //假设, 如果已知 [1,2,3]的所有子数组，那么 [1,2,3,4]的所有子数组应该是什么呢？
    //应该是 [1,2,3]的所有子数组 + 遍历[1,2,3]的所有子数组(每个数组内增加元素4)
    //根据这个规则写for循环代码
    public List<List<Integer>> subsets(int[] nums) {
        if (nums == null) {
            return null;
        }
        int length = nums.length;
        List<List<Integer>> res = new ArrayList<>();
        //先添加一个空数组
        res.add(new ArrayList<>());

        for (int i = 0; i < length; i++) {
            int size = res.size();
            for (int j = 0; j < size; j++) {
                List<Integer> elementList = new ArrayList<>(res.get(j));
                elementList.add(nums[i]);
                res.add(elementList);
            }
        }
        return res;
    }

    public static void main(String[] args) {
        SubSet subSet = new SubSet();
        List<List<Integer>> subsets = subSet.subsets(new int[]{1, 2, 3});
        System.out.println(subsets);
    }
}
