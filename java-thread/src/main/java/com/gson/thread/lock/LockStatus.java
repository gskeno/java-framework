package com.gsonkeno.jdk.training.concurrent.lock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by gaosong on 2017-09-03
 * lock.lock()获取不到锁的线程处于waiting状态，不是blocked状态
 */
public class LockStatus {
    private static Lock lock = new ReentrantLock();
    public static void main(String[] args) {
        Thread thread1 = new Thread(new LockThread("thrread1"),"thread1");
        Thread thread2 = new Thread(new LockThread("thrread2"),"thread2");

        thread1.start();
        thread2.start();
    }

    static class LockThread implements Runnable{

        private final String desc;

        public LockThread(String desc){
            this.desc = desc;
        }
        @Override
        public void run() {
            lock.lock();

            try {
                while(true){

                }
            } finally {
                lock.unlock();
            }
        }
    }
}
