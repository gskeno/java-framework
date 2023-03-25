package com.gson.algo.leetcode.math;

/**
 * https://leetcode.cn/problems/stone-game-ii/
 */
public class 石子游戏II {

    private int[] s;

    public int stoneGameII(int[] piles) {
        s = piles;
        int n = s.length;
        for (int i = n - 2; i >= 0; --i)
            s[i] += s[i + 1]; // 后缀和
        return dfs(0, 1);
    }

    private int dfs(int i, int m) {
        if (i + m * 2 >= s.length) return s[i];
        int mn = Integer.MAX_VALUE;
        for (int x = 1; x <= m * 2; ++x)
            mn = Math.min(mn, dfs(i + x, Math.max(m, x)));
        return s[i] - mn;
    }

    public static void main(String[] args) {
        石子游戏II solution = new 石子游戏II();
        int ans = solution.stoneGameII(new int[]{1, 2, 3, 4, 5, 100});
        System.out.println(ans);
    }
}
