package com.gson.javajdk.lock;

import java.util.concurrent.locks.LockSupport;

public class LockSupportTest {
    private static Object blocker = new Object();

    private static Object lock = new Object();

    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (lock){
                    LockSupport.park(blocker);
                    System.out.println(Thread.currentThread().getName() + " pack success");
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (lock){
                    LockSupport.park(blocker);
                    System.out.println(Thread.currentThread().getName() + " pack success");
                }
            }
        }).start();

        System.out.println("hello");
    }
}
