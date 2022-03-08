package com.gson.algo.sort;

import java.util.Arrays;

/**
 * 归并排序(非递归)
 */
public class MergeSort2 {

    public int[] sortArray(int[] nums){
        int length = nums.length;
        int[] src = nums;
        int[] dest = new int[length];

        // 段大小1变为2变为4，
        for (int seg = 1; seg < length; seg = seg*2) {
            // 每次处理相邻的两个段，每个段的元素个数都为seg
            for (int start = 0; start < length; start += seg*2) {
                // 每次处理的相邻的两个段的第二个段的首元素所在位置
                int mid = Math.min(start + seg, length);
                // 每次处理的相邻的两个段的第二个段的末尾元素所在位置+1
                int end = Math.min(start + seg*2, length);

                // i指向第一个段的首位置，j表示第二个段的首位置， k表示要插入到dest数组的位置
                int i = start, j = mid, k = start;
                while ( i < mid | j <end){
                    if (j == end || (i <mid && src[i] <src[j])){
                        dest[k++] = src[i++];
                    }else {
                        dest[k++] = src[j++];
                    }
                }
            }

            // 每次将排序结果放到原数组src中
            int[] temp = src;
            src = dest;
            dest = temp;
        }

        return src;
    }

    public static void main(String[] args) {
        MergeSort2 mergeSort2 = new MergeSort2();
        int[] nums = {4,1,5,6,2,7,8,3};
        int[] result = mergeSort2.sortArray(nums);
        System.out.println(Arrays.toString(result));
    }
}
