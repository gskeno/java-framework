package com.gson.algo.huawei;

import java.util.Scanner;

/**
 * https://www.nowcoder.com/practice/6d9d69e3898f45169a441632b325c7b4
 * 动态规划
 */
public class HJ24合唱队 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String total = scanner.nextLine();
            String heightStr = scanner.nextLine().trim();
            String[] heights = heightStr.split(" ");

            System.out.println(getEvictNums(heights));
        }
    }

    /**
     * dp[i]表示第i个人左边出现最多的满足要求的人数
     * f[i] 表示第i个人右边出现最多的满足要求的人数
     *
     * @param heights
     * @return
     */
    public static int getEvictNums(String[] heights) {
        // 186 186 150 200 160 130 197 200
        int[] dp = new int[heights.length];

        for (int i = 0; i < heights.length; i++) {
            for (int j = 0; j < i; j++) {
                // i号人如果比它之前的j号人的更高，则i号人左边出现最多的人数，则是j号人左边出现的最多人数 + 1。
                // 注意到j有很多个，在i前面的都是j，所以要把j的范围遍历完，取得最多的人数再+1
                if (Integer.valueOf(heights[i]) > Integer.valueOf(heights[j]) && dp[i] < dp[j] + 1) {
                    dp[i] = dp[j] + 1;
                }
            }
        }

        // 186 186 150 200 160 130 197 200
        int[] f = new int[heights.length];
        for (int i = heights.length - 1; i >= 0; i--) {
            for (int j = heights.length - 1; j > i; j--) {
                if (Integer.valueOf(heights[i]) > Integer.valueOf(heights[j]) && f[i] < f[j] + 1) {
                    f[i] = f[j] + 1;
                }
            }
        }

        int maxLen = 0;

        for (int i = 0; i < heights.length; i++) {
            // 以i号人作为中间人时，队列需要的人数
            int personsInQueue = dp[i] + f[i] + 1;
            maxLen = Math.max(maxLen, personsInQueue);
        }

        return heights.length - maxLen;
    }

    public static int getEvictNums2(int n, int[] arr){
        int[] left = new int[n]; //存储每个数左边小于其的数的个数
        int[] right = new int[n];//存储每个数右边小于其的数的个数
        left[0] = arr[0];
        right[n - 1] = arr[n-1];
        //记录以i为终点的从左向右和从右向左走的子序列元素个数
        int num[] =new  int [n];
        //记录当前子序列的长度
        int index = 1;
        for(int i=1;i<n;i++){
            if(arr[i]>left[index-1]){
                //直接放在尾部，i左侧元素个数
                num[i] = index;
                //更新递增序列
                left[index++] = arr[i];
            }else {
                //找到当前元素应该放在的位置
                int low = 0,high = index-1;
                while(low < high){
                    int mid = (low+high)/2;
                    if(left[mid] <arr[i]){
                        low = mid + 1;

                    }
                    else{
                        high = mid;
                    }
                }

                // 下面这两行是精髓所在
                // 将所属位置替换为当前元素
                left[low] = arr[i];
                // 当前位置i的左侧元素个数
                num[i] = low;
            }
        }
        index = 1;
        for(int i=n-2;i>=0;i--){
            if(arr[i]>right[index-1]){
                num[i] += index;
                right[index++] = arr[i];
            }else {
                int low = 0,high = index-1;
                while(low < high){
                    int mid = (high+low)/2;
                    if(right[mid]<arr[i]){
                        low = mid+1;
                    }
                    else {
                        high = mid;
                    }
                }
                right[low] = arr[i];
                num[i]+=low;
            }
        }
        int max = 1;
        for (int number: num ){
            max = Math.max(max,number);
        }
        // max+1为最大的k
        return  n - max;
    }
}
