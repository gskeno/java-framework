package com.gson.algo.leetcode;

import java.util.Arrays;
import java.util.Comparator;

/**
 * https://leetcode.cn/problems/maximum-units-on-a-truck/
 */
public class 卡车上的最大单元数 {

    public int maximumUnits(int[][] boxTypes, int truckSize) {
        Arrays.sort(boxTypes, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o2[1] - o1[1];
            }
        });
        int usedBoxes = 0;
        int maxUnit = 0;
        for(int[] boxType : boxTypes){
            if (usedBoxes == truckSize){
                return maxUnit;
            }

            if (boxType[0] >= (truckSize - usedBoxes)){
                maxUnit += (truckSize - usedBoxes) * boxType[1];
                usedBoxes  = truckSize;
            }else {
                maxUnit += boxType[0] * boxType[1];
                usedBoxes += boxType[0];
            }
        }
        return maxUnit;
    }

    public static void main(String[] args) {
        卡车上的最大单元数 solution = new 卡车上的最大单元数();
        int ans = solution.maximumUnits(new int[][]{{1, 3}, {2, 2}, {3, 1}}, 4);
        System.out.println(ans);

        ans = solution.maximumUnits(new int[][]{{5, 10}, {2, 5}, {4, 7}, {3,9}}, 10);
        System.out.println(ans);
    }
}
