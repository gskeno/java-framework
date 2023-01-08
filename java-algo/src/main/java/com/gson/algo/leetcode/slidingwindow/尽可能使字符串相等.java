package com.gson.algo.leetcode.slidingwindow;

/**
 * https://leetcode.cn/problems/get-equal-substrings-within-budget/
 * 给你两个长度相同的字符串，s 和 t。
 *
 * 将 s中的第i个字符变到t中的第 i 个字符需要|s[i] - t[i]|的开销（开销可能为 0），也就是两个字符的 ASCII 码值的差的绝对值。
 *
 * 用于变更字符串的最大预算是maxCost。在转化字符串时，总开销应当小于等于该预算，这也意味着字符串的转化可能是不完全的。
 *
 * 如果你可以将 s 的子字符串转化为它在 t 中对应的子字符串，则返回可以转化的最大长度。
 *
 * 如果 s 中没有子字符串可以转化成 t 中对应的子字符串，则返回 0。
 *示例 1：
 *
 * 输入：s = "abcd", t = "bcdf", maxCost = 3
 * 输出：3
 * 解释：s 中的 "abc" 可以变为 "bcd"。开销为 3，所以最大长度为 3。
 * 示例 2：
 *
 * 输入：s = "abcd", t = "cdef", maxCost = 3
 * 输出：1
 * 解释：s 中的任一字符要想变成 t 中对应的字符，其开销都是 2。因此，最大长度为 1。
 * 示例 3：
 *
 * 输入：s = "abcd", t = "acde", maxCost = 0
 * 输出：1
 * 解释：a -> a, cost = 0，字符串未发生变化，所以最大长度为 1。
 *
 * 1 <= s.length, t.length <= 10^5
 * 0 <= maxCost <= 10^6
 * s 和 t 都只含小写英文字母。
 *
 *
 
 */
public class 尽可能使字符串相等 {

    /**
     * 提示1: 构建一个int类型数组,元素值就是s[i] - t[i]的绝对值。
     * 提示2: 动态维护一个窗口，窗口内元素之和要 <= maxCost，最大的窗口就是答案。
     * 提示3: 窗口内元素和可以使用前缀和思想
     * @param s
     * @param t
     * @param maxCost
     * @return
     */
    public int equalSubstring(String s, String t, int maxCost) {
        int n = s.length();
        int[] nums = new int[n];
        int[] preSum = new int[n+1];
        for (int i = 0; i < n; i++) {
            nums[i] = Math.abs(s.charAt(i) - t.charAt(i));
            preSum[i+1] = preSum[i] + nums[i];
        }
        int region = 0;
        int left = 0;
        int right = 0;
        int ans = 0;
        while (right < n){
            region += nums[right];
            while (region > maxCost){
                region -= nums[left];
                left++;
            }
            ans = Math.max(ans, right - left + 1);
            right++;
        }
        return ans;
    }

    public static void main(String[] args) {
        尽可能使字符串相等 solution = new 尽可能使字符串相等();
        int ans = 0;
        ans = solution.equalSubstring("abcd", "bcdf", 3);
        System.out.println(ans);

        ans = solution.equalSubstring("abcd", "cdef", 3);
        System.out.println(ans);

        ans = solution.equalSubstring("abcd", "acde", 0);
        System.out.println(ans);
    }
}
