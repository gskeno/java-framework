package com.gson.algo.leetcode.monotonicstack;

/**
 * https://leetcode.cn/problems/remove-duplicate-letters/
 * 给你一个字符串 s ，请你去除字符串中重复的字母，使得每个字母只出现一次。需保证 返回结果的字典序最小（要求不能打乱其他字符的相对位置）。
 * 
 * 示例 1：
 *
 * 输入：s = "bcabc"
 * 输出："abc"
 * 示例 2：
 *
 * 输入：s = "cbacdcbc"
 * 输出："acdb"
 * 
 *
 * 提示：
 *
 * 1 <= s.length <= 104
 * s 由小写英文字母组成
 *
 
 */
public class 去除重复字母 {
    /**
     * 提示1: 考虑只移除一个字符，且移除后的字符串字符相对位置不变，且字典序最小。找到第一个满足的字符，将其移出即可。举例
     * a b c d e d c，相邻字母且前者大于后者的字母对是e d，删除e之后，得到的字符串a b c d d c字典序最小。
     *
     * 提示2: 使用单调栈，单调栈并不是需要Stack，而是一种思想
     *
     * 提示3: 按照提示1, 当S[i] > S[i+1]时，要将S[i]移除，但是如果其在后面不再出现，则不能移除。
     *
     * 提示4: 按照提示1, 如果S[i]已经在单调栈中，则不再需要将其放入栈中
     * @param s
     * @return
     */
    public String removeDuplicateLetters(String s) {
        // 记录每个字母是否已经在单调栈中
        boolean[] inStack = new boolean[26];
        // 记录每个字母出现的次数
        int[] nums = new int[26];
        for(char c: s.toCharArray()){
            nums[c - 'a'] ++;
        }
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);

            // 如ch之前已经在栈中，则ch不追加到栈中，否则，一定要追加到栈中。
            // visit[ch - 'a'] = true; sb.append(ch);放到if块的末尾
            // 需要一个数组标记每个字母当前是否已经在栈中，初始值为false。
            if (!inStack[ch - 'a']){
                // 这里考虑一种情况，栈顶字符x如果大于当前字符ch，且栈顶字符在之后还会出现，则可以移除栈顶字符
                // 此操作可以不断重复执行，直到栈为空或者栈顶字符 小于当前字符
                while (sb.length() > 0 && sb.charAt(sb.length()-1) > ch && nums[sb.charAt(sb.length()-1) - 'a'] > 0){
                    // 栈顶元素删除掉,则其不在栈中了
                    inStack[sb.charAt(sb.length()-1) - 'a'] = false;
                    sb.deleteCharAt(sb.length() -1);
                }

                inStack[ch - 'a'] = true;
                sb.append(ch);
            }
            // 当前字母在后续子串中出现的次数-1
            nums[ch - 'a']--;
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        去除重复字母 solution = new 去除重复字母();
        String s = "";
        s = solution.removeDuplicateLetters("bcabc");
        System.out.println(s);

        s = solution.removeDuplicateLetters("cbacdcbc");
        System.out.println(s);
    }
}
