package com.gson.algo.leetcode;

import java.util.Arrays;

/**
 * https://leetcode.cn/problems/koko-eating-bananas/
 * 珂珂喜欢吃香蕉。这里有 n 堆香蕉，第 i 堆中有piles[i]根香蕉。警卫已经离开了，将在 h 小时后回来。
 *
 * 珂珂可以决定她吃香蕉的速度 k （单位：根/小时）。每个小时，她将会选择一堆香蕉，从中吃掉 k 根。
 * 如果这堆香蕉少于 k 根，她将吃掉这堆的所有香蕉，然后这一小时内不会再吃更多的香蕉。
 *
 * 珂珂喜欢慢慢吃，但仍然想在警卫回来前吃掉所有的香蕉。
 *
 * 返回她可以在 h 小时内吃掉所有香蕉的最小速度 k（k 为整数）。
 *
 * 
 *
 * 示例 1：
 *
 * 输入：piles = [3,6,7,11], h = 8
 * 输出：4
 * 示例 2：
 *
 * 输入：piles = [30,11,23,4,20], h = 5
 * 输出：30
 * 示例 3：
 *
 * 输入：piles = [30,11,23,4,20], h = 6
 * 输出：23
 *
 * 提示：
 *
 * 1 <= piles.length <= 104
 * piles.length <= h <= 109
 * 1 <= piles[i] <= 109
 *
 */
public class 爱吃香蕉的珂珂 {
    public int minEatingSpeed(int[] piles, int h) {
        int sum = Arrays.stream(piles).sum();
        int minSpeed = (sum + h - 1) /h;
        int maxSpeed = Arrays.stream(piles).max().getAsInt();
        int k = maxSpeed;
        while (minSpeed < maxSpeed){
            int speed = (minSpeed + maxSpeed) /2;
            int time = getTime(piles, speed);
            if (time <= h){
                k = speed;
                maxSpeed = speed;
            }else {
                minSpeed = speed + 1;
            }
        }
        return k;
    }

    public int getTime(int[] piles, int speed){
        int time = 0;
        for (int i = 0; i < piles.length; i++) {
            time += (piles[i] + speed - 1)/ speed;
        }
        return time;
    }

    public static void main(String[] args) {
        爱吃香蕉的珂珂 solution = new 爱吃香蕉的珂珂();
        System.out.println(solution.minEatingSpeed(new int[]{3,6,7,11}, 8));
        System.out.println(solution.minEatingSpeed(new int[]{30,11,23,4,20}, 5));
        System.out.println(solution.minEatingSpeed(new int[]{30,11,23,4,20}, 6));
    }
}
