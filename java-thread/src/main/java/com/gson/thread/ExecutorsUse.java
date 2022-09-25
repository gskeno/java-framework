package com.gsonkeno.jdk.training.concurrent;



import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 工厂类和静态方法，用于创建线程池，线程池来管理线程的生命周期
 * Created by gaosong on 2017-07-11.
 */
public class ExecutorsUse {

    public static void main(String[] args) {
        ExecutorService poolService = Executors.newFixedThreadPool(2);

        MyThread myThread = new MyThread(5);
        MyThread myThread1 = new MyThread(3);
        MyThread myThread2 = new MyThread(1);
        MyThread myThread3 = new MyThread(2);
        MyThread myThread4 = new MyThread(4);

        poolService.execute(myThread);
        poolService.execute(myThread1);
        poolService.execute(myThread2);
        poolService.execute(myThread3);
        poolService.execute(myThread4);

        poolService.shutdown();
        System.out.println("shutdown invoked");

//        poolService.shutdownNow();
//        System.out.println("shutdownNow invoked");
    }

    static class MyThread implements Runnable {
        private int flagNum;

        public MyThread(int flagNum) {
            this.flagNum = flagNum;
        }

        @Override
        public void run() {
            try {
                System.out.println("Thread:" +
                        Thread.currentThread().getName() + ",flagNum:" + flagNum
                        + ",Time" + System.currentTimeMillis());
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                System.out.println(e);
                e.printStackTrace();
            }
        }
    }

    //饱和策略；
    //当线程池中的线程都在工作，且线程池已经满了的情况下，再提交任务，会直接抛AbortPolicy异常，即放弃策略
    @Test
    public void testAddThreadAftFill() throws InterruptedException {
        ExecutorService service = Executors.newFixedThreadPool(3);
        MyThread myThread = new MyThread(3);
        service.submit(myThread);
        service.submit(myThread);
        service.submit(myThread);
        service.submit(myThread);
        service.shutdown();
        //service.awaitTermination(Long.MAX_VALUE, TimeUnit.DAYS);
       // service.submit(new MyThread(0));

    }
}
