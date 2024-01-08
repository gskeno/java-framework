package com.gson.javajdk.math;

import java.util.Arrays;

public class ArraysTest {

    /**
     * 当未命中时，返回 (-(insertion point) - 1).
     * 这个插入点表示第一个比key大的元素在数组中的位置(从0开始)
     * 特别的，当key比数组所有元素都大时，可以认为插入点在array.length
     * @param args
     */
    public static void main(String[] args) {
        int[] nums = new int[]{1, 7, 20, 100, 200};
        int index = Arrays.binarySearch(nums, 0);
        System.out.println(index);
        index = Arrays.binarySearch(nums, 2);
        System.out.println(index);
        index = Arrays.binarySearch(nums, 300);
        System.out.println(index);
    }
}
