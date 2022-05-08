package com.gson.algo.huawei;

import java.util.Scanner;

public class HJ71字符串通配符 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()){
            String p = scanner.nextLine();
            String s = scanner.nextLine();
            dfs(s, p);
        }
    }

    /**
     * 动态规划
     * s是字符串，p是模式串
     * dp[i][j] 定义为s的前i个字符与p的前j个字符是否匹配
     *
     * dp[0][0]=true; 空字符串与空模式串匹配
     * dp[i][0]=false when i>0; 非空字符串与空模式不匹配
     * dp[0][j] = true when (p的前j个字符都是*); 空字符串与全*模式串匹配
     */
    public static void dfs(String s, String p){
        boolean[][] dp = new boolean[s.length()+1][p.length()+1];
        dp[0][0] = true;
        for (int k = 1; k <= p.length(); k++) {
            if (p.charAt(k-1) == '*'){
                dp[0][k] = true;
            }
            break;
        }


        // 先选定字符串
        for (int i = 1; i <= s.length() ; i++) {
            // 再依次选择模式串，
            for (int j = 1; j <= p.length(); j++) {
                // 当Pj不是*也不是?时，要与Si完全相同,且dp[i][j] = dp[i-1][j-1]
                if (p.charAt(j-1) != '?' && p.charAt(j-1) != '*'){
                    dp[i][j] = Character.toLowerCase(p.charAt(j-1)) == Character.toLowerCase(s.charAt(i-1))
                            && dp[i-1][j-1];
                }
                // 当Pj是?时，Si必须是数字或者字母,且dp[i-1][j-1]为true
                else if (p.charAt(j-1) == '?'){
                    dp[i][j] = Character.isLetterOrDigit(s.charAt(i-1)) && dp[i-1][j-1];
                }
                // 当Pj是*时，有以下两种选择
                // 1. 不用*号，P的第j个字符*不去做任何匹配，P的前j-1个字符与S的前i个字符能匹配即可, dp[i][j]=dp[i][j-1]
                // 2. 用*号， dp[i][j]=dp[i-1][j]&&Si是数字或者字母, 即S的前i-1个字符与P的前j个字符匹配时，P的第j个字符是*号，且S的第i个字符是数字或者字母
                else if (p.charAt(j-1) == '*'){
                    dp[i][j] = dp[i][j-1] || (dp[i-1][j] && Character.isLetterOrDigit(s.charAt(i-1)));
                }
            }
        }
        System.out.println(dp[s.length()][p.length()]);

    }

    /**
     * 递归思想
     * 如果模式p的首字符是字母，则直接与s的首字符做唯一确定性匹配，如果匹配成功，则递归，入参为s.subString(1), p.subString(1)
     *
     * 如果模式p的首字符是?，则直接与s的首字符达成匹配，进入递归，入参为s.subString(1), p.subString(1)
     *
     * 如果模式p的首字符是*，则可以直接与s前0，1，2...个字符匹配成功，进入递归，入参为s.subString(1), p.subString(1)等
     *
     * 如果s，和p的长度都是0，则匹配成功
     * @param s
     * @param p
     * @return
     */
    public static boolean isMatch(String s, String p){
        if (s.length() == 0 && p.length() == 0){
            return true;
        }
        if (s.length() == 0 && p.length() != 0){
            return false;
        }
        if (s.length() != 0 && p.length() == 0){
            return false;
        }
        char p0 = p.charAt(0);
        // 字母匹配判断
        if (Character.isLetter(p0)){
            char s0 = s.charAt(0);
            if (Character.toLowerCase(p0) == Character.toLowerCase(s0)){
                return isMatch(s.substring(1), p.substring(1));
            }
            // 字母都不匹配，直接结束
            return false;
        }

        // ?匹配判断,匹配任意一个字母或者数字
        else if (p0 == '?'){
            char s0 = s.charAt(0);
            if (Character.isLetterOrDigit(s0)){
                return isMatch(s.substring(1), p.substring(1));
            }
            // ?匹配到了s中的非数字和非字母，无法match
            return false;
        }

        // *匹配判断, 匹配s中0,1,2,... 任意个字母或者数字,直至遇到一个非数字和字母结束
        else if (p0 == '*'){
//            boolean matched;
//            // 注意这里的等于号，表示*匹配到了s的全部字符
//            // 每次遍历表示*去尝试匹配s的前0，1，...,i个字符
//            for (int i = 0; i <= s.length(); i++) {
//                if (i > 0 && !Character.isLetterOrDigit(s.charAt(i-1))){
//                    break;
//                }
//                // 与s中的前i个进行匹配
//                matched = isMatch(s.substring(i), p.substring(1));
//                if (matched){
//                    return true;
//                }
//            }
            // *不匹配s中的字符，或者先匹配s中的一个字符，*仍然保留进入递归


            char s0 = s.charAt(0);
                    // 不匹配s中的字符时,*不再保留
            return isMatch(s, p.substring(1))
                    // 匹配s中的1个字符时, *要保留
                    || (Character.isLetterOrDigit(s0) && isMatch(s.substring(1), p));

        }
        else if (p0 == s.charAt(0)){
            return isMatch(s.substring(1), p.substring(1));
        }
        return false;
    }
}
