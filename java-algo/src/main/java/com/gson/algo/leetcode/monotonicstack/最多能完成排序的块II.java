package com.gson.algo.leetcode.monotonicstack;

import java.util.Stack;

/**
 * https://leetcode.cn/problems/max-chunks-to-make-sorted-ii/
 */
public class 最多能完成排序的块II {

    /**
     * [2, 0, 1, 3] 最多只能分成两个块 [2, 0, 1]。
     *
     * 前一个块的最大值 <= 第二个块的最小值，自然
     * 前一个块的最大值 <= 第二个块的最大值
     * @param arr
     * @return
     */
    public int maxChunksToSorted(int[] arr) {
        // 维护一个单调不降栈, 栈中记录的是每一个块的最大值，
        // 一个合法的块 对应栈中一个元素
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < arr.length; i++) {
            // 新的元素 >= 前一个块的最大元素，则目前新元素可以独立作为一个块
            if (stack.isEmpty() || arr[i] >= stack.peek()){
                stack.push(arr[i]);
            }
            // 新的元素无法单独作为一个独立的块
            // [2, 0, 1, 7, 8, 5]，遍历到5前，已经有三个块，三个块的最大值
            // 分别是 2, 7, 8。遍历到5时，5会把7,8 两个块融合掉，形成一个块, [5,7,8]，
            // 该块最大值为8
            else {
                Integer chunkMax = stack.pop();
                // 融合之前合法的多个块，使arr[i]加入
                while (!stack.isEmpty() && arr[i] < stack.peek()){
                    stack.pop();
                }
                stack.push(chunkMax);
            }
        }
        return stack.size();
    }

    public static void main(String[] args) {
        double m = 8000d * 0.005 * 35 + 11000 *0.005 * 20 + 13000 * 0.005 * 23 + 60000 * 0.005 * 20;
        System.out.println(m);
    }
}
