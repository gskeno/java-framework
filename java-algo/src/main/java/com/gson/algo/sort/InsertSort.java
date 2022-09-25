package com.gsonkeno.dsaa.sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**插入排序
 * Created by gaosong on 2017-07-09.
 * <p>时间复杂度是o(n^2),稳定性排序算法
 *
 * <p>假设i(1<=i<=n-1)号之前均有序，则需加入i号元素后，(i+1)号元素之前仍然有序
 * 如何做到呢?把i号元素的值temp升起，拿temp与i-1号元素的值做比较，若temp不比i-1号元素值小，则自然(i+1)号元素之前是有序的。
 * 如果temp比(i-1)号元素值小，则，(i-1)+1号元素的值用(i-1)号的值替代，i--，
 * 循环往复，直至temp不比(i-1)号元素的值小，设(i-1)+1号元素的值为temp。
 */
public class InsertSort {

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
            insertSort(floats);
            System.out.println(Arrays.toString(floats));
            nums.clear();
        }
    }

    public static  void insertSort(Float[] nums){
        int len = nums.length;

        for (int i = 1; i < len; i++) {
            if (nums[i] < nums[i-1]){
                float temp = nums[i];//temp比全部排好序的前面元素的最后一个值小，则升起
                int j = i-1;
                for (; j >=0 && nums[j]>temp ; j--) {
                    nums[j+1] = nums[j];
                }
                nums[j+1] = temp;
            }
        }

    }

}
