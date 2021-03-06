package com.gson.algo.tree;

/**
 * 二叉树转双向有序链表
 */
public class SearchTree2DLinkedList {

    public static class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;

        public TreeNode(int val) {
            this.val = val;

        }
    }

    //分治法
    public TreeNode Convert(TreeNode pRootOfTree) {
        if (pRootOfTree != null) {
            TreeNode left = getMax(pRootOfTree.left);
            TreeNode right = getMin(pRootOfTree.right);
            if (left != null) {
                left.right = pRootOfTree;
                pRootOfTree.left = left;
            }
            if (right != null) {
                pRootOfTree.right = right;
                right.left = pRootOfTree;
            }

            while (pRootOfTree.left != null) {
                pRootOfTree = pRootOfTree.left;
            }
        }
        return pRootOfTree;
    }

    private TreeNode getMax(TreeNode pRootOfTree) {
        if (pRootOfTree != null) {
            TreeNode leftMax = getMax(pRootOfTree.left);
            TreeNode rightMin = getMin(pRootOfTree.right);
            if (leftMax != null) {
                pRootOfTree.left = leftMax;
                leftMax.right = pRootOfTree;
            }
            if (rightMin != null) {
                pRootOfTree.right = rightMin;
                rightMin.left = pRootOfTree;
                return rightMin;
            }
            return pRootOfTree;
        }
        return null;
    }

    private TreeNode getMin(TreeNode pRootOfTree) {
        if (pRootOfTree != null) {
            TreeNode leftMax = getMax(pRootOfTree.left);
            TreeNode rightMin = getMin(pRootOfTree.right);
            if (leftMax != null) {
                pRootOfTree.left = leftMax;
                leftMax.right = pRootOfTree;
            }
            if (rightMin != null) {
                pRootOfTree.right = rightMin;
                rightMin.left = pRootOfTree;
            }

            if (leftMax != null) {
                return leftMax;
            }
            return pRootOfTree;
        }
        return null;
    }


    //pre暂存上次中序遍历的节点，与此次建立先后关系
    TreeNode pre=null;
    public TreeNode Convert2(TreeNode pRootOfTree) {
        if (pRootOfTree==null)
            return null;
        Convert2(pRootOfTree.left);
        if (pre!= null){
            pRootOfTree.left=pre;
            pre.right=pRootOfTree;
        }
        pre=pRootOfTree;
        Convert2(pRootOfTree.right);
        return pre;
    }

    public static void main(String[] args) {
        TreeNode node8 = new TreeNode(8);
        TreeNode node6 = new TreeNode(6);
        TreeNode node10 = new TreeNode(10);
        TreeNode node4 = new TreeNode(4);
        TreeNode node7 = new TreeNode(7);
        TreeNode node2 = new TreeNode(2);
        TreeNode node5 = new TreeNode(5);
        TreeNode node9 = new TreeNode(9);
        TreeNode node12 = new TreeNode(12);
        node8.left = node6;
        node8.right = node10;
        node6.left = node4;
        node6.right = node7;
        node4.left = node2;
        node4.right = node5;
        node10.left = node9;
        node10.right = node12;

        SearchTree2DLinkedList searchTree2DLinkedList = new SearchTree2DLinkedList();
//        TreeNode beginNode = searchTree2DLinkedList.Convert(node8);
//
//        while (beginNode != null) {
//            System.out.println(beginNode.val);
//            beginNode = beginNode.right;
//        }

        System.out.println("-------");
        TreeNode node = searchTree2DLinkedList.Convert2(node8);
        while (node!= null){
            System.out.println(node.val);
            node = node.left;
        }
    }
}
