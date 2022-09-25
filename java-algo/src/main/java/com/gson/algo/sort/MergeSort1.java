package com.gsonkeno.dsaa.sort;

/**
 * 归并排序
 * Created by gaosong on 2018-01-30
 */
public class MergeSort {

    /**
     * 将两个有序表合并成一个有序表
     * @param a 待排序的数组
     * @param firstStart 第一个有序表在数组中的起始位置
     * @param secondStart 第二个有序表在数组中的起始位置
     * @param secondEnd 第二个有序表在数组中的结束位置
     *
     *  思想:建立一个中间数组b，排序过程中将元素有序的插入到中间数组b中
     */
    private static void merge(int[] a, int firstStart, int secondStart, int secondEnd){
        System.out.println("merge a," + firstStart + "," + secondStart + "," + secondEnd);
        int i = firstStart, j = secondStart, k = 0;
        int[] temp = new int[secondEnd - firstStart + 1];

        while ( i< secondStart && j <= secondEnd){
            if (a[i] <= a[j]){
                temp[k] = a[i];
                i++;
                k++;
            }else {
                temp[k] = a[j];
                j++;
                k++;
            }
        }

        while (i < secondStart){
            temp[k] = a[i];
            i++;
            k++;
        }

        while (j <= secondEnd){
            temp[k] = a[j];
            j++;
            k++;
        }

        System.arraycopy(temp, 0, a, firstStart, temp.length);

        for (int m = 0; m < a.length; ++m) {
            System.out.print(a[m] + "　");
        }
        System.out.println("-----");
    }


    /**
     *
     * @param a
     * @param s
     * @param len 每次归并的有序集合的长度,最小值为1
     */
    public static void mergeSort(int[] a, int s, int len) {
        int size = a.length;
        //mid在这里表示有多少个数据对对需要归。
        //如现在数组是1,8, 6,9, 2,98, 23,28, 78共9个元素
        //而此次归并排序时有序集合的长度是2，上述9个元素从左至右依次每两个元素都是有序的
        //此次计算得到mid=9/4=2,表示需要对两个数据对进行归并排序。
        int mid = size / (len << 1);
        //接着上面的假设，还剩下一个元素，所以c表示整理好待排序的数据对后，剩下的待处理的数据的个数
        int c = size & ((len << 1) - 1); //此处其实表示是 size 除(2*len)的余数。 因为len永远是2的倍数，这是2进制除法中的小技巧。
        System.out.println("mergeSort s=" + s +", len=" + len + ", c=" + c + ", mid=" + mid);
        //　-------归并到只剩一个有序集合的时候结束算法-------//
        if (mid == 0)
            return;
        //　------进行一趟归并排序-------//
        //每趟归并排序都要找到两个有序表的
        //  第一个表的起始元素的位置
        //  第二个表的起始元素的位置
        //  第二个表的结束元素的位置
        for (int i = 0; i < mid; ++i) {
            s = i * 2 * len;
            merge(a, s, s + len, (len << 1) + s - 1);

        }
        //　-------将剩下的数和倒数一个有序集合归并-------//
        //为什么需要这样做呢？因为现在的元素个数并不能正好凑成整数个数据对
        //接着上面的假设，此时数组应该是 1,6,8,9,  2,23,28,98,  78
        if (c != 0)
            merge(a, size - c - 2 * len, size - c, size - 1);
        //　-------递归执行下一趟归并排序------//
        mergeSort(a, 0, 2 * len);
    }

    public static void main(String[] args) {
        //int[] a = new int[]{4, 3, 6, 1, 2, 5};
//        int[] a = new int[]{1,8, 6,9, 2,98, 23,28, 78};
        int[] a = new int[]{1,8, 6,9, 2,98, 23,28, 78,76,38};
        mergeSort(a, 0, 1);
        for (int i = 0; i < a.length; ++i) {
            System.out.print(a[i] + "　");
        }
    }
}
