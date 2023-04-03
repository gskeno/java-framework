package com.gson.algo.array;

import java.util.*;

/**
 * https://leetcode.cn/problems/avoid-flood-in-the-city/
 */
public class 避免洪水泛滥 {

    public int[] avoidFlood(int[] rains) {
        int[] ans = new int[rains.length];
        // 晴天日期集合
        TreeSet<Integer> sunnyDaySet = new TreeSet<>();
        // 下雨的湖泊编号 --->该湖泊上次下雨的日期。
        Map<Integer,Integer> pool2PreRainDay = new HashMap<>();
        Integer validPoolNo = null;
        for (int i = 0; i < rains.length; i++) {
            // 晴天
            if (rains[i] == 0){
                sunnyDaySet.add(i);
            }
            // 雨天
            else {
                ans[i] = -1;
                int poolNo = rains[i];
                if (validPoolNo == null){
                    validPoolNo = poolNo;
                }
                Integer preRainDay = pool2PreRainDay.get(poolNo);
                // 该湖泊第一次下雨
                if (preRainDay == null){
                    pool2PreRainDay.put(poolNo, i);
                }else {
                    Integer higher = sunnyDaySet.higher(preRainDay);
                    if (higher != null){
                        // 编号higher晴天应该抽干的湖泊编号为poolNo
                        ans[higher] = poolNo;
                        sunnyDaySet.remove(higher);
                    }else {
                        return new int[]{};
                    }
                    pool2PreRainDay.put(poolNo, i);
                }
            }
        }
        // 剩下的晴天都可以抽干任意一个湖泊。我们找到一个有效湖泊编号一直抽即可
        Iterator<Integer> it = sunnyDaySet.iterator();
        while (it.hasNext()){
            Integer sunday = it.next();
            ans[sunday] = validPoolNo;
        }
        return ans;
    }

    public static void main(String[] args) {
        避免洪水泛滥 solution = new 避免洪水泛滥();
        System.out.println(Arrays.toString(solution.avoidFlood(new int[]{1,2,0,0,2,1})));
        System.out.println(Arrays.toString(solution.avoidFlood(new int[]{1,2,3,4})));
        System.out.println(Arrays.toString(solution.avoidFlood(new int[]{1,2,0,1,2})));
        System.out.println(Arrays.toString(solution.avoidFlood(new int[]{1,0,2,0,3,0,2,0,0,0,1,2,3})));
    }
}
