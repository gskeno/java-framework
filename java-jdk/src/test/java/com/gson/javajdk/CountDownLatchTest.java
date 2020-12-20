package com.gson.javajdk;

import org.junit.Test;

import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicLong;

public class CountDownLatchTest {

    @Test
    public void testConcurrentAddNums() throws InterruptedException {
        AtomicLong counter = new AtomicLong(0);
        CountDownLatch startSignal = new CountDownLatch(1);
        CountDownLatch doneSignal = new CountDownLatch(10);

        ExecutorService service = Executors.newFixedThreadPool(20);

        for (int i = 0; i < 10; i++) {
            service.submit(new Worker(startSignal, doneSignal, counter));
        }
        Thread.sleep(3000);
        //唤起子线程，使子线程一起开始工作
        startSignal.countDown();
        long start = System.currentTimeMillis();
        //等待子线程全部执行完，唤醒
        doneSignal.await();
        long end = System.currentTimeMillis();
        //获取子线程的计算总和
        System.out.println(String.format("use time = %s, counter=%s", (end - start), counter.get()));

    }

    class Worker implements Runnable{
        private  AtomicLong counter;
        private  CountDownLatch startSignal;
        private  CountDownLatch doneSignal;

        Worker(CountDownLatch startSignal, CountDownLatch doneSignal,AtomicLong counter){
            this.startSignal = startSignal;
            this.doneSignal = doneSignal;
            this.counter = counter;
        }

        @Override
        public void run() {
            try {
                startSignal.await();
            } catch (InterruptedException e) {
            }
            for (int i = 0; i < 100; i++) {
                try {
                    Thread.sleep(new Random().nextInt(200));
                } catch (InterruptedException e) {
                }
                int randomNum = new Random().nextInt(50) + 1;
                long currentCounter = counter.addAndGet(randomNum);
                System.out.println(Thread.currentThread() + " counter =" + currentCounter);
            }
            doneSignal.countDown();
            System.out.println(Thread.currentThread() + "countDown");
        }
    }
}
