package com.gson.algo.tree;

import com.gson.algo.TreeNode;

import java.util.ArrayList;

/**
 * https://www.nowcoder.com/practice/b736e784e3e34731af99065031301bca
 * JZ34 二叉树中和为某一值的路径(二)
 *
 *         10
 *     5        12
 *  4     7
 *  expected number = 22
 */
public class AllPathsOnFixedSum {

    public ArrayList<ArrayList<Integer>> FindPath(TreeNode root, int expectNumber) {
        ArrayList<Integer> path = new ArrayList<>();
        ArrayList<ArrayList<Integer>> result = new ArrayList<>();
        dfs(root,result, path, expectNumber);
        return result;
    }

    private void dfs(TreeNode node, ArrayList<ArrayList<Integer>> result, ArrayList<Integer> path, int left){
        if (node == null){
            return;
        }
        left -= node.val;
        path.add(node.val);

        // node为叶子节点
        if (node.left == null && node.right == null){
            if (left == 0){
                result.add(new ArrayList<>(path));
            }
            path.remove(path.size()-1);
            return;
        }
        dfs(node.left,  result, path, left);
        dfs(node.right, result,path, left);
        path.remove(path.size()-1);
    }

    public static void main(String[] args) {
        TreeNode node1 = new TreeNode(10);
        TreeNode node2 = new TreeNode(5);
        TreeNode node3 = new TreeNode(12);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(7);

        node1.left = node2;
        node1.right = node3;
        node2.left = node4;
        node2.right = node5;
        AllPathsOnFixedSum allPathsOnFixedSum = new AllPathsOnFixedSum();
        ArrayList<ArrayList<Integer>> arrayLists = allPathsOnFixedSum.FindPath(node1, 22);
        System.out.println(arrayLists);
    }
}
