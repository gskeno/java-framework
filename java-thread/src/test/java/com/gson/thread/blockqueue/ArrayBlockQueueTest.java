package com.gsonkeno.jdk.training.concurrent.blockqueue;

import java.util.Arrays;
import java.util.concurrent.ArrayBlockingQueue;

/**
 * Created by gaosong on 2018-02-26
 */
public class ArrayBlockQueueTest {
    public static void main(String[] args) {
        Integer[] c = new Integer[]{1,2,3,4,5};
        ArrayBlockingQueue<Integer> queue = new ArrayBlockingQueue<Integer>(5,true, Arrays.asList(c) );

        //队列已满，再加入元素会抛出异常，请看BlockQueue的源码注释
        //queue.add(6);

        queue.remove(1);
        queue.remove(2);
        queue.remove(3);
        queue.remove(4);
        System.out.println(queue.remove(5));
        System.out.println(queue.remove(6));
    }
}
