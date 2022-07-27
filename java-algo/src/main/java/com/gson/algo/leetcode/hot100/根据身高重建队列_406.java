package com.gson.algo.leetcode.hot100;

import java.util.*;

/**
 * https://leetcode.cn/problems/queue-reconstruction-by-height/
 *
 *
 *
 */
public class 根据身高重建队列_406 {

    /**
     * 从小到大遍历身高，身高 低的 排前面，身高 高的 排后面，但是应该排到队列的哪个位置呢？
     *
     * 当排第i个身高时，应排到第从前往后数 第 Ki+1 个不为空的位置
     * @param people
     * @return
     */
    public int[][] reconstructQueue(int[][] people) {
        int len = people.length;
        Arrays.sort(people, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[0] != o2[0]){
                    return o1[0] - o2[0];
                }else {
                    return o2[1] - o1[1];
                }
            }
        });

        int[][] ans = new int[len][];
        for (int i = 0; i < people.length; i++) {
            int[] person = people[i];
            // 将person安排到ans中第 Ki+1 个空位置
            int pos = person[1] + 1;
            int cnt = 0;
            for (int j = 0; j < ans.length; j++) {
                if (ans[j] == null){
                    cnt++;
                }

                if (cnt == pos){
                    ans[j] = person;
                    break;
                }
            }
        }

        return ans;
    }

    public int[][] reconstructQueue1(int[][] people) {
        Arrays.sort(people, new Comparator<int[]>() {
            public int compare(int[] person1, int[] person2) {
                if (person1[0] != person2[0]) {
                    return person2[0] - person1[0];
                } else {
                    return person1[1] - person2[1];
                }
            }
        });
        List<int[]> ans = new ArrayList<int[]>();
        for (int[] person : people) {
            ans.add(person[1], person);
        }
        return ans.toArray(new int[ans.size()][]);
    }

    public static void main(String[] args) {
        根据身高重建队列_406 solution = new 根据身高重建队列_406();
        int[][] people = new int[][]{
                {7,0},
                {4,4},
                {7,1},
                {5,0},
                {6,1},
                {5,2}
        };
        int[][] ans = solution.reconstructQueue(people);
        for(int[] person : ans){
            System.out.println(Arrays.toString(person));
        }

        int[][] ans1 = solution.reconstructQueue1(people);
        for(int[] person : ans1){
            System.out.println(Arrays.toString(person));
        }
    }
}
