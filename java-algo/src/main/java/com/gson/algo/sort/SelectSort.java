package com.gsonkeno.dsaa.sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**选择排序（Selection sort）
 * 是一种简单直观的排序算法。它的工作原理是每一次从待排序的数据元素中选出最小（或最大）的一个元素，
 * 存放在序列的起始位置，直到全部待排序的数据元素排完。
 *
 * 选择排序是不稳定的排序方法（比如序列[5， 5， 3]第一次就将第一个[5]与[3]交换，导致第一个5挪动到第二个5后面）。
 * Created by gaosong on 2017-07-07.
 *
 * 时间复杂度为O(n^2),最耗时的一种排序算法。 空间复杂度为O(1)
 */
public class SelectSort {

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

            Float[] results = selectSort(nums.toArray(new Float[]{}));
            System.out.println(Arrays.toString(results));
            nums.clear();
        }



    }

    public static  Float[]  selectSort(Float[] nums){

//        for (int i = 0; i < nums.length-1; i++) {
//            for (int j = i+1; j <nums.length ; j++) {
//                if (nums[i] > nums[j]){
//                    float temp = nums[j];
//                    nums[j] = nums[i];
//                    nums[i] = temp;
//                }
//            }
//
//        }
        //上面是不良示例，此示例下每次两元素比较时，若前者比后者大，则进行交换；
        //优良的设计是记录最小值元素的下标，若有其他值比最小值更小，则更换最小值的下标
        for (int i = 0; i < nums.length-1; i++) {
            int minIndex = i;
            for (int j = i+1; j <nums.length ; j++) {
                if (nums[j] < nums[minIndex]) minIndex = j;
            }

            //即此次循环的第一个元素不是最小值
            if (minIndex != i){
                float temp = nums[minIndex];
                nums[minIndex] = nums[i];
                nums[i] = temp;
            }
        }
          return  nums;
    }

}
