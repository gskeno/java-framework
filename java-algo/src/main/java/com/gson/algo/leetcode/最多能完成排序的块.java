package com.gson.algo.leetcode;

import java.util.Stack;

/**
 * 769. 最多能完成排序的块
 * 给定一个长度为 n 的整数数组 arr ，它表示在 [0, n - 1] 范围内的整数的排列。
 * <p>
 * 我们将 arr 分割成若干 块 (即分区)，并对每个块单独排序。将它们连接起来后，使得连接的结果和按升序排序后的原数组相同。
 * <p>
 * 返回数组能分成的最多块数量。
 * 输入: arr = [4,3,2,1,0]
 * 输出: 1
 * 解释:
 * 将数组分成2块或者更多块，都无法得到所需的结果。
 * 例如，分成 [4, 3], [2, 1, 0] 的结果是 [3, 4, 0, 1, 2]，这不是有序的数组。
 */
public class 最多能完成排序的块 {

    /**
     * 与单调栈无关
     * 如果[0, i]区间的最大值时 i，就形成了一个块
     *
     * @param arr
     * @return
     */
    public int maxChunksToSorted(int[] arr) {
        int ans = 0;
        int max = 0;
        for (int i = 0; i < arr.length; i++) {
            max = Math.max(max, arr[i]);
            if (max == i){
                ans++;
            }
        }
        return ans;
    }


    public static void main(String[] args) {
        最多能完成排序的块 solution = new 最多能完成排序的块();
        int ans;
        ans = solution.maxChunksToSorted(new int[]{1, 0, 2, 3, 4});
        System.out.println(ans);

        ans = solution.maxChunksToSorted(new int[]{4, 3, 2, 1, 0});
        System.out.println(ans);
    }
}
