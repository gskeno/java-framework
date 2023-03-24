package com.gson.algo.leetcode.count;

import java.util.Arrays;
import java.util.Comparator;

/**
 * https://leetcode.cn/problems/rank-teams-by-votes/
 */
public class 通过投票对团队排名 {

    public String rankTeams(String[] votes) {
        int n = votes[0].length();
        // 第0列存储字母ascii值，第i列存储该字母排位为第i的次数
        int[][] ranks = new int[26][n+1];
        String vote0 = votes[0];
        for(char c : vote0.toCharArray()){
            ranks[c - 'A'][0] = c;
        }
        for(String vote: votes){
            for (int i = 0; i < vote.length(); i++) {
                char c = vote.charAt(i);
                ranks[c - 'A'][i+1]++;
            }
        }
        Arrays.sort(ranks, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                for (int i = 1; i < o1.length; i++) {
                    if (o1[i] > o2[i]){
                        return -1;
                    }else if (o1[i] < o2[i]){
                        return 1;
                    }
                }
                return o1[0] - o2[0];
            }
        });
        StringBuilder sb = new StringBuilder();
        for(int[] rank : ranks){
            if (rank[0] == 0){
                break;
            }
            sb.append((char)rank[0]);
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        通过投票对团队排名 solution = new 通过投票对团队排名();
        String ans ;
        ans = solution.rankTeams(new String[]{"BCA", "CAB", "CBA", "ABC", "ACB", "BAC"});
        System.out.println(ans);
    }
}
