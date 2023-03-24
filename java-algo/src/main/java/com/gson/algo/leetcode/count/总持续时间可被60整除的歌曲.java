package com.gson.algo.leetcode.count;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.cn/problems/pairs-of-songs-with-total-durations-divisible-by-60/
 */
public class 总持续时间可被60整除的歌曲 {

    public int numPairsDivisibleBy60(int[] time) {
        // 时间为key的歌曲数量value
        Map<Integer,Integer> timeAndCount = new HashMap<>();
        for (int i = 0; i < time.length; i++) {
            time[i] = time[i] % 60;
            timeAndCount.put(time[i], timeAndCount.getOrDefault(time[i], 0) + 1);
        }
        Long ans = 0L;
        for (int i = 1; i <= 29 ; i++) {
            ans += (long)timeAndCount.getOrDefault(i, 0) * (long)timeAndCount.getOrDefault(60 - i, 0);
        }
        ans += timeAndCount.getOrDefault(0, 0) * (timeAndCount.getOrDefault(0, 0) - 1)/2;
        ans += timeAndCount.getOrDefault(30, 0) * (timeAndCount.getOrDefault(30, 0) - 1)/2;
        return ans.intValue();
    }

    public static void main(String[] args) {
        总持续时间可被60整除的歌曲 solution = new 总持续时间可被60整除的歌曲();
        int ans = solution.numPairsDivisibleBy60(new int[]{15,63,451,213,37,209,343,319});
        System.out.println(ans);

        ans = solution.numPairsDivisibleBy60(new int[]{60, 60, 60});
        System.out.println(ans);
    }
}
