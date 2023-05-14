package com.gson.algo.leetcode.slidingwindow;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

/**
 * https://leetcode.cn/problems/sliding-window-maximum/
 */
public class 滑动窗口最大值 {

    public int[] maxSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        Deque<Integer> deque = new LinkedList<>();
        for (int i = 0; i < k; i++) {
            while (!deque.isEmpty() && nums[deque.peekLast()] <= nums[i]) {
                deque.pollLast();
            }
            deque.offerLast(i);
        }
        int[] ans = new int[n - k + 1];
        int t = 0;
        ans[t] = nums[deque.peekFirst()];
        for (int i = k ; i < n; i++) {
            while (!deque.isEmpty() && nums[deque.peekLast()] <= nums[i]){
                deque.pollLast();
            }
            deque.offer(i);
            // 以上保证了队列从首到尾(下标) 映射的 数组元素值 单调递减

            // 此处while循环，会将不在滑动窗口中的下标移除，有点懒删除(延迟删除)的感觉
            while (!deque.isEmpty() && deque.peekFirst() <= i - k){
                deque.pollFirst();
            }
            ans[++t] = nums[deque.peekFirst()];
        }
        return ans;
    }

    public static void main(String[] args) {
        滑动窗口最大值 solution = new 滑动窗口最大值();
        int[] ans = solution.maxSlidingWindow(new int[]{1, 3, -1, -3, 5, 3, 6, 7}, 3);
        System.out.println(Arrays.toString(ans));
    }
}
