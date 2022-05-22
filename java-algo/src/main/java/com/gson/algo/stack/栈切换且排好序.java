package com.gson.algo.stack;

import java.util.Stack;

/**
 * A栈乱序，元素搬到B栈中去，且排好序，
 * 这里我们设置B栈中的排序为: 栈底部数值最大，栈顶部数值最小
 */
public class 栈切换且排好序 {

    /**
     * 思路:
     * 每次从栈A的栈顶部弹出元素并记为 局部变量temp,
     *      如果比栈B的顶部元素小或者等于，则直接入栈B，
     *      否则，将B中的栈顶部元素一一弹出,并入栈A，直到栈B中顶部元素大于temp，将temp入栈B，并将
     *           刚才从栈B中弹出入A中的元素，一一从栈A顶部再弹出入栈B复原状态。
     *
     *  最后，栈A为空了，则栈B元素满了，且有序
     *
     * @param stackA
     * @param stackB
     */
    public void transferAndSort(Stack<Integer> stackA, Stack<Integer> stackB){
        while (!stackA.isEmpty()){
            Integer temp = stackA.pop();
            if (stackB.isEmpty() || temp <= stackB.peek()){
                stackB.push(temp);
                continue;
            }

            int transferTimes = 0;
            while (!stackB.isEmpty()){
                if (temp > stackB.peek()){
                    stackA.push(stackB.pop());
                    transferTimes++;
                }else {
                    break;
                }
            }
            // temp找好了位置push
            stackB.push(temp);
            // 弹出的元素还原
            for (int i = 1; i <= transferTimes; i++) {
                stackB.push(stackA.pop());
            }
        }
    }

    public static void main(String[] args) {
        栈切换且排好序 solution = new 栈切换且排好序();
        Stack<Integer> stackA = new Stack<>();
        stackA.push(1);
        stackA.push(3);
        stackA.push(2);
        stackA.push(7);
        stackA.push(8);
        stackA.push(6);
        stackA.push(5);
        stackA.push(10);
        stackA.push(9);
        stackA.push(4);

        Stack<Integer> stackB = new Stack<>();
        solution.transferAndSort(stackA, stackB);
        while (!stackB.isEmpty()){
            System.out.println(stackB.pop());
        }
    }
}
