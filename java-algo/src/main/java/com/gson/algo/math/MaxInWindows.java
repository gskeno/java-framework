package com.gson.algo.math;

import java.util.ArrayDeque;
import java.util.ArrayList;

/**
 * https://www.nowcoder.com/practice/1624bc35a45c42c0bc17d17fa0cba788
 * 滑动窗口的最大值
 */
public class MaxInWindows {

    public ArrayList<Integer> maxInWindows(int [] num, int size) {
        ArrayList<Integer> res = new ArrayList<>();
        if(size == 0) {
            return res;
        }
        int begin;
        // 双端队列q的元素存储的都是数组num的下标
        ArrayDeque<Integer> q = new ArrayDeque<>();
        for(int i = 0; i < num.length; i++){
            // begin表示当前窗口的左位置,刚开始时，其是负数，当i=(size-1)时，左窗口位置为0
            begin = i - size + 1;
            if(q.isEmpty()){
                q.add(i);
            }
            // 保证双端队列q的首元素(下标值)一定是当前窗口最大元素的下标值
            // 左边界已经超过了上次最大值所在的位置
            else if(begin > q.peekFirst()){
                q.pollFirst();
            }
            // 同时要将数组nums中比当前元素值大的元素下标从q种移除出去
            while(!q.isEmpty() && num[q.peekLast()] <= num[i]){
                q.pollLast();
            }
            q.add(i);
            if(begin >= 0){
                res.add(num[q.peekFirst()]);
            }
        }
        return res;
    }

    public static void main(String[] args) {
        MaxInWindows maxInWindows = new MaxInWindows();
        ArrayList<Integer> integers = maxInWindows.maxInWindows(new int[]{2, 3, 4, 2, 6, 2, 5, 1}, 3);
        System.out.println(integers);
    }
}
