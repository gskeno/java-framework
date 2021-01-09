package com.gson.javajdk.concurrent;

import org.junit.Test;

import java.util.concurrent.SynchronousQueue;

public class SynchronousQueueTest {
    @Test
    public void testTakeThenPut() throws InterruptedException {
        SynchronousQueue<String> queue = new SynchronousQueue<>(true);
        for (int i = 0; i < 2; i++) {
            Thread.sleep(2 *1000);
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        String take = queue.take();
                        System.out.println("take=" + take);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            },"threadTake" + i).start();
            Thread.sleep(2 *1000);
        }


        Thread.sleep(10*1000);
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    queue.put("2");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"threadPut").start();

        while (true){

        }
    }
}
