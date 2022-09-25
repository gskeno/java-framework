package com.gsonkeno.jdk.training.concurrent;

import org.junit.Test;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by gaosong on 2017-09-02
 */
public class SynLockUse {

    public static Object o = new Object();
    public static Lock  lock = new ReentrantLock();

    public static void main(String[] args) {

//        new SynLockUse().testLockInterrupt();
        new SynLockUse().testTryLock();
//        Thread thread1 = new Thread(new MyThread("thread1"),"thread1");
//        Thread thread2 = new Thread(new MyThread("thread2"),"thread2");
//
//        thread1.start();
//
//        SleepUtil.sleep(3);
//        thread2.start();
//        SleepUtil.sleep(3);

//        System.out.println("执行中断前");
//        thread1.interrupt();
//        SleepUtil.sleep(2);
//        thread2.interrupt();
//        System.out.println("执行中断后");
//
//        SleepUtil.sleep(2);
    }

    static class MyThread implements Runnable {
        private final String desc;

        public MyThread(String desc) {
            this.desc = desc;
        }

        @Override
        public void run() {
            System.out.println("线程" + desc + "进入同步代码块前");
//            synchronized (o) {
//                System.out.println("线程" + desc + "进入同步代码块后");
//                while (true) {
//
//                }
//            }


            lock.lock();
            System.out.println("线程" + desc + "进入同步代码块后");
            try {
                while (true) {

                }
            } finally {
                lock.unlock();
            }

        }
    }

    static class SleepUtil{
        public static void sleep(int seconds){
            try {
                Thread.sleep(seconds *1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void testLockInterrupt(){
        Thread thread1 = new Thread(new LockThread("thread1"),"thread1");
        Thread thread2 = new Thread(new LockThread("thread2"),"thread2");

        thread1.start();
        SleepUtil.sleep(3);
        thread2.start();
        SleepUtil.sleep(3);

        thread1.interrupt();
    }

    @Test
    public void testTryLock(){
        Thread thread1 = new Thread(new TryLockThread("thread1"),"thread1");
        Thread thread2 = new Thread(new TryLockThread("thread2"),"thread2");

        thread1.start();
        SleepUtil.sleep(3);
        thread2.start();
        SleepUtil.sleep(3);

    }

    static class LockThread implements Runnable{

        private final String threadDesc;

        public LockThread(String threadDesc){
            this.threadDesc = threadDesc;
        }
        @Override
        public void run() {
            try {
                lock.lockInterruptibly();
                System.out.println("线程" + threadDesc + "进入锁");
            } catch (InterruptedException e) {//获取锁失败，比如被中断
                e.printStackTrace();
                System.out.println("线程" + threadDesc + "被中断");
                return;
            }

            try {
                while (true){

                }
            } finally {
                lock.unlock();
                System.out.println("线程" + threadDesc + "退出锁");
            }

        }
    }

    static class TryLockThread implements Runnable{

        private final String threadDesc;

        public TryLockThread(String threadDesc){
            this.threadDesc = threadDesc;
        }
        @Override
        public void run() {
            try {
                lock.tryLock(1, TimeUnit.SECONDS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            if(lock.tryLock()){
               System.out.println("线程" + threadDesc + "获取锁成功");
               while (true){

               }
           }else{
               System.out.println("线程" + threadDesc + "获取锁失败");
           }


        }
    }
}
