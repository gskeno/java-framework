package com.gsonkeno.dsaa.sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**二分插入排序，与插入排序细节上有些许不同
 * 此方案先找到待排序元素应该插入的位置，再移动位置后的元素
 * 最后插入待排序元素在目标位置
 * Created by gaosong on 2017-07-09.
 */
public class BinaryInsertSort {
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
            binaryInsertSort(floats);
            System.out.println(Arrays.toString(floats));
            nums.clear();
        }
    }

    // 二分法排序
    public static  void binaryInsertSort(Float a[]) {
        for (int i = 0; i < a.length; i++) {
            int low = 0, mid = 0, high = i;
            float temp = a[i];
            while (low <= high) {
                mid = (low + high) / 2;
                if (a[mid] < temp) {
                    low = mid + 1;
                } else {
                    high = mid - 1;
                }
            }
            // 找到要插入的位置，然后将这个位置以后的所有元素向后移动
            for (int j = i - 1; j > high; j--)

            {
                a[j + 1] = a[j];
            }
            a[high + 1] = temp;

        }
    }
}
