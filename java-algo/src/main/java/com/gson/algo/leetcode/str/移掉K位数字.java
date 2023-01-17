package com.gson.algo.leetcode.str;

/**
 * https://leetcode.cn/problems/remove-k-digits/
 * 
 * 给你一个以字符串表示的非负整数num 和一个整数 k ，移除这个数中的 k 位数字，使得剩下的数字最小。请你以字符串形式返回这个最小的数字。
 *
 * 
 * 示例 1 ：
 *
 * 输入：num = "1432219", k = 3
 * 输出："1219"
 * 解释：移除掉三个数字 4, 3, 和 2 形成一个新的最小的数字 1219 。
 * 示例 2 ：
 *
 * 输入：num = "10200", k = 1
 * 输出："200"
 * 解释：移掉首位的 1 剩下的数字为 200. 注意输出不能有任何前导零。
 * 示例 3 ：
 *
 * 输入：num = "10", k = 2
 * 输出："0"
 * 解释：从原数字移除所有的数字，剩余为空就是 0 。
 * 
 *
 * 提示：
 *
 * 1 <= k <= num.length <= 105
 * num 仅由若干位数字（0 - 9）组成
 * 除了 0 本身之外，num 不含任何前导零
 *
 */
public class 移掉K位数字 {
    /**
     * 提示1: 如果字符串一定要移除一个字符，移除哪个字符可以时移除后的字符串字典序最小。
     *       应该移除第一个S[i]>S[i+1]的字符S[i]
     *       比如 abcdca， 移除第二个c得到abcda字典序最小
     * 提示2: 本题要求保留n-k个字符，使其字典序最小
     * @param num
     * @param k
     * @return
     * "1432219", k = 3  "1219"
     */
    public String removeKdigits(String num, int k) {
        int n = num.length();
        int m = n - k;
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < n; i++) {
            // 提前生成
            if (sb.length() + (n-1 - i + 1) == m){
                sb.append(num.substring(i));
                break;
            }
            char ch = num.charAt(i);

            while (sb.length() > 0 && ch < sb.charAt(sb.length() - 1)
                    && ( (n-1)-(i+1) + 1 + (sb.length() - 1) + 1 >= m)){
                sb.deleteCharAt(sb.length() - 1);
            }
            if (sb.length() < m){
                sb.append(ch);
            }
        }
        int idx = 0;
        while (idx < sb.length() && sb.charAt(idx) == '0'){
            idx++;
        }
        String str = sb.substring(idx);
        return str.length() == 0 ? "0":str;
    }

    public static void main(String[] args) {
        移掉K位数字 solution = new 移掉K位数字();
        String s = solution.removeKdigits("1432219", 3);
        System.out.println(s);

        s = solution.removeKdigits("10200", 1);
        System.out.println(s);

        s = solution.removeKdigits("10", 2);
        System.out.println(s);

        s = solution.removeKdigits("10", 1);
        System.out.println(s);

        s = solution.removeKdigits("9", 1);
        System.out.println(s);

        s = solution.removeKdigits("100", 1);
        System.out.println(s);
    }
}
