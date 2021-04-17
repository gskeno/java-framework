package com.gson.algo.math;

/**
 * https://www.nowcoder.com/practice/6aa9e04fc3794f68acf8778237ba065b?tpId=13&tqId=11186&rp=1&ru=%2Fta%2Fcoding-interviews&qru=%2Fta%2Fcoding-interviews%2Fquestion-ranking&tab=answerKey
 * 丑数
 */
public class GetUglyNumber_Solution {
    public int GetUglyNumber_Solution(int index) {
        if (index <= 0){
            return 0;
        }
        int p2 = 0, p3 = 0, p5 = 0;
        int[] arr = new int[index];
        arr[0] = 1;
        for (int i = 1; i < arr.length; i++) {
            arr[i] = Math.min(arr[p2] * 2, Math.min(arr[p3]*3, arr[p5] * 5));
            if (arr[i] == arr[p2] * 2){
                p2++;
            }
            if (arr[i] == arr[p3] * 3){
                p3++;
            }
            if (arr[i] == arr[p5] * 5){
                p5++;
            }
        }
        return arr[index-1];
    }

    public static void main(String[] args) {
        GetUglyNumber_Solution solution = new GetUglyNumber_Solution();
        int i = solution.GetUglyNumber_Solution(8);
        System.out.println(i);
    }

}
