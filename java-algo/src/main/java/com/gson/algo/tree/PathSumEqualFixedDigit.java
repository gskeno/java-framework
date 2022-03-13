package com.gson.algo.tree;

import com.gson.algo.TreeNode;
import java.util.HashMap;
import java.util.Map;

/**
 * 向下路径之和等于固定数字的路径条数
 *         5
 *     2       4
 *  1    6  3     7
 *  路径之和等于8的路径有两条，是 5-->2-->1和 2-->6这两条
 */
public class PathSumEqualFixedDigit {
    public int pathSumEqualFixedDigit(TreeNode node, int sum){
        // 路径之和 --> 路径之和出现的次数
        Map<Integer,Integer> path2Count = new HashMap<>();
        path2Count.put(0,1);
        int result = helper(node, path2Count, 0, sum);
        return result;
    }

    /**
     *
     * @param node       当前遍历的节点
     * @param path2Count 节点路径之和----> 出现次数
     * @param path       遍历处理到node节点 之前的路径之和
     * @param sum        路径之和的目标值
     * @return           遍历当前node结束后，路径之和等于sum的路径条数
     */
    private int helper(TreeNode node, Map<Integer,Integer> path2Count, int path, int sum){
        if (node == null){
            return 0;
        }
        path += node.val;

        path2Count.put(path , path2Count.getOrDefault(path,0) + 1);
        // 画龙点睛之笔
        int count = path2Count.getOrDefault(path - sum, 0);
        count += helper(node.left, path2Count, path, sum);
        count += helper(node.right,path2Count, path, sum);

        // 当函数结束时，程序将回到节点的父节点，也就是说，在函数结束之前需要将当前节点
        // 从路径中删除，从根节点到当前节点累加的节点值之和也要从哈希表中删除，这就是函数helper
        // 返回之前要更新哈希表把参数path出现的次数-1的原因
        // 这有点回溯法的感觉了

        // 如果不删除，此例子时，5-->4-->9路径时，路径之和为16，而哈希表中又存在5-->2-->1路径之和为8的key
        // 这会导致程序误认为这条路径也满足需求，但实际上这两条路径不在一条路上，无法从路径中途出发，使路径之和为8
        path2Count.put(path, path2Count.get(path) - 1);
        return count;
    }

    public static void main(String[] args) {
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(5);
        TreeNode node6 = new TreeNode(6);
        TreeNode node7 = new TreeNode(7);

        node5.left = node2;
        node5.right = node4;

        node2.left = node1;
        node2.right = node6;
        node4.left = node3;
        node4.right = node7;

        PathSumEqualFixedDigit pathSumEqualFixedDigit = new PathSumEqualFixedDigit();
        int sum = 8;
        int count = pathSumEqualFixedDigit.pathSumEqualFixedDigit(node5, sum);
        System.out.println(count);

    }
}
