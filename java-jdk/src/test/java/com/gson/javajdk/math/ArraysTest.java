package com.gson.javajdk.math;

import java.util.Arrays;

public class ArraysTest {

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
