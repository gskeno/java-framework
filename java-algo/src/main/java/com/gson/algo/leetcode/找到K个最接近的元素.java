package com.gson.algo.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * https://leetcode.cn/problems/find-k-closest-elements/
 */
public class 找到K个最接近的元素 {

    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        // 第一步，找到第一个>= x的数的位置
        int n = arr.length;
        int idx = getIdx(arr, x);
        List<Integer> ans = new ArrayList<>();
        if (idx == -1){
            while (k >=1){
                ans.add( arr[n - k]);
                k--;
            }
            return ans;
        }
        int i = idx - 1;
        int j = idx;
        for (int l = 0; l < k; l++) {
            int distI = i < 0 ? Integer.MAX_VALUE : Math.abs(arr[i] - x);
            int distJ = j >= n ? Integer.MAX_VALUE : Math.abs(arr[j] - x);
            if (distJ < distI){
                //ans.add(arr[j]);
                j++;
            }else {
                // ans.add(arr[i]);
                i--;
            }
        }

        i++;
        j--;
        for (int l = i; l <= j; l++) {
            ans.add(arr[l]);
        }
        return ans;
    }

    /**
     * 找出第一个>= target的元素位置
     * 找不到返回-1
     */
    public int getIdx(int[] arr, int target){
        int left = 0;
        int right = arr.length - 1;
        if (target > arr[right]){
            return -1;
        }
        while(left < right){
            int mid = (left + right) /2;
            if(arr[mid] < target){
                left = mid+1;
            }else{
                right = mid;
            }
        }
        return right;
    }

    public static void main(String[] args) {
        找到K个最接近的元素 solution = new 找到K个最接近的元素();
        System.out.println(solution.getIdx(new int[]{1,1,1,1}, 0));
        System.out.println(solution.getIdx(new int[]{1,1,1}, 0));
        System.out.println(solution.getIdx(new int[]{1,1,1}, 2));
        System.out.println(solution.getIdx(new int[]{1,1,1,1}, 2));
        System.out.println(solution.getIdx(new int[]{1,2,3,5}, 1));
        System.out.println(solution.getIdx(new int[]{1,2,3,5}, 5));
        System.out.println(solution.getIdx(new int[]{1,2,3,5}, 3));
        System.out.println(solution.getIdx(new int[]{1,2,3,5}, 4));
        System.out.println(solution.findClosestElements(new int[]{1,2,3,4,5}, 4, 3));
        System.out.println(solution.findClosestElements(new int[]{1,2,3,4,5}, 4, -1));
        System.out.println(solution.findClosestElements(new int[]{3,5,8,10}, 2, 15));
    }
}
