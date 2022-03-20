package com.gson.algo.dynamic;

/**
 *  https://www.nowcoder.com/practice/046a55e6cd274cffb88fc32dba695668
 *  把数字翻译成字符串
 *
 *  有一种将字母编码成数字的方式：'a'->1, 'b->2', ... , 'z->26'。
 *
 * 现在给一串数字，返回有多少种可能的译码结果
 *
 * 输入： "12"
 * 返回值： 2
 * 说明： 2种可能的译码结果（”ab” 或”l”）
 *
 */
public class Digit2Str {
    public int digit2Str(String nums){
        // dp[i]存储前i+1个数字组成的字符串的译码结果
        int[] dp = new int[nums.length()];
        if (nums.length() == 0){
            return 0;
        }
        // 首位是0
        if (nums.charAt(0) == '0'){
            return 0;
        }
        // 首位不是0了
        dp[0] = 1;
        if (nums.length() == 1){
            return dp[0];
        }

        // 首位不是0，次位是0
        if (nums.charAt(1) == '0'){
            // 30
            if (nums.charAt(0) > '2'){
                dp[1] = 0;
            }
            //20
            else {
                dp[1] = 1;
            }
        }
        // 首位不是0，次位也不是0
        else {
            if (nums.substring(0,2).compareTo("26") > 0){
                dp[1] = 1;
            }else {
                dp[1] = 2;
            }
        }

        // 数字串长度只有2，则直接返回
        if (nums.length() == 2){
            return dp[1];
        }

        for (int i = 2; i < nums.length() ; i++) {
            dfs(nums, dp, i);
            if (dp[i] == 0){
                return 0;
            }
        }
        return dp[nums.length()-1];
    }

    private void dfs(String nums, int[] dp,  int i){
        char pre = nums.charAt(i - 1);

        if (nums.charAt(i) == '0'){
            if (pre == '0' || pre > '2'){
                dp[i] = 0;
                return;
            }
            dp[i] = dp[i-2];
        }else {
            if (pre == '0' || nums.substring(i-1,i+1).compareTo("26") > 0){
                dp[i] = dp[i-1];
            }else {
                dp[i] = dp[i-2]  + dp[i-1] ;
            }
        }
    }

    public static void main(String[] args) {
        Digit2Str digit2Str = new Digit2Str();
        int i = 0;
//        i = digit2Str.digit2Str("12");
//        System.out.println(i);
//        i  = digit2Str.digit2Str("31717126241541717");
//        System.out.println(i);
        i  = digit2Str.digit2Str("100");
        System.out.println(i);
    }
}
