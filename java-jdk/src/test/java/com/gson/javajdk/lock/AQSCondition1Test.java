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

    /**
     *
     * A线程await后，B线程获取到锁，B线程signal之前，使A线程中断，B线程释放锁
     *
     * 中断发生在signal之前，当A线程获取到B锁，await方法会抛出异常,await方法后的代码无法执行到
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        Thread threadA = new Thread(new Runnable() {
            @Override
            public void run() {
                lock.lock();
                try {
                    System.out.println(Thread.currentThread().getName() + "获取到锁," + DateUtil.getTime());
                    Thread.sleep(2000);
                    System.out.println(Thread.currentThread().getName() + "用2s执行完一半任务,await一下" + DateUtil.getTime());
                    condition.await();
                    System.out.println(Thread.currentThread().getName() + "继续执行完剩余一半任务" + DateUtil.getTime());
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    PrintUtils.printAqs(lock);
                    PrintUtils.print(condition);
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
                    System.out.println(Thread.currentThread().getName() + ",获取到锁,开始睡眠6s," + DateUtil.getTime());
                    Thread.sleep(6 * 1000);
                    System.out.println(Thread.currentThread().getName() + ",睡眠6s结束，开始调用signal," + DateUtil.getTime());
                    condition.signal();
                    Thread.sleep(500);
                    System.out.println(Thread.currentThread().getName() + ",调用signal后," + DateUtil.getTime());
                    PrintUtils.printAqs(lock);
                    PrintUtils.print(condition);
                } catch (Exception e) {
                    e.printStackTrace();
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
            if (time == 4){
                threadA.interrupt();
                System.out.println("threadA在threadB的signal调用之前发生中断," + DateUtil.getTime());
            }
            PrintUtils.printAqs(lock);
            PrintUtils.print(condition);
        }
    }
}
