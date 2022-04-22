package com.gson.algo.huawei;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * https://www.nowcoder.com/practice/e896d0f82f1246a3aa7b232ce38029d4
 */
public class HJ59找出字符串中第一个只出现一次的字符 {
    public static void main(String[] args) {
        // map存储字符->字符出现的次数
        Scanner scanner = new Scanner(System.in);
        Map<Character, Integer> charCount = new HashMap<>();
        while (scanner.hasNext()) {
            String line = scanner.nextLine();
            for (char c : line.toCharArray()) {
                charCount.put(c, charCount.getOrDefault(c, 0) + 1);
            }
            // 再遍历一次，value=1的字符就是第一个只出现一次的字符
            boolean found = false;
            for (char c : line.toCharArray()) {
                if (charCount.get(c) == 1) {
                    System.out.println(c);
                    found = true;
                    break;
                }
            }
            if (!found){
                System.out.println(-1);
            }
        }
    }
}
