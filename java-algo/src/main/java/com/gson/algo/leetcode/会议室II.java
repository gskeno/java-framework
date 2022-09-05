package com.gson.algo.leetcode;

import java.util.*;

/**
 * https://www.cnblogs.com/grandyang/p/5244720.html
 * [LeetCode] 253. Meeting Rooms II 会议室之二
 *
 *
 * Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] (si < ei), find the minimum number of conference rooms required.
 *
 * Example 1:
 *
 * Input:
 * [[0, 30],[5, 10],[15, 20]]
 *
 * Output: 2
 * Example 2:
 *
 * Input: [[7,10],[2,4]]
 * Output: 1
 */
public class 会议室II {
     public int minMeetingRooms(int[][] intervals){
         // 红黑树结构的Map
         TreeMap<Integer,Integer> treeMap = new TreeMap<>();
         // key是会议开始时间和结束时间，val是这一时间对应的会议数量。
         for (int i = 0; i < intervals.length; i++) {
             int[] interval = intervals[i];
             // 某一个会议开始，则val++
             Integer val1 = treeMap.getOrDefault(interval[0], 0);
             treeMap.put(interval[0], val1 + 1);
             // 某一会议结束，则val--
             Integer val2 = treeMap.getOrDefault(interval[1], 1);
             treeMap.put(interval[1], val2 - 1);
         }
         int ans = 0;
         // 正在使用的房间
         int usingRooms = 0;
         // 遍历红黑树map，时间从小到大
         // 遇到起始时间，要使用的房间数要增加；遇到结束时间，要使用的房间数要减少。
         for(Map.Entry<Integer, Integer> entry : treeMap.entrySet()){
             usingRooms += entry.getValue();
             ans = Math.max(ans, usingRooms);
         }
         return ans;
     }

    /**
     * 最小堆
     * @param intervals
     * @return
     */
    public int minMeetingRooms1(int[][] intervals){
        // 按照开始时间排序
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });
        // 最小堆, 记录的都是会议结束时间。
        Queue<Integer> queue = new PriorityQueue<>();
        // 每次循环后，最小的会议结束时间 会在最小堆顶。
        // 下次循环中，如果会议的开始时间 >= 最小的会议B 结束时间，则该会议不需要会议室，可沿用B会议室
        for(int[] interval : intervals){
            if (!queue.isEmpty() && queue.peek() <= interval[0]){
                queue.poll();
            }
            queue.offer(interval[1]);
        }
        return queue.size();
    }



    public static void main(String[] args) {
        会议室II solution = new 会议室II();
        int ans = solution.minMeetingRooms1(new int[][]{
                {0, 30},
                {5, 10},
                {15, 20},
        });
        System.out.println(ans);
    }
}
