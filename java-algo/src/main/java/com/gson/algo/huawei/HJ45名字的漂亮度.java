package com.gson.algo.huawei;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * https://www.nowcoder.com/practice/02cb8d3597cf416d9f6ae1b9ddc4fde3
 */
public class HJ45名字的漂亮度 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()){
            int N = scanner.nextInt();
            List<String> strList = new ArrayList<>();
            for (int i = 0; i < N; i++) {
                strList.add(scanner.next());
            }
            for(String str : strList){
                System.out.println(getBeauty(str));
            }
        }
    }

    // 字符串str中出现最多的字符漂亮度为26
    // 次多的为25
    // ... 则综合为整个字符串的最大漂亮度
    public static int getBeauty(String str){
        // 下标分别表示a,b, ....,x,y,z, 值表示下标所示字母出现的次数
        int[] counts = new int[26];
        for(char c : str.toCharArray()){
            counts[c-'a']+=1;
        }
        Arrays.sort(counts);
        int val = 26;
        int sum = 0;
        for (int i = counts.length-1; i >=0 ; i--) {
            if (counts[i] == 0){
                break;
            }
            sum += val * counts[i];
            val--;
        }
        return sum;
    }
}
