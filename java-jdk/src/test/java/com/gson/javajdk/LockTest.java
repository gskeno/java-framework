package com.gson.javajdk;

import org.junit.Assert;
import org.junit.Test;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LockTest extends Assert {
    public static final Lock lock = new ReentrantLock();
    private static final MutexLock mutexLock = new MutexLock();

    @Test
    public void test() throws InterruptedException {
        long begin = System.currentTimeMillis();
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                //获取锁后不释放
                System.out.println(Thread.currentThread() + "begin lock, time=" + TestUtils.getTime());
                lock.lock();
                System.out.println(Thread.currentThread() + "begin lock success,time=" + TestUtils.getTime());
                while (true) {
                }
            }
        }, "thread1");
        thread1.start();

        Thread.sleep(3000);

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        System.out.println(Thread.currentThread() + "begin lockInterruptibly,time=" + TestUtils.getTime());
                        lock.lockInterruptibly();
                        System.out.println(Thread.currentThread() + "begin lockInterruptibly success,time=" + TestUtils.getTime());
                    } catch (InterruptedException e) {
                        System.out.println(Thread.currentThread() + "lockInterruptibly InterruptedException,time=" + TestUtils.getTime());
                    }
                }
            }
        }, "thread2");
        thread2.start();

        while (true) {
            if (System.currentTimeMillis() - begin > 10 * 1000) {
                thread2.interrupt();
                Thread.sleep(1000 * 1);
            }
        }
    }

    @Test
    public void testCondition() throws InterruptedException {
        Condition condition = lock.newCondition();
        try {
            condition.await();
        }catch (Exception e){
            e.printStackTrace();
            assertTrue(e instanceof IllegalMonitorStateException);
        }
    }

    @Test
    public void testMutexLock() throws InterruptedException {
        new Thread(new Runnable() {
            @Override
            public void run() {
                mutexLock.lock();
                System.out.println(Thread.currentThread().getName() + "获取mutexLock成功," + TestUtils.getTime());
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                }
                mutexLock.unlock();
                System.out.println(Thread.currentThread().getName() + "释放mutexLock成功," + TestUtils.getTime());

            }
        },"Thread1").start();

        Thread.sleep(2000);

        new Thread(new Runnable() {
            @Override
            public void run() {
                mutexLock.lock();
                System.out.println(Thread.currentThread().getName() + "获取mutexLock成功," + TestUtils.getTime());
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                }
                System.out.println(Thread.currentThread().getName() + "释放mutexLock成功," + TestUtils.getTime());
            }
        },"thread2").start();

        while (true){
        }

    }
}
