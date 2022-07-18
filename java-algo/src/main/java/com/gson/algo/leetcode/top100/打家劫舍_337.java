package com.gson.algo.leetcode.top100;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.cn/problems/house-robber-iii/
 */
public class 打家劫舍_337 {

    /**
     * 设置f(o)表示 选择o节点进行打家劫舍的最大值
     *    g(o)表示不选择o节点进行打家劫舍的最大值
     *
     * 则有f(o) = o.val + g(o.left) + g(o.right)
     *    g(o) = max{ f(o.left), g(o.left) } + max{ f(o.right), g(o.right)}
     * @param root
     * @return
     */
    private Map<TreeNode, Integer> f = new HashMap<>();
    private Map<TreeNode, Integer> g = new HashMap<>();
    public int rob(TreeNode root) {
        dfs(root);
        return Math.max(f.getOrDefault(root, 0), g.getOrDefault(root, 0));
    }

    public void dfs(TreeNode node){
        if (node == null){
            // 递归结束
            return;
        }

        dfs(node.left);
        dfs(node.right);

        f.put(node, node.val + g.getOrDefault(node.left, 0 ) + g.getOrDefault(node.right, 0));
        g.put(node, Math.max(f.getOrDefault(node.left, 0), g.getOrDefault(node.left, 0)) +
                Math.max(f.getOrDefault(node.right, 0) , g.getOrDefault(node.right, 0))
        );
    }



    public static void main(String[] args) {

    }
}
