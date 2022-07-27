package com.gson.algo.leetcode.hot100;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.cn/problems/find-all-numbers-disappeared-in-an-array/
 */
public class 找到所有数组中消失的数字_448 {

    public List<Integer> findDisappearedNumbers(int[] nums) {
        for(int i = 0; i <nums.length; i++){
            int idx = nums[i];
            while(idx != 0 && nums[idx-1] != 0){
                int tmp = idx - 1;
                idx = nums[tmp];
                nums[tmp] = 0;
            }
        }
        List<Integer> list = new ArrayList<>();
        for(int i= 0; i< nums.length; i++){
            if(nums[i] != 0){
                list.add(i+1);
            }
        }

        return list;
    }

    public static void main(String[] args) {
        找到所有数组中消失的数字_448 solution = new 找到所有数组中消失的数字_448();
        List<Integer> disappearedNumbers = solution.findDisappearedNumbers(new int[]{4, 3, 2, 7, 8, 2, 3, 1});
        System.out.println(disappearedNumbers);
    }
}
