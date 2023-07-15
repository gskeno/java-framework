package com.gson.algo.array;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class SubSet {
    private static AtomicInteger ai = new AtomicInteger(0);

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


    /**
     * 方法2
     * @param nums
     * @return
     */
    public static List<List<Integer>> subsets2(int[] nums) {
        //结果集
        List<List<Integer>> result = new ArrayList();
        //临时存放的集合
        List<Integer> temp = new ArrayList();

        dfs(result,temp,nums,0);

        return result;
    }

    public static void dfs(List<List<Integer>> result, List<Integer> temp, int nums[], int j) {
        //添加到结果集中
        result.add(new ArrayList<Integer>(temp));
        // for 选择 in 选择列表:
        //     做选择
        // backtrack(路径, 选择列表)
        // 撤销选择
        for (int i = j; i < nums.length; i++) {
            //添加第i个数进入temp中
            temp.add(nums[i]);
            //将temp添加到结果集中并添加下一个数
            dfs(result, temp, nums, i + 1);
            Integer lastElement = temp.get(temp.size() - 1);
            if (nums[i] == lastElement){
                System.out.println("进入dfs之前和退出dfs之后相等" + nums[i]);
            }
            //把最新添加的一个数删掉继续循环
            temp.remove(lastElement);


        }//循环完返回上一层递归
    }



    public static void main(String[] args) {
        SubSet subSet = new SubSet();
        List<List<Integer>> subsets = subSet.subsets(new int[]{1, 2, 3});
        //System.out.println(subsets);
        List<List<Integer>> lists = SubSet.subsets2(new int[]{0, 1, 2,3});
        System.out.println(lists);
    }



}
