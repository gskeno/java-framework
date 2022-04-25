package com.gson.algo.huawei;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

/**
 * 编写一个程序，将输入字符串中的字符按如下规则排序。
 *
 * 规则 1 ：英文字母从 A 到 Z 排列，不区分大小写。
 *
 * 如，输入： Type 输出： epTy
 *
 * 规则 2 ：同一个英文字母的大小写同时存在时，按照输入顺序排列。
 *
 * 如，输入： BabA 输出： aABb
 *
 * 规则 3 ：非英文字母的其它字符保持原来的位置。
 *
 *
 * 如，输入： By?e 输出： Be?y
 */
public class HJ26字符串排序 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()){
            String line = scanner.nextLine();
            System.out.println(sortStr(line));
        }
    }

    public static String sortStr(String str){
        char[] chars = str.toCharArray();
        // 1. 存储的全是字母
        List<Character> letters = new ArrayList<>();
        for(char c : chars){
            if (Character.isLetter(c)){
                letters.add(c);
            }
        }

        // 2. 构造排序器
        letters.sort(new Comparator<Character>() {
            @Override
            public int compare(Character o1, Character o2) {
                // 转小写后再排序，保证了同字母，不管大小写，按照出现顺序排列
                // 不同字母，按照小写之后的相对顺序排列
                return Character.toLowerCase(o1) - Character.toLowerCase(o2);
            }
        });

        // 3. 重构排序后的字符串
        int index = 0;
        int i = 0;
        char[] sortedChars = new char[str.length()];
        for(char c : str.toCharArray()){
            if (Character.isLetter(c)){
                sortedChars[i++] = letters.get(index++);
            }
            // c不是字母
            else {
                sortedChars[i++] = c;
            }
        }
        return new String(sortedChars);
    }
}
