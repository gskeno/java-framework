package com.gson.javajdk.lock;

import com.gson.javajdk.DateUtil;
import com.gson.javajdk.PrintUtils;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class AQSCondition1Test {
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
                    e.printStackTrace();
                } finally {
                    PrintUtils.printAqs(lock);
                    System.out.println(Thread.currentThread().getName() + "释放锁," + DateUtil.getTime());
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
                    Thread.sleep(20 * 1000);
                    System.out.println(Thread.currentThread().getName() + "用6s执行完所有,休息" + DateUtil.getTime());
                    condition.signal();
                } catch (Exception e) {

                } finally {
                    lock.unlock();
                }
            }
        }, "threadB");
        threadB.start();
        int time = 0;
        while (true){
            Thread.sleep(1000);
            time++;
            if (time == 3){
                threadA.interrupt();
                System.out.println("threadA发生中断," + DateUtil.getTime());
            }
            PrintUtils.printAqs(lock);
            PrintUtils.print(condition);
        }
    }
}
