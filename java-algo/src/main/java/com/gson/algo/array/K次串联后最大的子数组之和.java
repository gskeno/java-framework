package com.gson.algo.array;

public class K次串联后最大的子数组之和 {

    public int kConcatenationMaxSum(int[] arr, int k) {
        int n = arr.length;
        int mod = 10_0000_0000 + 7;
        // dp[i]表示以arr[i]为截止元素的子数组的最大和
        long[] dp = new long[2*n];
        dp[0] = arr[0];
        long ret = dp[0];
        long sumInArr = arr[0];
        for (int i = 1; i < n; i++) {
            dp[i] = dp[i-1] > 0 ? dp[i-1] + arr[i % n] : arr[i%n];
            ret = Math.max(dp[i], ret);
            sumInArr = sumInArr + arr[i];
        }
        if (k > 1){
            long maxInSecondStage = 0;
            for (int i = n; i < 2*n; i++) {
                dp[i] = dp[i-1] > 0 ? dp[i-1] + arr[i % n] : arr[i%n];
                maxInSecondStage = Math.max(maxInSecondStage, dp[i]);
                ret = Math.max(maxInSecondStage, ret);
            }

            if (sumInArr > 0){
                ret = Math.max(ret, maxInSecondStage + (k-2) * sumInArr);
            }
        }
        return ret < 0 ? 0 : (int) (ret % mod);
    }

    public static void main(String[] args) {
        K次串联后最大的子数组之和 solution = new K次串联后最大的子数组之和();
        int ans = 0;
//        ans = solution.kConcatenationMaxSum(new int[]{-1, -2}, 7);
//        System.out.println(ans);
//
//        ans = solution.kConcatenationMaxSum(new int[]{1, -2, 1}, 5);
//        System.out.println(ans);
//
//        ans = solution.kConcatenationMaxSum(new int[]{1,2}, 3);
//        System.out.println(ans);

        ans = solution.kConcatenationMaxSum(new int[]{10000,10000,10000,10000,10000,10000,10000,10000,10000,10000}, 100000);
        System.out.println(ans);
        System.out.println((long)100000 * (100000-2) / (10_0000_0000 + 7));
    }

}
