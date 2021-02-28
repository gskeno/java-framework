package com.gson.algo.heap;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * https://www.nowcoder.com/practice/9be0172896bd43948f8a32fb954e1be1?tpId=13&tqId=11216&rp=1&ru=%2Fta%2Fcoding-interviews&qru=%2Fta%2Fcoding-interviews%2Fquestion-ranking&tab=answerKey
 * 一堆数字的中位数
 */
public class MedianOfQueue {

    /**
     * 维护一个最大堆，最小堆；
     * insert元素时，第奇数个元素(从1开始)要放置在最大堆中
     * 第偶数个元素要放置在最小堆中,
     * 由于放置元素时，要一直保持两个堆的元素个数相差在1以内，
     * 所以insert元素时，最大堆与最小堆之间元素可能会调整，
     * 保证最大堆的堆顶  <= 最小堆的堆顶
     *
     *
     *         最大堆          最小堆
     *          1
     *      ------------------------
     *          1              2
     *      ------------------------
     *          2
     *        1                3
     *      -----------------------
     *          2              3
     *        1              4
     *      -----------------------
     *          3            4
     *       1    2       5
     *
     * @param num
     */
    //最小堆
    private PriorityQueue<Integer> minHeap = new PriorityQueue<>();
    //最大堆
    private PriorityQueue<Integer> maxHeap = new PriorityQueue<>(new Comparator<Integer>() {
        @Override
        public int compare(Integer o1, Integer o2) {
            return o2.compareTo(o1);
        }
    });
    int cnt = 0;
    public void Insert(Integer num) {
       cnt++;
       //奇数次插入元素，放置在最大堆上
       if (cnt % 2 == 1){
           //如果num 比最小堆的最小值p要大,num与p互换
           if (minHeap.peek() != null && num > minHeap.peek() ){
               minHeap.offer(num);
               num = minHeap.poll();
           }
           maxHeap.offer(num);
       }
       //偶数次插入元素，放置在最小堆上
       else {
           //如果num比最大堆的最大值p要小，num与p互换
           if (maxHeap.peek() != null && num < maxHeap.peek()){
               maxHeap.offer(num);
               num = maxHeap.poll();
           }
           minHeap.offer(num);
       }
    }

    public Double GetMedian() {
        double res = 0;
        //奇数个元素，则弹出最大堆的堆顶即可
        if (cnt % 2 == 1){
            res =  maxHeap.peek();
        }else {
            res = (maxHeap.peek() + minHeap.peek())/2.0;
        }
        return res;
    }

    public static void main(String[] args) {
        MedianOfQueue medianOfQueue = new MedianOfQueue();
        Double aDouble = medianOfQueue.GetMedian();
        System.out.println(aDouble);
    }
}
