package com.gson.algo.huawei;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

/**
 * https://www.nowcoder.com/practice/f9a4c19050fc477e9e27eb75f3bfd49c
 */
public class Hj41称砝码 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNextInt()) { // 注意 while 处理多个 case
            int n = in.nextInt();//个数
            int[] w = new int[n];
            int[] nums = new int[n];
            for(int i=0;i<n;i++){
                w[i] = in.nextInt();//砝码的重量
            }
            for(int i=0;i<n;i++){
                nums[i] = in.nextInt();//砝码个数
            }
            int weightKinds = getWeightKinds(n, w, nums);
            System.out.println(weightKinds);
        }
    }

    public static int getWeightKinds(int n , int[] w, int[] nums){
        HashSet<Integer> set = new HashSet<>();//存放所有可能的结果，不用担心重复问题
        set.add(0);//初始化为0

        for(int i=0;i<n;i++){//遍历砝码
            ArrayList<Integer> list = new ArrayList<>(set);//取当前所有的结果
            for(int j=1;j<=nums[i];j++){//遍历个数
                for(int k=0;k<list.size();k++){
                    set.add(list.get(k) + w[i] * j);
                }
            }
        }
        return  set.size();
    }
}
