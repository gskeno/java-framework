package com.gson.javajdk.lock;

import java.util.concurrent.locks.ReentrantLock;

public class AQSTest {
    private static ReentrantLock reentrantLock = new ReentrantLock();

    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                reentrantLock.lock();
                System.out.println(Thread.currentThread().getName() + "获取到锁");
                reentrantLock.unlock();
            }
        }, "thread-1").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                reentrantLock.lock();
                System.out.println(Thread.currentThread().getName() + "获取到锁");
                try {
                    Thread.sleep(30000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                reentrantLock.unlock();
            }
        },"thread-2").start();
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                reentrantLock.lock();
                System.out.println(Thread.currentThread().getName() + "获取到锁");
                reentrantLock.unlock();
            }
        }, "thread-3");
        thread.start();
        thread.interrupt();

    }
}
