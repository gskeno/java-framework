package com.gson.javajdk;

import org.junit.Test;

import java.util.concurrent.ArrayBlockingQueue;

public class ArrayBlockingQueueTest {
    @Test
    public void test() throws InterruptedException {
        ArrayBlockingQueue blockingQueue = new ArrayBlockingQueue<String>(4);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    blockingQueue.put("1");
                    blockingQueue.put("2");
                    blockingQueue.put("3");
                    blockingQueue.put("4");
                    blockingQueue.put("5");
                    blockingQueue.put("6");
                }catch (Exception e){
                }
            }
        },"thread1").start();


        Thread.sleep(2*1000);
        //使thread1执行到put(5)时阻塞住
        //此时数组元素为[1,2,3,4]
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    //移除[1,2,3,4]中的1,2
                    blockingQueue.take();
                    blockingQueue.take();
                    //接着thread1执行put(5),put(6)
                    //数组元素此时为[5,6,3,4]
                }catch (Exception e){
                }
            }
        },"thread2").start();

        while (true){
        }
    }
}
