package com.gson.algo.leetcode.str;

/**
 * https://leetcode.cn/problems/jump-game-vii/
 */
public class 跳跃游戏VII {
    /**
     * boolean[] dp, dp[i]=true表示能跳到i位置
     * @param s
     * @param minJump
     * @param maxJump
     * @return
     */
    public boolean canReach(String s, int minJump, int maxJump) {
        int n = s.length();
        boolean[] dp = new boolean[n];
        dp[0] = true;
        for (int i = minJump; i < n ; i++) {
            if (s.charAt(i) == '1'){
                continue;
            }
            for (int j = Math.max(i - maxJump, 0); j <= i - minJump ; j++) {
                dp[i] |= dp[j];
                if (dp[i]){
                    break;
                }
            }
        }
        return dp[n-1];
    }

    /**
     * dp[i] = 1 表示可以到达，当且仅当 s[i] = 0 且 [i-maxJump, i-minJump] 区间内任意一个k, dp[k] = 1,
     * 这等价于 [i-maxJump, i-minJump] 区间内元素和 > 0
     * @param s
     * @param minJump
     * @param maxJump
     * @return
     */
    public boolean canReach1(String s, int minJump, int maxJump) {
        int n = s.length();
        int[] dp = new int[n];
        dp[0] = 1;
        int[] preSum = new int[n];
        for (int i = 0; i < minJump; i++) {
            preSum[i] = 1;
        }
        for (int i = minJump; i < n ; i++) {
            if (s.charAt(i) == '1'){
                preSum[i] = preSum[i-1];
                continue;
            }
            int regionSum = preSum[i - minJump] -  (i - maxJump <= 0 ? 0 : preSum[i-maxJump - 1]);
            if (regionSum > 0){
                dp[i] = 1;
                preSum[i] = preSum[i-1] + 1;
            }else {
                preSum[i] = preSum[i-1];
            }
        }
        return dp[n-1] == 1;
    }

    public static void main(String[] args) {
        跳跃游戏VII solution = new 跳跃游戏VII();
        boolean ans;
        ans = solution.canReach("011010", 2, 3);
        System.out.println(ans);
        System.out.println(solution.canReach1("011010", 2, 3));

        ans = solution.canReach("01101110", 2, 3);
        System.out.println(ans);
        System.out.println(solution.canReach1("01101110", 2, 3));
    }

}
