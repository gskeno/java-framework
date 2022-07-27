package com.gson.algo.leetcode.hot100;

import java.util.*;

public class 任务调度器_621 {

    /**
     * 每次都选择剩余执行次数最多的那个任务
     *
     * 维护一个二元组(nextValidI, restI)表示第i个任务最早可以执行的时间为nextValidI,其剩余执行的次数为restI
     * @param tasks
     * @param n
     * @return
     */
    public int leastInterval1(char[] tasks, int n){
        Map<Character, Integer> taskToCount = new HashMap<>();
        for(char task : tasks){
            taskToCount.put(task, taskToCount.getOrDefault(task, 0) + 1);
        }
        // 任务类别总数，同类任务算一个
        int m = taskToCount.size();
        List<Integer> nextValidIList = new ArrayList<>();
        List<Integer> restIList = new ArrayList<>();
        Set<Map.Entry<Character, Integer>> entries = taskToCount.entrySet();
        for(Map.Entry<Character, Integer> entry : entries){
            nextValidIList.add(1);
            restIList.add(entry.getValue());
        }
        // 当前时间
        int time = 0;
        for (int i = 0; i < tasks.length; i++) {
            ++time;
            // 从剩余任务中找最早可行执行的任务，因为"冻结"的存在，可能不是所有的任务当前时间都可以执行
            // 也有可能所有任务当前时间都不能执行
            int minNextValid = Integer.MAX_VALUE;
            for (int j = 0; j < m ; j++) {
                if (restIList.get(j) != 0 ){
                    minNextValid = Math.min(minNextValid, nextValidIList.get(j));
                }
            }
            // 确定下一个任务执行的时间
            time = Math.max(time, minNextValid);

            // 选取哪个任务来执行
            int best = -1;
            for (int j = 0; j < m; j++) {
               if (restIList.get(j) != 0 && nextValidIList.get(j) <= time){
                   // 选择剩余执行次数最多的那个任务
                   if (best == -1 || restIList.get(j) > restIList.get(best)){
                        best = j;
                   }
               }
            }
            // 任务编号已经选定为best,则该任务下一次执行时间为 time + n + 1;
            nextValidIList.set(best, time + n + 1);
            // 则剩余执行次数-1
            restIList.set(best, restIList.get(best) - 1);
        }
        return time;
    }

    public int leastInterval(char[] tasks, int n) {
        Map<Character, Integer> freq = new HashMap<Character, Integer>();
        // 最多的执行次数
        int maxExec = 0;
        for (char ch : tasks) {
            int exec = freq.getOrDefault(ch, 0) + 1;
            freq.put(ch, exec);
            maxExec = Math.max(maxExec, exec);
        }

        // 具有最多执行次数的任务数量
        int maxCount = 0;
        Set<Map.Entry<Character, Integer>> entrySet = freq.entrySet();
        for (Map.Entry<Character, Integer> entry : entrySet) {
            int value = entry.getValue();
            if (value == maxExec) {
                ++maxCount;
            }
        }

        return Math.max((maxExec - 1) * (n + 1) + maxCount, tasks.length);
    }

    public static void main(String[] args) {
        任务调度器_621 solution = new 任务调度器_621();
//        char[] tasks = {
//                'A','A','A','A','A',
//                'B','B','B','B','B',
//                'C','C','C','C','C',
//                'D','D','D','D',
//                'E','E','E',
//                'F','F','F','F',
//                'G','G','G'
//        };
//        int time = solution.leastInterval(tasks, 4);
//        System.out.println(time);

        char[] tasks = {'A','A','A','B','B','B'};
        int time = solution.leastInterval1(tasks, 2);
        System.out.println(time);
    }
}
