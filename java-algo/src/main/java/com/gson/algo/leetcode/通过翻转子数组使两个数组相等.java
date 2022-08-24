package com.gson.algo.leetcode;

/**
 * https://leetcode.cn/problems/make-two-arrays-equal-by-reversing-sub-arrays/
 */
public class 通过翻转子数组使两个数组相等 {

    public  boolean canBeEqual(int[] target, int[] arr){
        int n = target.length;
        int begin = 0;
        for (int i = 0; i < n ; i++) {
            int ele = target[i];
            boolean find = false;
            for (int j = begin; j < n ; j++) {
                if (arr[j] == ele){
                    // 翻转arr中begin到j范围内的元素
                    reverse(arr, begin, j);
                    begin++;
                    find = true;
                    break;
                }
            }
            if (!find){
                return false;
            }
        }
        return true;
    }

    public void reverse(int[] arr, int begin, int end){
        while (begin<end){
            swap(arr, begin, end);
            begin++;
            end--;
        }
    }

    public void swap(int[] arr, int i, int j){
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }


    public static void main(String[] args) {
        通过翻转子数组使两个数组相等 solution = new 通过翻转子数组使两个数组相等();
        System.out.println(solution.canBeEqual(new int[]{1,2,3,4}, new int[]{2,4,1,3}));
        System.out.println(solution.canBeEqual(new int[]{7}, new int[]{7}));
        System.out.println(solution.canBeEqual(new int[]{3,7,9}, new int[]{3,7,11}));
    }
}
