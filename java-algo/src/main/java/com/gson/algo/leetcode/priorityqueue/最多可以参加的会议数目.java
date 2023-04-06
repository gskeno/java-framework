package com.gson.algo.leetcode.priorityqueue;

import java.util.*;

/**
 * https://leetcode.cn/problems/maximum-number-of-events-that-can-be-attended/
 */
public class 最多可以参加的会议数目 {


    /**
     * 1. 贪心，对于每个会议，优先选择在会议的第一天开会，这样能最大程度避免影响其他参加其他会议
     *
     * @param events
     * @return
     */
    public int maxEvents(int[][] events) {
//        // 根据会议开始时间由小到大， 会议结束时间由小到大 排序
//        Arrays.sort(events, new Comparator<int[]>() {
//            @Override
//            public int compare(int[] o1, int[] o2) {
//                if (o1[0] != o2[0]){
//                    return o1[0] - o2[0];
//                }
//                return o1[1] - o2[1];
//            }
//        });
//        int occupiedDayNo = 0;
//        int joinedEventCount = 0;
//        for (int i = 0; i < events.length; i++) {
//            int[] startAndEnd = events[i];
//            int start = startAndEnd[0];
//            int end = startAndEnd[1];
//            while (start<= end && start<= occupiedDayNo){
//                start++;
//            }
//            if (start <= end && start > occupiedDayNo){
//                occupiedDayNo++;
//                joinedEventCount++;
//            }
//        }
//        return joinedEventCount;

        PriorityQueue<int[]> queue = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[0] != o2[0]){
                    return o1[0] - o2[0];
                }
                return o1[1] - o2[1];
            }
        });
        for (int i = 0; i < events.length; i++) {
            queue.offer(events[i]);
        }
        int occupiedDayNo = 0;
        int joinedEventCount = 0;
        while (!queue.isEmpty()){
            int[] event = queue.poll();
            List<int[]> list = new ArrayList<>();
            while (!queue.isEmpty() && queue.peek()[0] == event[0]){
                list.add(queue.poll());
            }
            int start = event[0];
            int end = event[1];
            while (start<= end && start<= occupiedDayNo){
                start++;
            }
            if (start <= end && start > occupiedDayNo){
                occupiedDayNo = start;
                joinedEventCount++;
                for (int i = 0; i < list.size(); i++) {
                    if (start + 1 <= list.get(i)[1]){
                        queue.offer(new int[]{start + 1, list.get(i)[1]});
                    }
                }
            }
        }
        return joinedEventCount;
    }

    public static void main(String[] args) {
        最多可以参加的会议数目 solution = new 最多可以参加的会议数目();
        int ans = 0;
//        ans = solution.maxEvents(new int[][]{{1, 2}, {2, 3}, {3, 4}});
//        System.out.println(ans);
//
//        ans = solution.maxEvents(new int[][]{{1, 2}, {2, 3}, {3, 4}, {1,2}});
//        System.out.println(ans);
//
//        ans = solution.maxEvents(new int[][]{{1, 2}, {1,2}, {3, 3}, {1,5},{1,5}});
//        System.out.println(ans);

        ans = solution.maxEvents(new int[][]{{1, 2}, {1,2}, {1, 6}, {1,2},{1,2}});
        System.out.println(ans);
    }
}
