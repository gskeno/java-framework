package com.gson.algo.interview;

/**
 * 给定一个数组，是否能够做到只删除一个元素，使数组非严格有序
 * (非严格有序表示数组可以是非严格递增或者非严格递减)
 * <p>
 * 如 2 3 2 2 5, 删除3，数组会非严格递增
 * 如 3 2 3 3 1, 删除2，数组会非严格递减
 * <p>
 * 如果数组本身就是非严格有序的，可以不做任何元素删除，返回true
 * <p>
 * 数组元素个数 0< nums.length < 10000
 */
public class 删除一个元素使数组非严格有序 {

    /**
     * @param nums
     * @return
     */
    public boolean isLooseOrdered(int[] nums) {
        // 数组在3个元素以内，则删除其中一个必然可以使之有序
        if (nums.length <= 3) {
            return true;
        }

        // 判断删除一个元素是否可以非严格递增有序
        // 判断删除一个元素是否可以非严格递减有序
        return isAddOrder(nums) || isMinusOrder(nums);
    }

    public boolean isAddOrder(int[] nums) {
        int n = nums.length;
        int cnt = 0;
        int top = nums[0];
        for (int i = 0; i < n; i++) {
            if (nums[i] >= top) {
                top = nums[i];
                continue;
            }
            // 出现了骤减
            cnt++;
            //  且 nums[i] < nums[i-2], 删除nums[i-1]元素，能容易使数据非严格递增，比删除nums[i]更好
            if (nums[i] >= nums[i - 2]) {
                top = nums[i];
            }

            if (cnt > 1) {
                return false;
            }
        }
        return cnt <= 1;
    }

    /**
     * 是否非严格递减
     * @param nums
     * @return
     */
    public boolean isMinusOrder(int[] nums) {
        int n = nums.length;
        int cnt = 0;
        int down = nums[0];
        for (int i = 0; i < n; i++) {
            if (nums[i] <= down) {
                down = nums[i];
                continue;
            }
            // 出现了骤减
            cnt++;
            //  且 nums[i] < nums[i-2], 删除nums[i-1]元素，能容易使数据非严格递增，比删除nums[i]更好
            if (nums[i] <= nums[i - 2]) {
                down = nums[i];
            }

            if (cnt > 1) {
                return false;
            }
        }
        return cnt <= 1;
    }

    public static void main(String[] args) {
        删除一个元素使数组非严格有序 solution = new 删除一个元素使数组非严格有序();
        System.out.println(solution.isAddOrder(new int[]{1,2,3,4}));
        System.out.println(solution.isAddOrder(new int[]{1,3,2,4}));
        System.out.println(solution.isAddOrder(new int[]{1,4,2,3}));
        System.out.println(solution.isAddOrder(new int[]{1,4,3,2}));
        System.out.println("-------非严格递减");
        System.out.println(solution.isMinusOrder(new int[]{4,3,2,1}));
        System.out.println(solution.isMinusOrder(new int[]{4,2,3,1}));
        System.out.println(solution.isMinusOrder(new int[]{4,2,1,3}));
        System.out.println(solution.isMinusOrder(new int[]{4,1,2,3}));
    }


}
