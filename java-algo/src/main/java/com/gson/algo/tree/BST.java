package com.gson.algo.tree;

import java.util.Stack;

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
    public void preOrderTravel(){
        preOrderTravel(root);
    }

    private  void preOrderTravel(Node x){
        if (x != null){
            System.out.println(x.key + ":" + x.value);
            preOrderTravel(x.left);
            preOrderTravel(x.right);
        }
    }

    /**
     * 先序遍历(栈)
     */
    public void preTravelStack(){
        preTravelStack(root);
    }

    private void preTravelStack(Node x){
        Stack<Node> stack = new Stack<>();
        Node node = x;
        while (node != null || stack.size() > 0){
            if (node != null){
                System.out.println(node.key + ":" + node.value);
                stack.push(node);
                node = node.left;
            }else {
                //场景1：此时栈顶节点是叶子节点, node为叶子节点的左孩子
                //      则此时node == null, node=stack.pop()后,node为叶子节点
                //      node = node.right后，node为叶子节点的右孩子
                //      node仍然为null，下一次循环后，依然进入else分支
                //      ....node变为叶子节点的右兄弟节点
                //      再循环进入if分支
                node = stack.pop();
                node = node.right;
            }
        }
    }



    /**
     * 中序遍历(递归)
     */
    public void midOrderTravel(){
        midOrderTravel(root);
    }

    private void midOrderTravel(Node x){
        if (x != null){
            midOrderTravel(x.left);
            System.out.println(x.key + ":" + x.value);
            midOrderTravel(x.right);
        }
    }

    /**
     * 中序遍历(栈)
     */
    public void midTravelStack(){
        midTravelStack(root);
    }

    private void midTravelStack(Node x){
        Stack<Node> stack = new Stack<>();
        Node node = x;
        //注意要点
        //1.弹出栈时sout
        while (node != null || stack.size() > 0){
            if (node != null){
                stack.push(node);
                node = node.left;
            }else {
                //场景1：此时栈顶节点是叶子节点, node为叶子节点的左孩子
                //      则此时node == null, node=stack.pop()后,node为叶子节点
                //      node = node.right后，node为叶子节点的右孩子
                //      node仍然为null，下一次循环后，依然进入else分支
                //      ....node变为叶子节点的右兄弟节点
                //      再循环进入if分支
                node = stack.pop();
                System.out.println(node.key + ":" + node.value);
                node = node.right;
            }
        }

    }

    public void postOrderTravel(){
        postOrderTravel(root);
    }

    private void postOrderTravel(Node x){
        if (x != null){
            postOrderTravel(x.left);
            postOrderTravel(x.right);
            System.out.println(x.key + ":" + x.value);
        }
    }

    /**
     * 后序遍历（递归)
     */
    public void  posOrderTravel(){
        posOrderTravel(root);
    }
    private  void posOrderTravel(Node x){
        if (x != null){
            posOrderTravel(x.left);
            posOrderTravel(x.right);
            System.out.println(x.key + ":" + x.value );
        }
    }

    /**
     * 后序遍历(栈)
     * @param x
     */
    private   void posOrderTravelByStack(Node x){
        Stack<Node> stack = new Stack<>();
        Stack<Node> temp = new Stack<>();
        Node node = x;
        while (node != null || stack.size()>0){
            if (node != null){
                stack.push(node);
                temp.push(node);
                node = node.right;
            }else {
                node = stack.pop();
                node = node.left;
            }
        }

        while (temp.size()>0){
            Node popNode = temp.pop();
            System.out.println(popNode.key + ":" + popNode.value);
        }
    }

}
