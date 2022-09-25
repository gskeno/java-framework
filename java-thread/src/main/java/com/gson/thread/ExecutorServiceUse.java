package com.gsonkeno.jdk.training.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by gaosong on 2017-07-16.
 */
public class ExecutorServiceUse {
    public static void main(String[] args) {
        ExecutorService service = Executors.newFixedThreadPool(3);
        MyThread myThread = new MyThread(3);
        service.submit(myThread);
        service.submit(myThread);
        service.submit(myThread);
        service.submit(myThread);

        service.shutdown();

    }


    static  class MyThread implements Runnable {
        private int flagNum;

        public MyThread(int flagNum) {
            this.flagNum = flagNum;
        }

        @Override
        public void run() {
            try {
                Thread.sleep(5000);
                System.out.println("Thread:" +
                        Thread.currentThread().getName() + ",flagNum:" + flagNum
                        + ",Time" + System.currentTimeMillis());

            } catch (InterruptedException e) {
                System.out.println(e);
            }
        }
    }
}

