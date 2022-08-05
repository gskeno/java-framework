package com.gson.algo.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class 四数之和_18 {

    public List<List<Integer>> fourSum(int[] nums, int target) {
        List<List<Integer>> ans = new ArrayList<>();
        if (nums.length < 4) {
            return new ArrayList<>();
        }
        Arrays.sort(nums);
        int n = nums.length;
        for (int firstIdx = 0; firstIdx < n - 3; firstIdx++) {
            if (firstIdx > 0 && nums[firstIdx] == nums[firstIdx - 1]) {
                continue;
            }
            // long 防止溢出
            long remain1 = (long)target - nums[firstIdx];
            for (int secondIdx = firstIdx + 1; secondIdx < n - 2; secondIdx++) {
                if (secondIdx > firstIdx + 1 && nums[secondIdx] == nums[secondIdx - 1]) {
                    continue;
                }
                long remain2 = remain1 - nums[secondIdx];
                int forthIdx = n - 1;

                for (int thirdIdx = secondIdx + 1; thirdIdx < n - 1; thirdIdx++) {
                    if (thirdIdx > secondIdx + 1 && nums[thirdIdx] == nums[thirdIdx - 1]) {
                        continue;
                    }
                    if (nums[thirdIdx] + nums[forthIdx] < remain2){
                        continue;
                    }

                    while (nums[thirdIdx] + nums[forthIdx] > remain2 && forthIdx > thirdIdx){
                        forthIdx--;
                    }

                    if (thirdIdx != forthIdx && nums[thirdIdx] + nums[forthIdx] == remain2){
                        List<Integer> list  = new ArrayList<>();
                        list.add(nums[firstIdx]);
                        list.add(nums[secondIdx]);
                        list.add(nums[thirdIdx]);
                        list.add(nums[forthIdx]);
                        ans.add(list);
                    }
                }
            }
        }

        return ans;
    }

    public static void main(String[] args) {
        四数之和_18 solution = new 四数之和_18();
        // -2, -1, 0, 0, 1, 2
        List<List<Integer>> ans = solution.fourSum(new int[]{1, 0, -1, 0, -2, 2}, 0);
        System.out.println(ans);
        System.out.println(-294967296 - 1000000000);
        System.out.println(-294967296 - 1000000000 - 1000000000);
        System.out.println(
        -294967296 - 1000000000 - 1000000000 -1000000000- 1000000000);
        test1();
    }

    public static void test1(){
        四数之和_18 solution = new 四数之和_18();
        List<List<Integer>> ans = solution.fourSum(new int[]{1000000000, 1000000000, 1000000000,1000000000}, -294967296);
        System.out.println(ans);
    }
}
