package com.gson.algo.leetcode.statecompress;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class 求子集 {

    public Set<Integer> subset(int n){
        Set<Integer> ans = new HashSet<>();
        // (i-1) & n 表示求小于i的n的最大子集
        for (int i = n; i > 0 ; i= (i-1) & n) {
            ans.add(i);
        }
        return ans;
    }

    public static void main(String[] args) {
        求子集 solution = new 求子集();
        Set<Integer> subset = solution.subset(0b10110);
        Iterator<Integer> iterator = subset.iterator();
        while (iterator.hasNext()){
            Integer next = iterator.next();
            System.out.println(Integer.toBinaryString(next));
        }
    }
}
