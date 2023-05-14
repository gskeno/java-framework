package com.gson.algo.sort;

/**
 * 快排 算法4
 */
public class QuickSort4 {

    /**
     * 对数组nums从下标low到下标hi范围内的元素进行快速排序
     * @param nums
     * @param low
     * @param hi
     */
    public static void quickSort(int[] nums, int low, int hi){
        if (hi <= low){
            return;
        }
        int j = partition(nums, low, hi);
        quickSort(nums, low, j -1);
        quickSort(nums, j+1, hi);
    }

    /**
     * 快速排序的切分函数
     * @param nums
     * @param low  low肯定 < hi
     * @param hi
     * @return 返回值j，该函数执行后满足以下性质
     * 1. 数组nums下标j处的元素值被确定，放置的就是最终排序后j处应该放置的值
     * 2. a[low]至a[j-1]的值都 <= a[j]
     * 3. a[j+1]至a[hi]的值都  >= a[j]
     */
    public static int partition(int[] nums, int low, int hi){
        int i = low;
        int j = hi + 1;
        int pivot = nums[low];
        while (true){
            while (less(nums[++i], pivot)){
                // 越界处理
                if (i == hi) break;
            }


            while (less(pivot, nums[--j])){
                // 越界处理
                if (j == low) break;
            }

            // i左侧的元素都 <= pivot
            // j右侧的元素都 >= pivot
            if (i >= j){
                break;
            }
            exch(nums, i, j);
        }
        exch(nums, low, j);
        return j;
    }
    public static boolean less(int a, int b){
        return a < b;
    }

    public static void  exch(int[] nums, int i, int j){
        if (nums[i] != nums[j]){
            int temp = nums[i];
            nums[i] = nums[j];
            nums[j] = temp;
        }
    }

    public static void main(String[] args) {
       // QuickSort4.quickSort(new int[]{5,5,5,5,5}, 0, 4);
        QuickSort4.quickSort(new int[]{5,2,5,7}, 0, 3);
    }

}
