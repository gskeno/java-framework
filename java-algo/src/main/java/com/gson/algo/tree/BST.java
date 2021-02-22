package com.gson.algo.tree;

/**
 *
 * 二叉查找树（左孩子小于当前节点，右孩子大于当前节点)
 * <a href="https://www.cnblogs.com/yaobolove/p/6213936.html">二叉树遍历博客</a>
 * <a href="https://algs4.cs.princeton.edu/home/">算法第四版</a>
 * @param <Key>
 * @param <Value>
 */
public class BST<Key extends Comparable<Key>, Value> {
    /**二叉查找树的根结点**/
    private Node root;

    private class Node{
        private Key key;  //键
        private Value value; //值
        private Node left, right; //左右子结点
        private int n; //以该结点为根的子树结点的个数(包含自身)

        public Node(Key key, Value value, int n) {
            this.key = key;
            this.value = value;
            this.n = n;
        }
    }

    public int size(Node node){
        if (node == null){
            return 0;
        }
        return node.n;
    }

    public void put(Key key, Value value){
        root = put(root, key, value);
    }
    /**
     * 如果key存在以x为根结点的子树中，则更新它的值
     * 否则将以key，value为键值对的新结点插入到该子树中
     * @param x
     * @param key
     * @param value 操作后的以x为根结点的子树
     */
    private Node put(Node x,Key key, Value value){
        if (x == null){
            return new Node(key, value, 1);
        }
        int compare = key.compareTo(x.key);
        if (compare < 0){
            x.left = put(x.left, key, value);
        }else if (compare > 0){
            x.right = put(x.right, key, value);
        }else {
            x.value = value;
        }
        x.n = size(x.left) + size(x.right) + 1;

        return x;
    }

    /**
     * 先序遍历(递归)
     */
    public  void preOrderTravel(){
        preOrderTravel(root);
    }

    private   void preOrderTravel(Node x){
        if (x != null){
            System.out.println(x.key + ":" + x.value);
            preOrderTravel(x.left);
            preOrderTravel(x.right);
        }
    }

}
