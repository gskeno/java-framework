package com.gson.algo.backtrack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * https://leetcode.cn/problems/combination-sum-ii/solution/zu-he-zong-he-ii-by-leetcode-solution/
 */
public class LC40 {

    List<int[]> freq = new ArrayList<int[]>();
    List<List<Integer>> ans = new ArrayList<List<Integer>>();
    List<Integer> sequence = new ArrayList<Integer>();

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);
        for (int num : candidates) {
            int size = freq.size();
            if (freq.isEmpty() || num != freq.get(size - 1)[0]) {
                freq.add(new int[]{num, 1});
            } else {
                ++freq.get(size - 1)[1];
            }
        }
        dfs(0, target);
        return ans;
    }

    public void dfs(int pos, int rest) {
        if (rest == 0) {
            ans.add(new ArrayList<Integer>(sequence));
            return;
        }
        if (pos == freq.size() || rest < freq.get(pos)[0]) {
            return;
        }

        dfs(pos + 1, rest);

        int most = Math.min(rest / freq.get(pos)[0], freq.get(pos)[1]);
        for (int i = 1; i <= most; ++i) {
            sequence.add(freq.get(pos)[0]);
            List<Integer> temp = new ArrayList<>(sequence);
            // dfs函数自身保证了状态不变，这里是说，执行完dfs后,sequence不会变化，但是在执行
            // dfs之前，sequence增加了一个元素，这个影响在后面要消除
            // 这里看到增加了most次，所以后面要有一个for循环移除most次
            // 为什么remove不直接放在dfs递归的下一行呢？
            // 因为这里这个for循环我们是尝试使用freq.get(pos)[0]元素1次,2次,...,most次
            // 如果直接放到dfs下一行，我们无法使用元素2次,...,most次,因为下次for前，add的元素被清除了
            dfs(pos + 1, rest - i * freq.get(pos)[0]);
            if (!sequence.equals(temp)){
                throw new RuntimeException("递归后状态应该不变");
            }
        }
        // 回溯，重置状态，不一定是直接跟在递归函数后面的，如本题
        for (int i = 1; i <= most; ++i) {
            sequence.remove(sequence.size() - 1);
        }
    }


    public static void main(String[] args) {
        LC40 solution = new LC40();
        List<List<Integer>> lists;
        // [[1, 1, 6], [1, 2, 5], [1, 7], [2, 6]]

        lists = solution.combinationSum2(new int[]{10, 1, 2, 7, 6, 1, 5}, 8);
        System.out.println(lists);

        //[[1, 2, 2], [5]]
        lists = solution.combinationSum2(new int[]{2, 5, 2, 1, 2}, 5);
        System.out.println(lists);

    }
}
