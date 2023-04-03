package com.gson.algo.array;

import java.util.*;

public class 蓄水 {
    /**
     * 1. 哪些水桶容量+1 会表现的更好呢？
     * 2. 水桶加1会使整个世界"暂停"，即所有水缸不能蓄水，所以水桶容量+1还是不变，需要评估
     * @param bucket
     * @param vat
     * @return
     */
    public int storeWater(int[] bucket, int[] vat) {
        int upgradeCount = 0;
        // 蓄水操作次数越多，水桶编号越低(越高亦可)， 优先级越高，越应该优先处理
        PriorityQueue<int[]> queue = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[1] != o2[1]){
                    return o2[1] - o1[1];
                }
                return o2[0] - o1[0];
            }
        });
        // 每个缸装满需要的次数
        int[] full = new int[bucket.length];
        // 最大蓄水次数
        int maxStoreCount = 0;
        for (int i = 0; i < bucket.length; i++) {
            // 水缸刻度为0时，不需要操作
            if (vat[i] == 0){
                // 避免后来会/0
                bucket[i] = 1;
                continue;
            }
            // 水桶容量为0时，必须扩容，否则该水缸永远装不满
            if (bucket[i] == 0) {
                bucket[i] = 1;
                upgradeCount++;
            }
            // 向上取整技巧， a / b = [a + (b-1)]/ b
            full[i] = (vat[i] + bucket[i] - 1) / bucket[i];
            // 升级一次，操作次数并没有变多，则入队列
            // if (1 + (vat[i] + bucket[i]) / (bucket[i] + 1) <= (vat[i] + bucket[i] - 1) / bucket[i]){
                queue.offer(new int[]{i, full[i]});
            // }
            maxStoreCount = Math.max(maxStoreCount, full[i]);
        }
        int minOperateCount = upgradeCount + maxStoreCount;
        System.out.println("minOperateCount=" + minOperateCount);

        while (!queue.isEmpty()){
            int[] bucketNoAndStoreCount = queue.poll();
            int bucketNo = bucketNoAndStoreCount[0];
            if (1 + (vat[bucketNo] + bucket[bucketNo]) / (bucket[bucketNo] + 1) >
                    (vat[bucketNo] + bucket[bucketNo] - 1) / bucket[bucketNo]){
                break;
            }
            bucket[bucketNo]++;
            System.out.println("bucket[" + bucketNo + "]=" + bucket[bucketNo]);
            full[bucketNo] = (vat[bucketNo] + bucket[bucketNo] - 1) / bucket[bucketNo];
            upgradeCount++;
            // 多成长一次，操作次数并不会变多
            //if ((vat[bucketNo] + bucket[bucketNo] - 1) / bucket[bucketNo] >= 1 + (vat[bucketNo] + bucket[bucketNo]) / (bucket[bucketNo] + 1)) {
                queue.offer(new int[]{bucketNo, full[bucketNo]});
            //}

            maxStoreCount = queue.peek()[1];
            minOperateCount = Math.min(minOperateCount, maxStoreCount + upgradeCount);
            System.out.println("minOperateCount=" + minOperateCount);
        }

        return minOperateCount;
    }

    public static void main(String[] args) {
        蓄水 solution = new 蓄水();
        int ans ;
//        ans = solution.storeWater(new int[]{1, 3}, new int[]{6, 8});
//        System.out.println(ans);
//
//        ans = solution.storeWater(new int[]{9,0,1}, new int[]{0,2,2});
//        System.out.println(ans);

//        ans = solution.storeWater(new int[]{0}, new int[]{0});
//        System.out.println(ans);
//        ans = solution.storeWater(new int[]{1, 5}, new int[]{120, 200});
//        System.out.println(ans);
        ans = solution.storeWater(new int[]{5252,8417,6745,1503,6356,6583,85,3513,2536,6644,7705,8371,6112,6981,3260,9961,7615,4221,8828,8937,4255,7390,3864,8330,7912,6073,4919,4998,8640,2528,4342,5024,2395,2181,3374,3916,6984,65,9412,9667,2512,5804,3278,1421,9869,1320,5258,6572,3318,4288,4250,1223,8974,7042,7123,3004,1457,6396,8827,1177,3653,815,6967,7143,2064,616,1034,500,2336,6374,4753,5545,2201,8747,9242,7430,213,3555,2943,1131,4121,7484,1119,8894,9309,4228,9352,7750,9155,9611,481,4676,1644,4823,484,1065,1252,2308,4139,6685},
                new int[]{7061,9787,7009,2296,8070,7316,4937,4130,4862,7190,8982,9236,9245,9670,6450,9978,7826,5036,9452,9751,6047,9356,8702,8810,9754,9454,7069,5562,8916,7418,4909,9824,6555,4428,9005,5241,8631,7338,9850,9967,9126,9941,8842,9169,9993,8704,9414,9527,6981,9151,4496,3698,9005,9804,7575,8182,7752,8432,8961,9011,9995,1321,9576,9463,3125,9551,6718,6097,7400,8961,8769,9904,9675,9699,9567,7851,3085,6510,6556,8982,9468,8998,1561,9788,9761,4655,9393,7780,9695,9868,7791,9671,7142,9777,6147,1750,2913,5585,8349,7616});
        System.out.println(ans);


    }
}
