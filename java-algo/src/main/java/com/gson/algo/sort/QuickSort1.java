package com.gsonkeno.dsaa.sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**快速排序
 *
 * <p>关键词 递归 二分
 *
 * <p>通过一趟排序将要排序的数据分割成独立的两部分，其中一部分的所有数据都比另外一部分的所有数据都要小，
 * 然后再按此方法对这两部分数据分别进行快速排序，整个排序过程可以递归进行，以此达到整个数据变成有序序列
 * Created by gaosong on 2017-07-08.
 */
public class QuickSort {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Float> nums = new ArrayList<>();
        //每行输入的数据以单个空格分隔
        while (scanner.hasNextLine()){
            String line = scanner.nextLine();
            String[] values = line.split(" ");

            for (int i = 0; i < values.length; i++) {
                String value = values[i];

                nums.add(Float.valueOf(value));
            }
            Float[] floats = nums.toArray(new Float[]{});
            quickSort(floats,0,floats.length -1 );
            System.out.println(Arrays.toString(floats));
            nums.clear();
        }
    }

    /**
     * 快速排序递归
     * @param nums
     * @param beginPos 数组需要排序部分开始位置，最小值为0
     * @param endPos   数组需要排序部分结束位置，最大值为数组size - 1
     */
    public static  void quickSort(Float[] nums, int beginPos, int endPos){
        //以下所说的数组不是指nums，而是nums的beginPos到endPos截取的数组部分

        //从前往后遍历的指针，也即数组下标
        int i = beginPos;
        //从后往前遍历的指针，也即数组下标
        int j = endPos;

        if (i >= j) return;

        //数组的第一个元素作为比较值，顺便可以想象为将此元素的数值抽空。
        float midValue = nums[beginPos];
        //此次while循环过后，理想情况下，比较值将要处于数组的较中间位置
        while (i < j){
            //从后往前找小于比较值的数组元素，
            //若找不到，且前后遍历指针仍未相遇，继续往前找
            while (nums[j] >= midValue && j > i){
                j --;
            }

            //此时，从后往前找已经找到小于比较值的元素，假设其值是lower

            //若此时的前后遍历指针仍未相遇，则将lower赋给抽空元素,也即nums[i]
            //顺便想象为此时的j位置数组元素变成了抽空元素，原来的抽空元素
            //已经有了值，不再是抽空元素
            if (i < j){
                nums[i] = nums[j];
                //从前往后遍历指针也顺便+1
                i ++ ;
            }

            //从前往后找大于比较值的数组元素
            //若找不到，且前后遍历指针仍未相遇，继续往后找
            while (nums[i] <= midValue && i < j){
                i ++;
            }

            //此时，从后往前找已经找到大于比较值的元素，假设其值是higher

            //若此时的前后遍历指针仍未相遇，则将higher赋给抽空元素,也即nums[j]
            //顺便想象为此时的i位置数组元素变成了抽空元素，原来的抽空元素nums[j]
            //已经有了值，不再是抽空元素
            if (i < j){
                nums[j] = nums[i];
                //从后往前遍历指针也顺便-1
                j--;
            }
        }

        //此时i==j，即前后遍历的指针相遇，将比较值存储在此位置
        //那么存储值左边的元素必然比存储值小,存储值右边的元素必然比存储值大
        nums[i] = midValue;
        // 存储值左右两边的截取数组依然使用递归方式进行处理排序
        quickSort(nums,beginPos,i-1);
        quickSort(nums,j+1,endPos);
    }


    public static void quickSort(int[] A, int start, int end) {
        if (start >= end) {
            return;
        }

        int left = start, right = end;
        // key point 1: pivot is the value, not the index
        int pivot = A[(start + end) / 2];

        // key point 2: every time you compare left & right, it should be
        // left <= right not left < right
        while (left <= right) {
            while (left <= right && A[left] < pivot) {
                left++;
            }
            while (left <= right && A[right] > pivot) {
                right--;
            }
            if (left <= right) {
                int temp = A[left];
                A[left] = A[right];
                A[right] = temp;

                left++;
                right--;
            }
        }

        quickSort(A, start, right);
        quickSort(A, left, end);
    }
    
}
