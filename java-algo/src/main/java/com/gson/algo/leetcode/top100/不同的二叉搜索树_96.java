package com.gson.algo.leetcode.top100;

/**
 * https://leetcode.cn/problems/unique-binary-search-trees/
 * 给你一个整数 n ，求恰由 n 个节点组成且节点值从 1 到 n 互不相同的 二叉搜索树 有多少种？
 * 返回满足题意的二叉搜索树的种数。
 * <p>
 * 1 <= n <= 19
 */
public class 不同的二叉搜索树_96 {

    /**
     * 递归
     * 设置G(n)表示 由 n 个节点组成的 二叉搜索树 的种数
     * 设置F(i, n)表示 由i为根节点，n个节点组成的二叉搜索树的种数, 1=< i <= n
     * <p>
     * 有G(0)=1, G(1)=1, G(2)=2
     * 很明显有 G(n) = 求和 F(i,n), i取值范围为 1到n
     * <p>
     * 比如 1，2，3，4，5，6，7
     * 以i=55为根节点，7个节点组成的二叉搜索数的种类为
     * 左子树的4个节点(i-1)组成的种类数 *   右子树的2个节点(n-i)组成的种类数
     * <p>
     * 则有F(i,n) = G(i-1)*G(n-i)
     * <p>
     * 两者联合有， G(n) = G(i-1)*G(n-i), i从1到n，遍历求和
     * 最终答案为G(n)
     *
     * @param n
     * @return
     */
    public int numTrees(int n) {

        int[] G = new int[n + 1];
        G[0] = G[1] = 1;
        for (int i = 2; i <= n; i++) {
            for (int j = 1; j <= i; j++) {
                G[i] += G[j - 1] * G[i - j];
            }
        }
        return G[n];
    }

    public static void main(String[] args) {
        不同的二叉搜索树_96 solution = new 不同的二叉搜索树_96();
        System.out.println(solution.numTrees(2));
        System.out.println(solution.numTrees(3));
    }
}
