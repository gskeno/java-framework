package com.gson.algo.leetcode.str;

import java.util.Arrays;
import java.util.Comparator;

/**
 * https://leetcode.cn/problems/largest-number/
 */
public class 最大数 {

    public String largestNumber(int[] nums) {
        String[] strs = new String[nums.length];
        for (int i = 0; i < strs.length; i++) {
            strs[i] = nums[i] + "";
        }
        
        Arrays.sort(strs, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return (o2+o1).compareTo(o1+o2);
            }
        });
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < strs.length; i++) {
            sb.append(strs[i]);
        }
        // 移除前导0
        int idx = 0;
        while (idx <sb.length() && sb.charAt(idx) == '0'){
            idx++;
        }
        return idx == sb.length() ? "0" : sb.substring(idx);
    }

    public static void main(String[] args) {
        最大数 solution = new 最大数();
        String ans = "";
        ans = solution.largestNumber(new int[]{0});
        System.out.println(ans);
    }
    
}
