package com.gson.algo.tree;

import com.gson.algo.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * https://www.nowcoder.com/practice/ff05d44dfdb04e1d83bdbdab320efbcb?tpId=13&tqId=11211&rp=1&ru=%2Fta%2Fcoding-interviews&qru=%2Fta%2Fcoding-interviews%2Fquestion-ranking&tab=answerKey
 * 对称的二叉树
 */
public class IsSymmetrical {

    //失败案例，没有理解透彻对称二叉树的特性
    boolean isSymmetrical1(TreeNode pRoot) {
        if (pRoot == null){
            return true;
        }
        List<TreeNode> list1 = new ArrayList<>();
        List<TreeNode> list2 = new ArrayList<>();
        list1.add(pRoot);

        while (true){
            for (int i = 0; i < list1.size(); i++) {
                TreeNode treeNode = list1.get(i);
                if (treeNode.left != null){
                    list2.add(treeNode.left);
                }
                if (treeNode.right != null){
                    list2.add(treeNode.right);
                }
            }
            list1.clear();

            //校验tempToAdd是对称的
            int tempToAddSize = list2.size();
            if (tempToAddSize == 0){
                return true;
            }
            if (tempToAddSize %2 != 0){
                return false;
            }

            for (int i = 0; i < tempToAddSize/2; i++) {
                if (list2.get(i).val != list2.get(tempToAddSize-1-i).val){
                    return false;
                }
            }
            List<TreeNode> temp = list1;
            list1 = list2;
            list2 = temp;
        }

    }

    boolean isSymmetrical(TreeNode pRoot) {
        if (pRoot == null){
            return true;
        }
        return isSame(pRoot.left, pRoot.right);
    }

    boolean isSame(TreeNode left, TreeNode right){
        //两者都为空，则对称
        if (left == null && right == null){
            return true;
        }

        //两者有一个为空,则不对称
        if (left == null || right == null){
            return false;
        }

        //两者的值不相等，则不对称
        if (left.val != right.val){
            return false;
        }

        //left的左孩子 == right的右孩子 且
        //left的右孩子 == right的左孩子，则对称
        return isSame(left.left, right.right) && isSame(left.right, right.left);
    }

    public static void main(String[] args) {
        TreeNode node1 = new TreeNode(8);
        TreeNode node2 = new TreeNode(6);
        TreeNode node3 = new TreeNode(6);
        TreeNode node4 = new TreeNode(5);
        TreeNode node5 = new TreeNode(7);
        TreeNode node6 = new TreeNode(7);
        TreeNode node7 = new TreeNode(5);
        node1.left = node2;
        node1.right = node3;
        node2.left = node4;
        node2.right = node5;
        node3.left = node6;
        node3.right = node7;

        IsSymmetrical isSymmetrical = new IsSymmetrical();
        boolean symmetrical = isSymmetrical.isSymmetrical(node1);
        System.out.println(symmetrical);

        node1 = new TreeNode(5);
        node2 = new TreeNode(5);
        node3 = new TreeNode(5);
        node4 = new TreeNode(5);
        node5 = new TreeNode(5);
        node6 = new TreeNode(5);
        node7 = new TreeNode(5);
        node1.left = node2;
        node1.right = node3;
        node2.left = node4;
        node3.right = node5;
        node4.left = node6;
        node5.right = node7;
        symmetrical = isSymmetrical.isSymmetrical(node1);
        System.out.println(symmetrical);

    }
}
