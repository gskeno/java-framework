package com.gson.algo.leetcode.str;

public class 检查单词是否为句中其他单词的前缀 {

    public int isPrefixOfWord(String sentence, String searchWord){
        // 是否开启了一个单词
        boolean wordStart = false;
        // 单词编号
        int wordNo = 0;
        int j = 0;
        for (int i = 0; i < sentence.length(); i++) {
            if (sentence.charAt(i) != ' ' && (i == 0 || sentence.charAt(i-1) == ' ')){
                wordStart = true;
                wordNo++;
            }

            if (wordStart){
                if (sentence.charAt(i) == searchWord.charAt(j)){
                    j++;
                }else {
                    j = 0;
                    wordStart = false;
                }
                if (j == searchWord.length()){
                    return wordNo;
                }
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        检查单词是否为句中其他单词的前缀 solution = new 检查单词是否为句中其他单词的前缀();
        int ans;
        ans = solution.isPrefixOfWord("i love eating burger", "burg");
        System.out.println(ans);

        ans = solution.isPrefixOfWord("this problem is an easy problem", "problem");
        System.out.println(ans);
    }
}
