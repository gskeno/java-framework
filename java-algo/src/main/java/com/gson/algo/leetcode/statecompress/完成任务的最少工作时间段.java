package com.gson.algo.leetcode.statecompress;

/**
 * https://leetcode.cn/problems/minimum-number-of-work-sessions-to-finish-the-tasks/
 *
 * n == tasks.length
 * 1 <= n <= 14
 * 1 <= tasks[i] <= 10
 * max(tasks[i]) <= sessionTime <= 15
 */
public class 完成任务的最少工作时间段 {

    // f(s) 表示完成任务状态为s时最少工作时间段。
    // 枚举s的子集sub，若sub状态可以在1个工作时间段内完成，则s状态可以在 1 + f(s^sub)个工作时间段内完成，
    // 枚举过程中找到最小的值就是f(s)
    // 而最终答案为f(1<<tasks.length - 1)
    public int minSessions(int[] tasks, int sessionTime) {
        int n = tasks.length;
        int M = 1<<n;
        int[] sum = new int[M];
        // 首先求每个子集和,套用模板
        for (int i = 0; i < n; i++) {
            int base = 1 << i;
            for (int j = 0; j < base; j++) {
                sum[base | j] = tasks[i] + sum[j];
            }
        }
        int[] f = new int[M];
        for (int s = 1; s < M; s++) {
            f[s] = n;
            // 遍历s的每一个子集,求出其所需工作时间段，找出最小的工作时间段
            for (int sub = s; sub >0 ; sub = (sub - 1) & s) {
                if (sum[sub] <= sessionTime){
                    // 1 + f[s^sub] 表示sub状态需要1个时间段，剩余状态s^sub需要f[s^sub]个时间段
                    f[s] = Math.min(f[s], 1 + f[s^sub]);
                }
            }
        }
        return f[M-1];

    }
}
