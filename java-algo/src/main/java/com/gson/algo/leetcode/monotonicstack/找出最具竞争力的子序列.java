package com.gson.algo.leetcode.monotonicstack;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

public class 找出最具竞争力的子序列 {


    public int[] mostCompetitive(int[] nums, int k){
        int n = nums.length;
        int[] ans = new int[k];
        int right = n - k;
        int idx = 0;
        // 维护一个单调不减栈
        Deque<Integer> deque = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            while (!deque.isEmpty() && nums[i] < nums[deque.peekFirst()]){
                deque.pollFirst();
            }
            deque.offerFirst(i);
            if (i >= right){
                ans[idx++] = nums[deque.pollLast()];
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        找出最具竞争力的子序列 solution = new 找出最具竞争力的子序列();
        int[] ans;
        ans = solution.mostCompetitive(new int[]{3,5,2,6}, 2);
        System.out.println(Arrays.toString(ans));

        ans = solution.mostCompetitive(new int[]{2, 4, 3, 3, 5, 4, 9, 6}, 4);
        System.out.println(Arrays.toString(ans));
    }
}
