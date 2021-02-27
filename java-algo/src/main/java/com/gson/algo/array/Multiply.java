package com.gson.algo.array;

import java.util.Arrays;

/**
 * https://www.nowcoder.com/practice/94a4d381a68b47b7a8bed86f2975db46?tpId=13&tqId=11204&rp=1&ru=%2Fta%2Fcoding-interviews&qru=%2Fta%2Fcoding-interviews%2Fquestion-ranking&tab=answerKey
 * 给定一个数组A[0,1,...,n-1],请构建一个数组B[0,1,...,n-1],
 * 其中B中的元素B[i]=A[0]*A[1]*...*A[i-1]*A[i+1]*...*A[n-1]。
 * 不能使用除法。（注意：规定
 * B[0] = A[1] * A[2] * ... * A[n-1]，
 * B[n-1] = A[0] * A[1] * ... * A[n-2];）
 * 对于A长度为1的情况，B无意义，故而无法构建，因此该情况不会存在。
 */
public class Multiply {
    /**
     * eg 输入 [1,2,3,4,5]
     *    输出 [120,60,40,30,24]
     * @param A
     * @return
     */
    public int[] multiply(int[] A) {
        if (A == null || A.length == 0 || A.length == 1){
            return null;
        }
        int[] B = new int[A.length];
        int[] leftB = new int[A.length];
        int[] rightB = new int[A.length];

        //思路
        // leftB[i] = A[0] * ... * A[i-1]
        // leftB[i+1] = leftB[i] * A[i]
        leftB[0] = 1;
        for (int i = 1; i < A.length; i++) {
            leftB[i] = leftB[i-1] * A[i-1];
        }

        //rightB[i] = A[i+1] * A[i+2] * ... * A[n-1]
        //rightB[i+1] = A[i+2] * A[i+3] *... * A[n-1]
        //故得出: rightB[i] = rightB[i+1] * A[i+1]
        rightB[A.length-1] = 1;
        for (int i = A.length-2; i >=0 ; i--) {
            rightB[i] = rightB[i+1] * A[i+1];
        }

        for (int i = 0; i < A.length; i++) {
            B[i] = leftB[i] * rightB[i];
        }
        return B;
    }

    public static void main(String[] args) {
        Multiply multiply = new Multiply();
        int[] a = {1,2,3,4,5};
        int[] b = multiply.multiply(a);
        System.out.println(Arrays.toString(b));
    }
}
