package com.gson.algo.leetcode.binarysearch;


public class BinarySearch {

    /**
     * 求数组中第一个 >= x的元素位置
     * @param arr
     * @param x
     * @return
     */
    public int binarySearch(int[] arr, int x) {
        int low = 0, high = arr.length - 1;
        while (low < high) {
            // mid值可能等于low，但一定小于high
            int mid = low + (high - low) / 2;
            // 一次循环中要么使high变小，要么使low变大
            if (arr[mid] >= x) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }
        return low;
    }

    public static void main(String[] args) {
        BinarySearch binarySearch = new BinarySearch();
        int i = binarySearch.binarySearch(new int[]{1, 3, 5, 7, 9}, 8);
        System.out.println(i); // 4
        i = binarySearch.binarySearch(new int[]{1, 3, 5, 7, 9}, 9);
        System.out.println(i); // 4
        i = binarySearch.binarySearch(new int[]{1, 3, 5, 7, 9}, 1);
        System.out.println(i); // 4
        i = binarySearch.binarySearch(new int[]{1, 1, 3, 5, 7, 9}, 1);
        System.out.println(i); // 4
        i = binarySearch.binarySearch(new int[]{1, 7, 8}, 8);
        System.out.println(i); // 2
        i = binarySearch.binarySearch(new int[]{1, 7, 8}, 9);
        System.out.println(i); // 2
    }

}
