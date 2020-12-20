package com.gson.javajdk;

import org.junit.Test;

import java.util.Random;
import java.util.concurrent.*;

public class CyclicBarrierTest {
    @Test
    public void test() throws InterruptedException {
        int barrierNum = 5;
        //屏障，会阻塞5个线程
        CyclicBarrier barrier = new CyclicBarrier(barrierNum, new Runnable() {
            @Override
            public void run() {
                System.out.println("all threads reached barrier");
            }
        });

        ExecutorService service = Executors.newFixedThreadPool(barrierNum);
        for (int i = 0; i < barrierNum; i++) {
            service.submit(new Runnable() {
                @Override
                public void run() {
                    try {
                        int randomSleepTime = new Random().nextInt(5000);
                        Thread.sleep(randomSleepTime);
                        System.out.println(String.format("%s sleep %s ms and reach barrier",
                                Thread.currentThread(), randomSleepTime));
                        barrier.await();

                        System.out.println(String.format("after all threads reached barrier, %s begin to" +
                                " remaining work", Thread.currentThread()));
                    } catch (InterruptedException | BrokenBarrierException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
        int oldNum = barrier.getNumberWaiting();
        System.out.println("oldNum=" + oldNum);
        while (true){
            int newNum = barrier.getNumberWaiting();
            if (newNum != oldNum){
                System.out.println("oldNum=" + newNum);
                //5个线程都到达屏障
                if (oldNum == 4 && newNum == 0){
                    break;
                }
                oldNum = newNum;
            }
        }
    }
}
