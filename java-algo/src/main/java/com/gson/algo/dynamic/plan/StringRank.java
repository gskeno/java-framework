package com.gson.algo.dynamic.plan;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.TreeSet;

/**
 * 全排列
 * https://www.nowcoder.com/practice/fe6b651b66ae47d7acce78ffdd9a96c7?tpId=13&tqId=11180&rp=1&ru=%2Fta%2Fcoding-interviews&qru=%2Fta%2Fcoding-interviews%2Fquestion-ranking&tab=answerKey
 */
public class StringRank {

    private ArrayList<String> res = new ArrayList<>();
    private TreeSet<String> paths = new TreeSet<>();
    private StringBuilder path = new StringBuilder();
    private boolean[] visited;

    public ArrayList<String> Permutation(String str) {
        if (str == null || str.equals("")) {
            return res;
        }
        char[] strs = str.toCharArray();
        Arrays.sort(strs);
        visited = new boolean[strs.length];
        combination(strs, 0);
        res.addAll(paths);
        return res;
    }

    private void combination(char[] strs, int len) {
        if (len == strs.length) {
            paths.add(path.toString());
            return;
        }
        for (int i = 0; i < strs.length; i++) {
            if (!visited[i]) {
                visited[i] = true;
                path.append(strs[i]);
                combination(strs, len + 1);
                //Duang ~ 回溯 - 状态重置
                visited[i] = false;
                //注意，这里是删除最后一个元素,而不是path.deleteCharAt(i);
                //设想一下, str=ab时，当产出ba一条结果时，再回溯时，应该先删除
                //最后的a,而如果用脚标，此时i=0;下一次i=1,delete会报错
                path.deleteCharAt(path.length() - 1);
//                try {
//                    path.deleteCharAt(i);
//                }catch (Exception e){
//                    e.printStackTrace();
//                }

            }
        }
    }

    public static void main(String[] args) {
        StringRank stringRank = new StringRank();
        stringRank.Permutation("ab");
    }
}
