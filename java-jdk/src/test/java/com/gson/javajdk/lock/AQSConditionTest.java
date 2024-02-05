package com.gson.javajdk.lock;

import com.gson.javajdk.DateUtil;
import com.gson.javajdk.PrintUtils;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
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
                    System.out.println(Thread.currentThread().getName() + "获取到锁,开始睡眠2s," + DateUtil.getTime());
                    Thread.sleep(2000);
                    System.out.println(Thread.currentThread().getName() + "睡眠2s结束，开始await," + DateUtil.getTime());
                    condition.await();
                    System.out.println(Thread.currentThread().getName() + "从await返回，继续睡眠2s," + DateUtil.getTime());
                    Thread.sleep(2000);
                    System.out.println(Thread.currentThread().getName() + "继续睡眠2s结束,释放锁" + DateUtil.getTime());
                } catch (Exception e) {
                    e.printStackTrace();
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
                    System.out.println("---" + Thread.currentThread().getName() + "获取到锁,开始睡眠6s," + DateUtil.getTime());
                    Thread.sleep(6000);
                    System.out.println("---" + Thread.currentThread().getName() + "睡眠6结束,开始signal," + DateUtil.getTime());
                    condition.signal();
                    System.out.println("---" + Thread.currentThread().getName() + "signal结束,再睡眠2s," + DateUtil.getTime());
                    Thread.sleep(2000);
                    System.out.println("---" + Thread.currentThread().getName() + "再睡眠2s结束，释放锁，" + DateUtil.getTime());
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            }
        }, "threadB");
        threadB.start();
        while (true){
            Thread.sleep(1000);
            PrintUtils.printAqs(lock);
            PrintUtils.print(condition);        }
    }
}
