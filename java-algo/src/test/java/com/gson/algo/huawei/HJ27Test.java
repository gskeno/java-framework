package com.gson.algo.huawei;

import org.junit.Test;

public class HJ27Test {
    @Test
    public void test1(){
        String[] words = new String[]{"cab" , "ad",  "abcd",  "cba",  "abc", "bca"};
        String inputWord = "abc";
        int k = 1;
        HJ27查找兄弟单词.brotherWord(words, inputWord, k);
    }

    @Test
    public void test2(){
        String[] words = new String[]{"abc" , "bca",  "cab"};
        String inputWord = "abc";
        int k = 1;
        HJ27查找兄弟单词.brotherWord(words, inputWord, k);
    }
}
