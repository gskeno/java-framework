package com.gson.algo.huawei;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Stack;

/**
 * 一条线上，若干个球，球的质量可正可负，但不为0
 * 正表示向右移动
 * 负表示向左移动
 * 同向或者反向都不会相撞
 * 相向时才可能撞击，两个球质量的绝对值m, n。
 * m和n相等,则两个球相撞抵消
 * 否则，绝对值小的球被撞毁，
 * 问最终留在线上的球
 */
public class 华为机考3 {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        while (in.hasNextLine()){
            String s = in.nextLine();
            List<Integer> list = new ArrayList<>();
            String[] strs = s.split(" ");
            for (int i = 0; i < strs.length; i++) {
                list.add(Integer.valueOf(strs[i]));
            }
            System.out.println(run(list));
        }
    }

    /**
     * 栈
     * @param list
     * @return
     */
    public static String run(List<Integer> list){
        Stack<Integer> stack = new Stack<>();
        // 处理当前小球时，如果当前小球向左👈运动，且栈顶的小球向右👉运动时，才可能相撞，否则，小球直接进队列
        for (int i = 0; i < list.size() ; i++) {
            Integer num = list.get(i);
            if (stack.isEmpty()){
                stack.push(num);
                continue;
            }
            // 小球向右运动，直接进入队列
            if (num > 0){
                stack.push(num);
            }
            // 小球向左运动，且上一个小球也向左运动，或者队列为空
            else if (stack.peek() < 0){
                stack.push(num);
            }
            // 这里只有1种情况，栈顶小球向右运动，且当前小球向左运动
            else {
                // 栈顶小球被击碎
                while (!stack.isEmpty() && stack.peek() > 0 && stack.peek() + num < 0){
                    stack.pop();
                }

                // 跳出循环有4种可能
                // 1.栈空了
                if (stack.isEmpty()){
                    stack.push(num);
                    continue;
                }
                // 2. 栈顶小球也向左运动
                if (stack.peek() < 0){
                    stack.push(num);
                    continue;
                }
                // 3. 栈顶小球与当前小球抵消
                if (stack.peek() + num == 0){
                    stack.pop();
                    continue;
                }
                // 4. 当前小球被击碎, no code
            }
        }
        return stack.toString();
    }

}
