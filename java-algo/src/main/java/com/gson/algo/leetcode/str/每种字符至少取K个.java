package com.gson.algo.leetcode.str;

/**
 * https://leetcode.cn/problems/take-k-of-each-character-from-left-and-right/
 */
public class 每种字符至少取K个 {
    /**
     *
     * @param s
     * @param k
     * @return
     */
    public int takeCharacters(String s, int k) {
        int[] abcCount = new int[3];
        for(char c : s.toCharArray()){
            abcCount[c- 'a']++;
        }
        // a,b,c 字符不满足全部出现 >=k次
        if (abcCount[0] < k || abcCount[1] < k || abcCount[2] < k){
            return -1;
        }
        // 分两段，第一段使用全部元素，第二段区间为空；
        // 然后不断压缩第一段区间，分析第二段区间至少需要多大
        int ans = s.length();
        int len = s.length();
        // 前区间 [0, k1]， 后区间[k2, len]
        // 每次把前区间最后一个元素扣掉，扣掉后如果不满足条件，后区间一直补充元素，一直补充到满足条件为止。
        // 这时，算需要的次数为 k1 + 1; 再加上 len - k2
        for (int k1= len - 1, k2 = len; k1 >= 0; k1--){
            abcCount[s.charAt(k1) - 'a']--;
            while (abcCount[0] < k || abcCount[1] < k || abcCount[2] < k){
                k2--;
                abcCount[s.charAt(k2) - 'a']++;
            }
            ans = Math.min(ans, k1 + len - k2);
        }
        return ans;
    }

    public static void main(String[] args) {
        每种字符至少取K个 solution = new 每种字符至少取K个();
        System.out.println(solution.takeCharacters("aabaaaacaabc", 2));
        System.out.println(solution.takeCharacters("a", 1));
    }

}
