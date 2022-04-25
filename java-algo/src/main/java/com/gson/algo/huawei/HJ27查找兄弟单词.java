package com.gson.algo.huawei;

import java.util.*;

/**
 * https://www.nowcoder.com/practice/03ba8aeeef73400ca7a37a5f3370fe68
 */
public class HJ27查找兄弟单词 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()){
            String line = scanner.nextLine();
            String[] str = line.split(" ");
            int wordCount = Integer.parseInt(str[0]);
            String[] words = new String[wordCount];
            for (int i = 0; i < wordCount; i++) {
                words[i] = str[i+1];
            }
            String inputWord = str[str.length - 2];
            int k = Integer.parseInt(str[str.length -1]);
            brotherWord(words, inputWord, k);
        }
    }

    public static void brotherWord(String[] words, String inputWord, int k){
        List<String> brothers = new ArrayList<>();
        for(String word : words){
            if (word.length() != inputWord.length() || word.equals(inputWord)){
                continue;
            }
            // 是兄弟单词
            if (isBrotherWord(word, inputWord)){
                brothers.add(word);
            }
        }
        brothers.sort(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareTo(o2);
            }
        });
        System.out.println(brothers.size());
        if (brothers.size() >= k){
            System.out.println(brothers.get(k-1));
        }
    }

    public static boolean isBrotherWord(String word1, String word2){
        char[] chars1 = word1.toCharArray();
        char[] chars2 = word2.toCharArray();
        Arrays.sort(chars1);
        Arrays.sort(chars2);
        for (int i = 0; i < chars1.length; i++) {
            if (chars1[i] != chars2[i]){
                return false;
            }
        }
        return true;
    }
}
