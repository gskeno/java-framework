package com.gson.algo.huawei;


import java.util.Scanner;

public class 华为机考 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()){
            String line = scanner.nextLine();
            String maxStr = getMaxStr1(line);
            System.out.println(maxStr);
        }
    }

    /**
     * 使用指针
     * 一个指针指向子串可能开始的位置(含)
     * 一个指针指向子串可能结束的位置(含)
     * 一个指针指向子串中.符号所在的位置
     * 一个标记flag表示子串是否已经开始
     * 一个指针指向子串中点号的位置
     * @param line
     * @return
     */
    public static String getMaxStr(String line){
        boolean flag = false;
        String max = "";
        char[] chars = line.toCharArray();
        int begin = -1;
        int end = -1;
        int dotPos = -1;
        for (int i = 0; i < chars.length; i++) {
            // 开始一个子串
            if ((chars[i] == '+' || chars[i] == '-' || Character.isDigit(chars[i])) && !flag){
                flag = true;
                begin = end = i;
                continue;
            }
            // 当前字符不是数字，也不是点号，且子串在进行中，则 结束一个子串
            if (!Character.isDigit(chars[i]) && chars[i] != '.' && flag){
                flag = false;
                String may  = getMax(line, begin, end);
                max = may.length() >= max.length() ? may : max;
                if (dotPos !=-1){
                    dotPos=-1;
                }
                if (chars[i] == '+' || chars[i] == '-'){
                    flag = true;
                    begin = end = i;
                }
                continue;
            }

            // 当前是第一次出现点号
            if (chars[i] == '.' && dotPos == - 1){
                dotPos = i;
                end = i;
                continue;
            }
            // 当前是第二次出现点号
            if (chars[i] == '.' && dotPos != -1){
                String may  = getMax(line, begin, end);
                max = may.length() >= max.length() ? may : max;
                begin = dotPos+1;
                end = i;
                dotPos = i;
                continue;
            }

            // 当前是数字，可以追加
            if (Character.isDigit(chars[i])){
                end = i;
            }
        }
        // 最后一个字符的处理
        String may  = getMax(line, begin, end);
        max = may.length() >= max.length() ? may : max;
        return max;
    }

    public static String getMax(String line, int i, int j){
        if ( i == -1 && j == -1){
            return "";
        }
        String sub = line.substring(i, j + 1);
        if (sub.endsWith("+") || sub.endsWith("-") || sub.endsWith(".")){
            return sub.substring(0, sub.length()-1);
        }
        return sub;
    }




    public static String getMaxStr1(String line){
            StringBuilder sb = new StringBuilder();
            char[] elements = line.toCharArray();
            boolean start = false;
            String maxStr = "";
            /**
             * 使用一个StringBuilder sb，
             * a. 当前字符合法，可以加入到数字子串中去时，则sb.append;
             * 合法case 1: sb为空，当前字符为+，-，且不是最后字符，且后面紧跟数字
             *         2: 当前字符为数字
             *         3. sb不为空，当前字符为.,且sb中不含., 且当前字符不是最后一个字符，当前字符后面紧跟数字，前面也紧跟数字
             *
             * 遇到非法case后，就要判断当前的sb是不是最长子串了，
             * 且要重置sb为空，    比如1122+遇到+时，或者-时
             * 或者sb置为当前字符，比如1122+23遇到+时，或者-时
             * 或者 yyyy.(当sb为xxxx.yyyy且当前字符是.时)
             */
        for (int i = 0; i < elements.length; i++) {
            if (sb.length() == 0 && (elements[i] == '+' || elements[i] == '-') && (i+1) <= elements.length-1 && Character.isDigit(elements[i+1])){
                sb.append(elements[i]);
            }else if (Character.isDigit(elements[i])){
                sb.append(elements[i]);
            }else if (sb.length() != 0 && elements[i] == '.' && !sb.toString().contains(".") && (i+1)<= elements.length-1 && Character.isDigit(elements[i+1]) && Character.isDigit(sb.charAt(sb.length()-1))){
                sb.append(elements[i]);
            }else {
                maxStr = sb.length() >= maxStr.length() ? sb.toString() : maxStr;
                if (elements[i] == '.' && sb.toString().contains(".") && (i+1)<= elements.length-1 && Character.isDigit(elements[i+1])){
                    sb = new StringBuilder(sb.toString().split("\\.")[1]);
                    sb.append(elements[i]);
                }else if ((elements[i] == '+' || elements[i] == '-' ) && (i+1)<= elements.length-1 && Character.isDigit(elements[i+1])){
                    sb = new StringBuilder();
                    sb.append(elements[i]);
                } else {
                    sb = new StringBuilder();
                }
            }
        }

        // 最后的sb处理
        if (sb.length() >0){
            maxStr = sb.length() >= maxStr.length() ? sb.toString() : maxStr;
        }
        return maxStr;
    }


}

