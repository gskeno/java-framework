package com.gson.algo.tree;

import com.gson.algo.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

public class 树的深度 {

    /**
     * 树的层次遍历，重点在于怎么找到每层的最后一个节点。
     * 1) 记录每层的节点数以及下一层的节点数，当前层节点全部遍历完毕后，将下一层节点数设置为当前层节点数，继续迭代
     * @param treeNode
     * @return
     */
    public int getDepth(TreeNode treeNode){
        Queue<TreeNode> queue = new LinkedList<>();
        int depth = 0;
        int nodesCountOfCurLevel = 1;
        int nodesCountOfNextLevel = 0;
        queue.add(treeNode);

        while (!queue.isEmpty()){
            TreeNode node = queue.poll();
            nodesCountOfCurLevel--;
            if (node.left != null){
                queue.add(node.left);
                nodesCountOfNextLevel++;
            }
            if (node.right != null){
                queue.add(node.right);
                nodesCountOfNextLevel++;
            }

            // 当前层节点全部遍历完
            if (nodesCountOfCurLevel == 0 ){
                nodesCountOfCurLevel = nodesCountOfNextLevel;
                nodesCountOfNextLevel = 0;
                depth++;
            }
        }
        return depth;
    }
}
