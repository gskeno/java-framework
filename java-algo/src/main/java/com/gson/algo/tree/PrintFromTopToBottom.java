package com.gson.algo.tree;

import com.gson.algo.TreeNode;

import java.util.ArrayList;

/**
 * 从上到下打印二叉树
 * https://www.nowcoder.com/practice/7fe2212963db4790b57431d9ed259701?tpId=13&tqId=11175&rp=1&ru=%2Fta%2Fcoding-interviews&qru=%2Fta%2Fcoding-interviews%2Fquestion-ranking&tab=answerKey
 */

public class PrintFromTopToBottom {
    public ArrayList<Integer> PrintFromTopToBottom(TreeNode root) {
        if (root == null){
            return new ArrayList<>();
        }
        ArrayList<TreeNode> treeNodes = new ArrayList<>();
        treeNodes.add(root);
        int i = 0;
        while (i < treeNodes.size()){
            TreeNode treeNode = treeNodes.get(i);
            TreeNode leftChild = treeNode.left;
            if (leftChild != null){
                treeNodes.add(leftChild);
            }
            TreeNode rightChild = treeNode.right;
            if (rightChild != null){
                treeNodes.add(rightChild);
            }
            i++;
        }
        ArrayList<Integer> intList = new ArrayList<>();
        for (int j = 0; j < treeNodes.size(); j++) {
            intList.add(treeNodes.get(j).val);
        }
        return intList;
    }

    /**
     * 使用队列操作
     * @param root
     * @return
     */
    public ArrayList<Integer> PrintFromTopToBottom2(TreeNode root) {
        ArrayList<Integer> list = new ArrayList<>();
        ArrayList<TreeNode> queue = new ArrayList<>();
        if (root == null) {
            return list;
        }
        queue.add(root);
        while (queue.size() != 0) {
            //每次移出队首元素时，队尾要加入该元素的左右孩子(非空),将队首元素保存到列表
            TreeNode temp = queue.remove(0);
            if (temp.left != null){
                queue.add(temp.left);
            }
            if (temp.right != null) {
                queue.add(temp.right);
            }
            list.add(temp.val);
        }
        return list;
    }

    public static void main(String[] args) {
        TreeNode node1 = new TreeNode(5);
        TreeNode node2 = new TreeNode(4);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(2);
        TreeNode node5 = new TreeNode(1);

        node1.left = node2;
        node2.left = node3;
        node3.right = node4;
        node4.right = node5;

        PrintFromTopToBottom printFromTopToBottom = new PrintFromTopToBottom();
        ArrayList<Integer> integers = printFromTopToBottom.PrintFromTopToBottom(node1);
        System.out.println(integers);
    }
}
