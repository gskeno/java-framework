package com.gson.algo.leetcode;

import java.util.Arrays;

/**
 * https://leetcode.cn/problems/the-latest-time-to-catch-a-bus/
 * 
 * 给你一个下标从 0开始长度为 n的整数数组buses，其中buses[i]表示第 i辆公交车的出发时间。
 * 同时给你一个下标从 0开始长度为 m的整数数组passengers，其中passengers[j]表示第j位乘客的到达时间。
 * 所有公交车出发的时间互不相同，所有乘客到达的时间也互不相同。
 *
 * 给你一个整数capacity，表示每辆公交车最多能容纳的乘客数目。
 *
 * 每位乘客都会搭乘下一辆有座位的公交车。如果你在 y时刻到达，公交在x时刻出发，满足y <= x且公交没有满，
 * 那么你可以搭乘这一辆公交。最早到达的乘客优先上车。
 *
 * 返回你可以搭乘公交车的最晚到达公交站时间。你 不能跟别的乘客同时刻到达。
 *
 * 注意：数组buses 和passengers不一定是有序的。
 *
 * 
 *
 * 示例 1：
 *
 * 输入：buses = [10,20], passengers = [2,17,18,19], capacity = 2
 * 输出：16
 * 解释：
 * 第 1 辆公交车载着第 1 位乘客。
 * 第 2 辆公交车载着你和第 2 位乘客。
 * 注意你不能跟其他乘客同一时间到达，所以你必须在第二位乘客之前到达。
 * 示例 2：
 *
 * 输入：buses = [20,30,10], passengers = [19,13,26,4,25,11,21], capacity = 2
 * 输出：20
 * 解释：
 * 第 1 辆公交车载着第 4 位乘客。
 * 第 2 辆公交车载着第 6 位和第 2 位乘客。
 * 第 3 辆公交车载着第 1 位乘客和你。
 
 */
public class 坐上公交最晚的时间 {

    public int latestTimeCatchTheBus(int[] buses, int[] passengers, int capacity) {
        Arrays.sort(buses);
        Arrays.sort(passengers);
        int busIdx = 0;
        int passengerIdx = 0;
        int personsOnBus = 0;
        // 注意到，跳出while循环时，busIdx== buses.length
        // 会出现2类情况
        // 1. personOnBus < capacity, 表示最后一辆车上乘客未坐满，我们初步认为乘客在最后一辆车到达时赶到即可。
        // 2. personOnBus = capacity, 表示最后一辆车上乘客已坐满(可能还有人未坐上车，也可能所有人都已坐上车),
        //    我们与最后一位乘客一起赶到即可。

        // 再做最后的调整，如果到达时间不是唯一的(别人也在同一时间到达)，则到达时间-1,直至到达时间是唯一的。
        while (busIdx < buses.length){
            personsOnBus = 0;
            while (passengerIdx < passengers.length && passengers[passengerIdx] <= buses[busIdx] && personsOnBus < capacity){
                passengerIdx ++;
                personsOnBus ++;
            }
            busIdx++;
        }
        // 最后一个上车的乘客编号(从0开始)
        passengerIdx--;
        // 如果车上还有位置，则以最后一辆车到达时间为准；如果车上没有位置，以最后一位乘客到达时间为准
        int lastTime = personsOnBus < capacity ? buses[buses.length - 1] : passengers[passengerIdx];
        while (passengerIdx >= 0 && passengers[passengerIdx--] == lastTime) --lastTime; // 往前找没人到达的时刻
        return lastTime;
    }

    public static void main(String[] args) {
        坐上公交最晚的时间 solution = new 坐上公交最晚的时间();
        int ans = solution.latestTimeCatchTheBus(new int[]{10, 20}, new int[]{2, 17, 18, 19}, 2);
        System.out.println(ans);
        ans = solution.latestTimeCatchTheBus(new int[]{20,30,10}, new int[]{19,13,26,4,25,11,21}, 2);
        System.out.println(ans);
    }
}
