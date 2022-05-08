package com.gson.algo.huawei;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * https://www.nowcoder.com/practice/fbc417f314f745b1978fc751a54ac8cb
 * （9-（7/7））*3 解决不了该case
 */
public class HJ67的24点游戏算法 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextInt()) {
            int num1 = scanner.nextInt();
            int num2 = scanner.nextInt();
            int num3 = scanner.nextInt();
            int num4 = scanner.nextInt();
            int[] nums = new int[]{num1, num2, num3, num4};
            List<Integer> numList = new ArrayList<>();
            numList.add(num1);
            numList.add(num2);
            numList.add(num3);
            numList.add(num4);
 //           int[] visit = new int[4];
//
//            boolean flag = false;
//            for (int i = 0; i < 4; i++) {
//                visit[i] = 1;
//                if (dfs(nums, visit, nums[i])) {
//                    // System.out.println("从nums[" + i + "]=" + nums[i] + "开始抡，可以达到24点");
//                    flag = true;
//                    break;
//                }
//                visit[i] = 0;
//            }
//            System.out.println(flag);
            
            // 方案2，每次从n个数中选择2个数，有Cn2种选法，这两个数可以有6种运算，假设这两个数为A,B
            // 6种运算分别为 A+B, A-B, B-A, A*B, A/B(能整除情况下), B/A(能整除情况下),将运算结果替代
            // 选择的两个数。比如 9, 7, 7, 3这四个数
            // 第一次，选择的两个数为 7，7 做除法，结果为1，1替代这两个数，则原始数据变为 9，1，3
            // 第二次，选择的两个数为 9, 1 做减法，结果为8，8替代这两个数，则原始数据变为 8，3
            // 第三次，选择的两个数为 8, 3 做乘法，结果为24, 24替代这两个数，则原始数据变为24。
            // 此时只有一个数字了，不再进行计算，结束，找到一个结果能达到24点
            boolean flag = reach24(numList);
            System.out.println(flag);
        }
    }

    /**
     * 是否达到24点
     * @param nums
     * @return
     */
    public static boolean reach24(List<Integer> nums){
        if (nums.size() == 1){
            return nums.get(0) == 24;
        }
        for (int i = 0; i <= nums.size()-2; i++) {
            for (int j = i+1; j <= nums.size()-1 ; j++) {
                Integer nums1 = nums.get(i);
                Integer nums2 = nums.get(j);

                // 相加
                ArrayList<Integer> numList1 = new ArrayList<>(nums);
                numList1.remove(j);
                numList1.remove(i);
                numList1.add(nums1 + nums2);
                boolean r1 = reach24(numList1);
                if (r1){
                    return true;
                }

                // 相减1
                ArrayList<Integer> numList2 = new ArrayList<>(nums);
                numList2.remove(j);
                numList2.remove(i);
                numList2.add(nums1 - nums2);
                boolean r2 = reach24(numList2);
                if (r2){
                    return true;
                }

                // 相减2
                ArrayList<Integer> numList3 = new ArrayList<>(nums);
                numList3.remove(j);
                numList3.remove(i);
                numList3.add(nums2 - nums1);
                boolean r3 = reach24(numList3);
                if (r3){
                    return true;
                }

                // 相乘
                ArrayList<Integer> numList4 = new ArrayList<>(nums);
                numList4.remove(j);
                numList4.remove(i);
                numList4.add(nums1 * nums2);
                boolean r4 = reach24(numList4);
                if (r4){
                    return true;
                }

                // 相除1
                if (nums2 != 0 && nums1 % nums2 == 0){
                    ArrayList<Integer> numList5 = new ArrayList<>(nums);
                    numList5.remove(j);
                    numList5.remove(i);
                    numList5.add(nums1 / nums2);
                    boolean r5 = reach24(numList5);
                    if (r5){
                        return true;
                    }
                }

                // 相除2
                if (nums1 != 0 && nums2 % nums1 == 0){
                    ArrayList<Integer> numList6 = new ArrayList<>(nums);
                    numList6.remove(j);
                    numList6.remove(i);
                    numList6.add(nums2 / nums1);
                    boolean r6 = reach24(numList6);
                    if (r6){
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public static boolean dfs(int[] nums, int[] visit, int temp) {
        System.out.println("temp=" + temp + " visit=" + Arrays.toString(visit));
        for (int i = 0; i < nums.length; i++) {
            if (visit[i] == 0) {
                visit[i] = 1;
                if (dfs(nums, visit, temp + nums[i]) ||
                        dfs(nums, visit, temp - nums[i]) ||
                        dfs(nums, visit, temp * nums[i]) ||
                        (temp % nums[i] == 0 && dfs(nums, visit, temp / nums[i]))
                ) {
                    return true;
                }
                // 回溯
                visit[i] = 0;
            }
        }
        if (temp == 24) {
            return true;
        }
        return false;
    }
}
