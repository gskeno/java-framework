package com.gson.algo.tree;

import com.gson.algo.TreeNode;
import com.sun.codemodel.internal.JMod;

/**
 * https://www.nowcoder.com/practice/6e196c44c7004d15b1610b9afca8bd88?tpId=13&tqId=11170&rp=1&ru=%2Fta%2Fcoding-interviews&qru=%2Fta%2Fcoding-interviews%2Fquestion-ranking&tab=answerKey
 * 输入两棵二叉树A，B，判断B是不是A的子结构。（ps：我们约定空树不是任意一个树的子结构）
 */
public class HasSubtree {

    public boolean HasSubtree(TreeNode root1, TreeNode root2) {
        return (root1 != null && root2 != null) &&
                (recur(root1, root2)
                 || HasSubtree(root1.left, root2)
                 || HasSubtree(root1.right, root2));
    }

    public boolean recur(TreeNode node1, TreeNode node2){
        if (node2 == null){
            return true;
        }
        if (node1 == null){
            return false;
        }
        if (node1.val != node2.val){
            return false;
        }
        return recur(node1.left, node2.left) && recur(node1.right, node2.right);
    }

    public static void main(String[] args) {
        HasSubtree hasSubtree = new HasSubtree();
        TreeNode node1 = new TreeNode(3);
        TreeNode node2 = new TreeNode(4);
        TreeNode node3 = new TreeNode(5);
        TreeNode node4 = new TreeNode(1);
        TreeNode node5 = new TreeNode(2);

        node1.left = node2;
        node1.right = node3;

        node2.left = node4;
        node2.right = node5;

        TreeNode nodeA = new TreeNode(4);
        TreeNode nodeB = new TreeNode(2);
        nodeA.right = nodeB;

        boolean b = hasSubtree.HasSubtree(node1, nodeA);
        System.out.println(b);
    }
}
