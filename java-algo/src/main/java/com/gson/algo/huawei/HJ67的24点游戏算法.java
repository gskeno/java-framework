package com.gson.algo.huawei;

import java.util.Arrays;
import java.util.Scanner;

public class HJ67的24点游戏算法 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextInt()) {
            int num1 = scanner.nextInt();
            int num2 = scanner.nextInt();
            int num3 = scanner.nextInt();
            int num4 = scanner.nextInt();
            int[] nums = new int[]{num1, num2, num3, num4};
            int[] visit = new int[4];
            boolean flag = false;
            for (int i = 0; i < 4; i++) {
                visit[i] = 1;
                if (dfs(nums, visit, nums[i])) {
                    // System.out.println("从nums[" + i + "]=" + nums[i] + "开始抡，可以达到24点");
                    flag = true;
                    break;
                }
                visit[i] = 0;
            }
            System.out.println(flag);
        }
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
