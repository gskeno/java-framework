package com.gson.algo.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * https://leetcode.cn/problems/nim-game/
 * 你和你的朋友，两个人一起玩Nim 游戏：
 *
 * 桌子上有一堆石头。
 * 你们轮流进行自己的回合，你作为先手。
 * 每一回合，轮到的人拿掉1 - 3 块石头。
 * 拿掉最后一块石头的人就是获胜者。
 * 假设你们每一步都是最优解。请编写一个函数，来判断你是否可以在给定石头数量为 n 的情况下赢得游戏。如果可以赢，返回 true；否则，返回 false 。
 *
 * 输入：n = 4
 * 输出：false
 * 解释：以下是可能的结果:
 * 1. 移除1颗石头。你的朋友移走了3块石头，包括最后一块。你的朋友赢了。
 * 2. 移除2个石子。你的朋友移走2块石头，包括最后一块。你的朋友赢了。
 * 3.你移走3颗石子。你的朋友移走了最后一块石头。你的朋友赢了。
 * 在所有结果中，你的朋友是赢家。
 *
 */
public class Nim游戏_292 {

    /**
     * 当前人能赢得情况有两种
     * 1. 当前人从可选择的石头数量中选择任意一种数量能赢就可以赢，即1，2，3这三种情况。
     * 2. 对方有一种情况输掉，当前人就可以赢
     * @param n
     * @return
     */

    public boolean canWinNim(int n) {
        return dfs(n);
    }


    Map<Integer, Boolean> map = new HashMap<>();
    /**
     * 剩余石头数量为n时，当前人能否赢
     * @param n
     * @return
     */
    private boolean dfs(int n){
        if (map.get(n) == null){
            boolean result = false;
            // 当前人可以选择拿掉1，2，3个石头
            for (int i = 1; i <=3 ; i++) {
                // 任意一种拿法自己能赢就赢了
                if (n == i){
                    result = true;
                    break;
                }
                // 存在一种拿法，会使对方输掉，自己也算赢
                if (!dfs(n - i)){
                    result = true;
                    break;
                }
            }
            map.put(n, result);
        }
        return map.get(n);
    }

    public static void main(String[] args) {
        Nim游戏_292 solution = new Nim游戏_292();
        System.out.println(solution.canWinNim(1));
        System.out.println(solution.canWinNim(2));
        System.out.println(solution.canWinNim(3));
        System.out.println(solution.canWinNim(4));
        System.out.println(solution.canWinNim(5));
        System.out.println(solution.canWinNim(6));
        System.out.println(solution.canWinNim(7));
        System.out.println(solution.canWinNim(8));
        System.out.println(solution.canWinNim(9));
        System.out.println(solution.canWinNim(10));
        System.out.println(solution.canWinNim(11));
        System.out.println(solution.canWinNim(12));
    }
}
