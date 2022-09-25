package com.gsonkeno.dsaa.sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**冒泡排序
 * <p>冒泡排序算法的原理如下：（从后往前,每趟排序后的最小的值在最前面）
 * <p>Created by gaosong on 2017-07-08.
 */
public class BubbleSort {
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
            bubbleSort(floats);
            System.out.println(Arrays.toString(floats));
            nums.clear();
        }
    }
    public static  void bubbleSort(Float[] nums){
        int len = nums.length;

        //每次循环将最小值推到最前面
        for (int i = 0; i <len; i++) {
            boolean swap = false;
            for (int j = len-1; j > i ; j--) {
                if (nums[j] < nums[j-1]){
                    float temp = nums[j];
                    nums[j] = nums[j-1];
                    nums[j-1] = temp;
                    swap = true; //此次循环仍是乱序
                }
            }

            if (!swap) break;
        }
    }
}
