package com.gsonkeno.jdk.training.concurrent.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by gaosong on 2017-09-16
 */
public class ReentrantLockUse {

    private static  Lock lock = new ReentrantLock();
    public static void main(String[] args) {
        Thread thread1 = new Thread(new RunningThread(), "RunningThread1");

        Thread thread2 = new Thread(new RunningThread(), "RunningThread2");

        thread1.start();
        thread2.start();
    }

    private static class RunningThread implements Runnable{
        @Override
        public void run() {
            try {
                lock.lock();
                while (true){
                }
            } finally {
                lock.unlock();
            }
        }
    }
}
