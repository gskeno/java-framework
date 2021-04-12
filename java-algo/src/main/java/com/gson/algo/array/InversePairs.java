package com.gson.algo.array;

/**
 * https://www.nowcoder.com/practice/96bd6684e04a44eb80e6a68efc0ec6c5?tpId=13&tqId=11188&rp=1&ru=%2Fta%2Fcoding-interviews&qru=%2Fta%2Fcoding-interviews%2Fquestion-ranking&tab=answerKey
 * 逆序对
 */
public class InversePairs {

    //借助归并排序思想
    public int InversePairs(int [] array) {
        if (array == null || array.length == 1){
            return 0;
        }
        return sort(array, 0, array.length-1);
    }

    public int sort(int[] arr, int lo, int hi){
        if (lo == hi) { // <----- 安全带
            return 0 ;
        }
        int mid = ((hi - lo) >> 1) + lo;
        int a = sort(arr, lo, mid);
        int b = sort(arr, mid + 1, hi);
        int c = merge(arr, lo, mid, hi);
        return a + b +c;
    }

    /**
     * 返回有序数组[low-mid], [mid+1 high]  组成的逆序对个数
     * @return
     */
    public int merge(int[] arr, int low, int mid, int high){
        int[] temp = new int[high - low + 1];
        int i = 0, p1 = low, p2 = mid + 1;
        int count = 0;
        int index = 0;
        while (p1 <= mid && p2 <= high) {
            // 与归并排序不同的地方，在merge过程中统计逆序对数
            if (arr[p1] > arr[p2]) {
                count += mid - p1 + 1;
                temp[index++] = arr[p2++];
            } else {
                temp[index++] = arr[p1++];
            }
        }
        while (p1 <= mid) {
            temp[index++] = arr[p1++];
        }
        while (p2 <= high) {
            temp[index++] = arr[p2++];
        }
        for (i = 0; i < temp.length; i++) {
            arr[low + i] = temp[i];
        }
        return count;
    }

    public static void main(String[] args) {
        InversePairs inversePairs = new InversePairs();
        int i = inversePairs.InversePairs(new int[]{1,2,3,4,5,6,7,0});
        System.out.println(i);
    }
}
