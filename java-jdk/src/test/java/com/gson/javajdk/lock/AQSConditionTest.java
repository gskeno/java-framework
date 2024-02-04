package com.gson.javajdk.lock;

import com.gson.javajdk.DateUtil;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class AQSConditionTest {
    static ReentrantLock lock = new ReentrantLock(true);
    static Condition condition = lock.newCondition();

    public static void main(String[] args) throws InterruptedException {
        Thread threadA = new Thread(new Runnable() {
            @Override
            public void run() {
                lock.lock();
                try {
                    System.out.println(Thread.currentThread().getName() + "获取到锁," + DateUtil.getTime());
                    Thread.sleep(2000);
                    System.out.println(Thread.currentThread().getName() + "用2s执行完一半任务,休息一下" + DateUtil.getTime());
                    condition.await();
                    System.out.println(Thread.currentThread().getName() + "继续执行完剩余一半任务" + DateUtil.getTime());
                } catch (Exception e) {

                } finally {
                    lock.unlock();
                }

            }
        }, "threadA");
        threadA.start();

        Thread threadB = new Thread(new Runnable() {
            @Override
            public void run() {
                lock.lock();
                try {
                    System.out.println(Thread.currentThread().getName() + "获取到锁," + DateUtil.getTime());
                    Thread.sleep(6000);
                    System.out.println(Thread.currentThread().getName() + "用6s执行完所有,休息" + DateUtil.getTime());
                    condition.signal();
                } catch (Exception e) {

                } finally {
                    lock.unlock();
                }
            }
        }, "threadB");
        threadB.start();

        Thread.sleep(3000);
        threadA.interrupt();
    }
}
