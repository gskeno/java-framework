package com.gson.algo.huawei;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * https://www.nowcoder.com/practice/668603dc307e4ef4bb07bcd0615ea677
 */
public class HJ74参数解析 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()){
            String line = scanner.nextLine();
            analysis(line);
        }
    }

    public static void analysis(String s){
        char[] chars = s.toCharArray();
        int begin = 0;
        int end = 0;
        // 遇到冒号开始
        boolean beginColon = false;
        // 遇到冒号结束
        boolean endColon = false;
        List<String> list = new ArrayList<>();
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == '"'){
                if (!beginColon && !endColon){
                    begin=i+1;
                    beginColon = true;
                }else if (beginColon && !endColon){
                    end=i;
                    endColon = true;
                }

                if (beginColon && endColon){
                    beginColon  = endColon = false;
                }
            }else if (chars[i] != ' '){
                end++;
            }else if (chars[i] == ' '){
                if (beginColon){
                    continue;
                }
                list.add(s.substring(begin, end));
                begin = i+1;
                end = i+1;
            }

            if ( i == chars.length-1){
                list.add(s.substring(begin, end));
            }
        }
        System.out.println(list.size());
        for(String line : list){
            System.out.println(line);
        }
    }
}
