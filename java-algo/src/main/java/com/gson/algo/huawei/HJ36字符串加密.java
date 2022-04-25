package com.gson.algo.huawei;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.Scanner;

public class HJ36字符串加密 {

    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String s1 = sc.nextLine().toUpperCase();
            String s2 = sc.nextLine();
            String mix = mix(s1, s2);
            System.out.println(mix);
        }
    }

    /**
     *
     * @param s1 密钥
     * @param s2 字符串
     * @return
     */
    public static String mix(String s1, String s2){
        char[] chars1 = s1.toCharArray();
        char[] chars2 = s2.toCharArray();
        LinkedHashSet<Character> set = new LinkedHashSet();
        for (int i = 0; i < chars1.length; i++) {
            set.add(chars1[i]);
        }
        int k = 0;
        while (set.size() < 26) {
            char a = (char) ('A' + k);
            set.add(a);
            k++;
        }
        // list里都是大写字母
        ArrayList<Character> list = new ArrayList<>(set);
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < chars2.length; i++) {
            if (chars2[i] == ' ') {
                sb.append(chars2[i]);
            }
            // i是大写字母
            else if (chars2[i] < 'a') {
                int n = (int) (chars2[i] - 'A');
                // c是大写字母
                char c = list.get(n);
                sb.append(c);
            }
            // i是小些字母
            else {
                int n = (int) (chars2[i] - 'a');
                // c都是小写字母
                char c = (char)(list.get(n)+'a'-'A');
                sb.append(c);
            }

        }

        return sb.toString();
    }
}
