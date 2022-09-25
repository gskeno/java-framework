package com.gsonkeno.dsaa.sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**希尔插入排序
 * Created by gaosong on 2017-07-09.
 */
public class ShellSort {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        List<Float> nums = new ArrayList<>();
        //每行输入的数据以单个空格分隔
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] values = line.split(" ");

            for (int i = 0; i < values.length; i++) {
                String value = values[i];
                nums.add(Float.valueOf(value));
            }

            Float[] floats = nums.toArray(new Float[]{});
            shellSort(floats);
            System.out.println(Arrays.toString(floats));
            nums.clear();
        }

    }
    //希尔排序
    public static  void shellSort(Float[] nums){

        int d=nums.length;
        while(true)
        {
            d=d/2;
            for(int x=0;x<d;x++)
            {
                for(int i=x+d;i<nums.length;i=i+d)
                {
                    float temp=nums[i];
                    int j;
                    for(j=i-d;j>=0&&nums[j]>temp;j=j-d)
                    {
                        nums[j+d]=nums[j];
                    }
                    nums[j+d]=temp;
                }
            }
            if(d==1)
            {
                break;
            }
        }
    }

}