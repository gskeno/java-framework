package com.gson.algo.tree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * 二叉树遍历<br>
 * <a href="https://www.cnblogs.com/yaobolove/p/6213936.html">二叉树遍历博客</a>
 */
public class BinaryTree<AnyType extends Comparable<? super AnyType>> {
    static class  Node<AnyType>{
        private Node leftNode;
        private Node rightNode;
        private int value;

        public Node(int value){
            this.value = value;
        }
    }

    /**
     * 层级遍历，使用队列
     * 每弹出一个元素，就追加该元素的左右两个孩子到队列中
     * **/

    public static void levelTravel(Node root){
        if (root == null) return;
        LinkedList<Node> queue = new LinkedList<>();

        queue.add(root);
        while (!queue.isEmpty()){
            Node node = queue.poll();
            System.out.print(node.value + "   ");
            if (node.leftNode  != null) queue.add(node.leftNode);
            if (node.rightNode != null) queue.add(node.rightNode);
        }
    }
    /***先序遍历(递归)**/
    public static void preOrderTravel(Node root){
        if (root != null){
            System.out.print(root.value + "   ");
            preOrderTravel(root.leftNode);
            preOrderTravel(root.rightNode);
        }
    }

    /**
     * 先序遍历(非递归，使用栈)
     * @param root
     */
    public static void preOrderTravelByStack(Node root){
        Stack<Node> stack = new Stack<>();
        Node node = root;

        while (node != null || stack.size() >0){
            if (node != null){
                System.out.print(node.value + "   ");
                stack.push(node); //可以想象是第一次执行时，根节点先打印，再入站
                node = node.leftNode; //node指向根的左节点
            }else{
                //如果根的左节点为空，则获取根的右节点，但是根现在在哪里呢？其实在栈顶
                node = stack.pop(); //node指向根
                node = node.rightNode; //node指向根的右节点
            }
        }
    }

    /***中序遍历(递归)**/
    public static void midOrderTravel(Node root){
        if (root != null){
            midOrderTravel(root.leftNode);
            System.out.print(root.value + "   ");
            midOrderTravel(root.rightNode);
        }
    }

    /**
     * 中序遍历(非递归)
     * @param root
     */
    public static void midOrderTravelByStack(Node root){
        Stack<Node> stack = new Stack<>();
        Node node = root;

        while (node != null || stack.size()>0){
            if (node != null ){
                stack.push(node);
                node = node.leftNode;
            }else {
                node = stack.pop();
                System.out.print(node.value + "   ");
                node = node.rightNode;
            }
        }
    }

    /**倒序遍历(递归)***/
    public static void posOrderTravel(Node root){
        if (root != null){
            posOrderTravel(root.leftNode);
            posOrderTravel(root.rightNode);
            System.out.print(root.value + "   ");
        }
    }

    /**
     * 后序遍历(非递归)
     * @param root
     */
    public static void posOrderTravelByStack(Node root){
        Stack<Node> stack = new Stack<>();
        Stack<Node> temp = new Stack<>();
        Node node = root;
        while (node != null || stack.size()>0){
            if (node != null){
                stack.push(node);
                temp.push(node);
                node = node.rightNode;
            }else {
                node = stack.pop();
                node = node.leftNode;
            }
        }

        while (temp.size()>0){
            System.out.print(temp.pop().value + "   ");
        }
    }

    /***获取树的深度**/
    public static int getDepth(Node root){
        if (root == null) return 0;

        int left = getDepth(root.leftNode);
        int right = getDepth(root.rightNode);
        return Math.max(left,right) + 1;
    }

    /**获取树的宽度,层序遍历，遍历一层后，下一层入队列，同时该层元素全部出队列，比较每层元素的数量**/
    public static int getWidth(Node root){
        if (root == null) return 0;
        Queue<Node> queue = new LinkedList<Node>();
        queue.add(root);
        int width = 1;
        while (true){
            int lineSize = queue.size();

            if (lineSize == 0 ) break;
            while (lineSize>0){
                Node node = queue.poll();
                if (node.leftNode != null) queue.add(node.leftNode);
                if (node.rightNode != null)queue.add(node.rightNode);
                lineSize --;
            }

            width = Math.max(width,queue.size());
        }
        return width;
    }

    public static void main(String[] args) {
        Node root = new Node(3);
        root.leftNode = new Node(2);
        root.rightNode = new Node(1);

//                        3
//                   2           1
//              4        7   12        10


        root.leftNode.leftNode = new Node(4);

        root.leftNode.rightNode = new Node(7);
        root.rightNode.leftNode = new Node(12);
        root.rightNode.rightNode = new Node(10);

        //层级遍历
        levelTravel(root);
        System.out.println();
        //先序遍历
        System.out.print("先序遍历(递归)结果如下:");
        preOrderTravel(root);
        System.out.println();

        System.out.print("先序遍历(堆栈)结果如下:");
        preOrderTravelByStack(root);
        System.out.println();

        //中序遍历
        System.out.print("中序遍历(递归)结果如下:");
        midOrderTravel(root);
        System.out.println();

        System.out.print("中序遍历(堆栈)结果如下:");
        midOrderTravelByStack(root);
        System.out.println();

        //倒序遍历
        System.out.print("倒序遍历(递归)结果如下:");
        posOrderTravel(root);
        System.out.println();

        System.out.print("倒序遍历(堆栈)结果如下:");
        posOrderTravelByStack(root);
        System.out.println();

        //获取树的深度(递归)
        System.out.print("获取树的深度");
        System.out.println(getDepth(root));

        //获取树的宽度
        System.out.print("获取树的宽度");
        System.out.println(getWidth(root));

    }
}
