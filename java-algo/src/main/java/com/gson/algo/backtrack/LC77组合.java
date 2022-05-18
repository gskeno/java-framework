package com.gson.algo.backtrack;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定两个整数n和k, 返回范围[1,n]中所有可能的k个数的组合
 * 输入n=4, k=2
 * 则有[
 * [1,2]
 * [1,3]
 * [1,4]
 * [2,3]
 * [2,4]
 * [3,4]
 */
public class LC77组合 {
    public List<List<Integer>> combination(int n, int k ){
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        helper(result, path, 0, n, k);
        return result;
    }

    /**
     * 基于一个原则:每次选择的数都比上次选择的数大
     * @param result
     * @param path
     * @param n
     * @param selectedNum 本次要选择的数要大于selectedNum
     * @param k
     */
    private void helper(List<List<Integer>> result, List<Integer> path, int selectedNum, int n, int k){
        // k个数都已经选完，则结束
        if (path.size() == k){
            result.add(new ArrayList<>(path));
            return;
        }

        // 选一个大于selectedNum且小于等于n的数
        for (int i = selectedNum+1; i <= n; i++) {
            path.add(i);
            helper(result, path, i, n, k);
            path.remove(path.size()-1);
        }
    }

    public static void main(String[] args) {
        LC77组合 solution = new LC77组合();
        List<List<Integer>> list;
        list = solution.combination(4, 2);
        System.out.println(list);

        list = solution.combination(1, 1);
        System.out.println(list);
    }
}
