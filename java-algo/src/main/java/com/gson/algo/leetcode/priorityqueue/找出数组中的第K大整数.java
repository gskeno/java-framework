package com.gson.algo.leetcode.priorityqueue;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 1985. 找出数组中的第 K 大整数
 */
public class 找出数组中的第K大整数 {

    public static int compareS(String o1, String o2){
        if (o1.length() != o2.length()){
            return o1.length() - o2.length();
        }
        return o1.compareTo(o2);
    }
    public String kthLargestNumber(String[] nums, int k) {
        // 最小堆
        PriorityQueue<String> queue = new PriorityQueue<>(k, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
               return compareS(o1, o2);
            }
        });
        for (int i = 0; i < nums.length; i++) {
            if (queue.size() < k){
                queue.offer(nums[i]);
                continue;
            }
            if (compareS(nums[i], queue.peek()) > 0){
                queue.poll();
                queue.offer(nums[i]);
            }
        }
        return queue.poll();
    }

    public static void main(String[] args) {
        找出数组中的第K大整数 solution = new 找出数组中的第K大整数();
        String ans ;
        ans = solution.kthLargestNumber(new String[]{"3", "6", "7", "10"}, 4);
        System.out.println(ans);

        ans = solution.kthLargestNumber(new String[]{"2", "21", "12", "1"}, 3);
        System.out.println(ans);

        ans = solution.kthLargestNumber(new String[]{"0", "0"}, 2);
        System.out.println(ans);

        ans = solution.kthLargestNumber(new String[]{"0", "0", "2", "2"}, 2);
        System.out.println(ans);

        ans = solution.kthLargestNumber(new String[]{"0", "0", "2", "2", "3"}, 2);
        System.out.println(ans);

        ans = solution.kthLargestNumber(new String[]{"623986800","3","887298","695","794","6888794705",
                        "269409","59930972","723091307","726368","8028385786","378585"}, 11);
        System.out.println(ans);

        ans = solution.kthLargestNumber(new String[]{"233", "97"}, 1);
        System.out.println(ans);
    }
}
