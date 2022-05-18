package com.gson.algo.backtrack;

import java.util.ArrayList;
import java.util.List;

/**
 * beginWord = "hit"
 * endWord = "cog"
 * wordList = ["hot", "dot","dog","lot","log","cog"]
 */
public class 单词转换 {
    private boolean[] visit;
    private boolean found;

    public List<String> wordConvert(String beginWord, String endWord, List<String> dictionary){
        // 没有符合要求的转换序列
        if (!dictionary.contains(endWord)){
            return new ArrayList<>();
        }
        List<String> result = new ArrayList<>();
        visit= new boolean[dictionary.size()];
        convert(beginWord, endWord, dictionary, result);
        if (!result.isEmpty()){
            result.add(0, beginWord);
        }
        return result;
    }

    /**
     * 当前curWord是否可以通过dictionary中的其他词连续转化后到达endWord
     * @param curWord
     * @param endWord
     * @param dictionary
     * @return
     */
    private List<String> convert(String curWord, String endWord, List<String> dictionary, List<String> result){
        if (curWord.equals(endWord)){
            found = true;
            return result;
        }
        for (int i = 0; i < dictionary.size(); i++) {
            String dict = dictionary.get(i);
            // 当前词与字典中没有选择过的词一一比较，如果只相差一个字母，则该词可能是下一个词，递归找下去
            if (differOneLetter(curWord, dict) && !visit[i]){
                // 选择
                result.add(dict);
                visit[i] = true;
                // 递归
                convert(dict, endWord, dictionary, result);

                if (found){
                    return result;
                }
                // 回置状态
                result.remove(dict);
                visit[i] = false;
            }
        }
        return new ArrayList<>();
    }

    private boolean differOneLetter(String word1, String word2){
        int differs = 0;
        for (int i = 0; i < word1.length(); i++) {
            if (word1.charAt(i) != word2.charAt(i)){
                differs++;
            }
            if (differs>1){
                return false;
            }
        }
        if (differs == 1){
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        单词转换 solution = new 单词转换();
        String beginWord = "hit";
        String endWord = "cog";
        List<String> wordList = new ArrayList<>();
        wordList.add("hot");
        wordList.add("dot");
        wordList.add("dog");
        wordList.add("lot");
        wordList.add("log");
        wordList.add("cog");
        List<String> list = solution.wordConvert(beginWord, endWord, wordList);
        System.out.println(list);
    }
}
