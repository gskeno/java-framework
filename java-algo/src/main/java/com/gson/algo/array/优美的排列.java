package com.gson.algo.array;

/**
 *https://leetcode.cn/problems/beautiful-arrangement/
 */
public class 优美的排列 {

    public int countArrangement(int n) {
        int M = 1<<n;
        int[] f = new int[M];
        // 一个不选择，则状态为0，方案数为1
        f[0] = 1;
        for (int mask = 1; mask < M ; mask++) {
            // mask状态选择了num个数字了(即1的个数)
            int num = Integer.bitCount(mask);
            // 这num个数字中，最后一个数字可以尝试从1-n中选择
            for (int i = 0; i < n; i++) {
                if ( (mask & (1 <<i )) != 0 && ( (i +1) % num == 0 || num %(i+1) == 0 )){
                    // 即0110 可由0100和0010转化而来
                    f[mask] += f[mask^(1<<i)];
                }
            }
        }
        return f[M-1];
    }

    public static void main(String[] args) {
        优美的排列 solution = new 优美的排列();
        int ans = solution.countArrangement(2);
        System.out.println(ans);
    }

}
