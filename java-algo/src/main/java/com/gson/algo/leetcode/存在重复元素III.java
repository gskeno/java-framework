package com.gson.algo.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.cn/problems/contains-duplicate-iii/
 *
 * 给你一个整数数组 nums 和两个整数k 和 t 。请你判断是否存在 两个不同下标 i 和 j
 * ，使得abs(nums[i] - nums[j]) <= t ，同时又满足 abs(i - j) <= k 。
 *
 * 如果存在则返回 true，不存在返回 false。
 *
 */
public class 存在重复元素III {

    /**
     * 桶排序，每个桶的容量设置为t+1,连续的数字可以尝试放置进一个桶里面，
     * 比如 t=3,
     * 0,1,2,3 放置到桶编号0里，
     * 4,5,6,7 放置到桶编号1里
     * value = (t+1)*a+b。如果value满足左侧表达式，则value应该放置在编号a桶里
     * 注意，value可能为负数，所以桶编号也可能为负数
     *
     * 遍历到元素nums[i]时，根据计算nums[i]如果应该放置在桶编号m里，
     * 去查询m桶里是否已经存在元素，如果存在，则说明已经存在一个元素满足abs(nums[i] - nums[j]) <= t ,则返回true。
     * 如果不存在，则查m桶相邻的m-1桶和m+1桶是否存在元素满足abs(nums[i] - nums[j]) <= t,如果存在，则返回true。
     *
     * 否则, 将nums[i]放置到m桶里。另外，将nums[i-k]从桶里移除。
     *
     * 另外，要注意到溢出的风险。int val1, val2,
     * val1+1可能溢出。
     * val1-val2也可能溢出。
     * @param nums
     * @param k
     * @param t
     * @return
     */
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        Map<Long, Integer> bucketToVals = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int val = nums[i];
            // size必须使用long型，因为t+1可能会溢出int
            long size = (long)t + 1;
            long bucketIdx = getIdx(val, size);
            if (bucketToVals.get(bucketIdx) != null){
                return true;
            }
            if (bucketToVals.get(bucketIdx-1) != null && Math.abs((long)bucketToVals.get(bucketIdx-1) - val) <= t){
                return true;
            }
            if (bucketToVals.get(bucketIdx+1) != null && Math.abs((long)bucketToVals.get(bucketIdx+1)-val) <=t ){
                return true;
            }
            bucketToVals.put(bucketIdx, val);
            if (i - k >= 0){
                bucketToVals.remove(getIdx(nums[i-k], size));
            }
        }
        return false;
    }

    public long getIdx(int val, long size){
        if (val>= 0){
            return val /size;
        }
        return ((long)val + 1)/size - 1;
    }

    public static void main(String[] args) {
        存在重复元素III solution = new 存在重复元素III();
        //System.out.println(solution.containsNearbyAlmostDuplicate(new int[]{1,5,9,1,5,9}, 2,3));
        // System.out.println(solution.containsNearbyAlmostDuplicate(new int[]{2,0,-2,2}, 2,1));
        System.out.println(solution.containsNearbyAlmostDuplicate(new int[]{2147483647,-1,2147483647}
        ,1, 2147483647));
    }


}
