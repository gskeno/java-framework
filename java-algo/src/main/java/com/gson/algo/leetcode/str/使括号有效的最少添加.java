package com.gson.algo.leetcode.str;

/**
 * https://leetcode.cn/problems/minimum-add-to-make-parentheses-valid/
 */
public class 使括号有效的最少添加 {

    public int minAddToMakeValid(String s) {
        StringBuffer sb = new StringBuffer();
        int ans = 0;
        for(char c : s.toCharArray()){
            if (c == ')'){
                //在sb的最左侧添加一个(
                if (sb.length() == 0){
                    ans++;
                    continue;
                }
                if (sb.charAt(sb.length() - 1) == '('){
                    sb.deleteCharAt(sb.length() -1 );
                    continue;
                }
            }

            if (c == '('){
                sb.append(c);
                continue;
            }
        }
        while (sb.length() != 0){
            sb.deleteCharAt(sb.length() - 1);
            ans++;
        }
        return ans;
    }

    public static void main(String[] args) {
        使括号有效的最少添加 solution = new 使括号有效的最少添加();
        int ans;
        ans = solution.minAddToMakeValid("())");
        System.out.println(ans);

        ans = solution.minAddToMakeValid("(((");
        System.out.println(ans);
    }

}
