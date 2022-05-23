package com.gson.algo.search;

/**
 * https://leetcode.cn/problems/xuan-zhuan-shu-zu-de-zui-xiao-shu-zi-lcof/
 */
public class 旋转数组中最小的数 {
    /**
     * 二分法
     * @param numbers
     * @return
     */
    public int minArray(int[] numbers) {
        if (numbers.length == 1){
            return numbers[0];
        }
        int left = 0;
        int right = numbers.length-1;

        // 思考一下，这里<=与<有什么区别
        // 当=时，只会走到right--分支，下一次跳出循环，相比<=少一次循环而已
        while (left <= right){
            int mid = left + (right-left)/2;
            // mid右边的值不比nums[mid]小,最小值在[left,mid]之间
            if (numbers[mid] < numbers[right]){
                right = mid;
            }
            // mid左边的值都是递增的，最小值在mid+1到right区间
            else if (numbers[mid] > numbers[right]){
                left = mid+1;
            }else {
                right--;
            }
        }
        return numbers[left];
    }

    public static void main(String[] args) {
        旋转数组中最小的数 solution = new 旋转数组中最小的数();
        System.out.println(solution.minArray(new int[]{2, 3, 2, 2, 2}));
        System.out.println(solution.minArray(new int[]{2, 3, 2}));
        System.out.println(solution.minArray(new int[]{2, 3, 2,2}));
        System.out.println(solution.minArray(new int[]{2, 3,3,3, 2,2}));
        System.out.println(solution.minArray(new int[]{2, 3,3,3,2, 2,2}));
    }
}
