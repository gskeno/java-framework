package com.gson.algo.leetcode.graph;

/**
 * https://leetcode.cn/problems/chuan-di-xin-xi/
 */
public class 信息传递_LCP07 {

    public int numWays(int n, int[][] relation, int k) {
        return numWays(0, n -1, relation, k);
    }

    // 从start到end的走法
    public int numWays(int start, int end, int[][] relation, int k){
        int ans = 0;

        if(k == 1){
            for(int[] edge : relation){
                if(edge[0] == start && edge[1] == end){
                    ans = 1;
                    return ans;
                }
            }
            return ans;
        }

        for(int[] edge : relation){
            if(edge[0] == start){
                ans += numWays(edge[1], end, relation, k -1);
            }
        }
        return ans;
    }

    public static void main(String[] args) {
        信息传递_LCP07 solution = new 信息传递_LCP07();
        int ans = solution.numWays(5,
                new int[][]{
                        {0, 2},
                        {2, 1},
                        {3, 4},
                        {2, 3},
                        {1, 4},
                        {2, 0},
                        {0, 4}
                },
                3);
        System.out.println(ans);
    }

}
