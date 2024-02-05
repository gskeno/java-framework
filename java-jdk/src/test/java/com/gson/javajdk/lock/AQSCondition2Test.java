package com.gson.javajdk.lock;

import com.gson.javajdk.DateUtil;
import com.gson.javajdk.PrintUtils;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class AQSCondition2Test {
    static ReentrantLock lock = new ReentrantLock(true);
    static Condition condition = lock.newCondition();

    /**
     *
     * A线程await后，B线程获取到锁，B线程signal后，使A线程中断，B线程释放锁
     * A线程await返回，表示再次获取到锁
     *
     * 整个过程虽然发生了中断，但是中断发生在signal之后，所以A线程能够继续完成lock/unlock的整套动作
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        Thread threadA = new Thread(new Runnable() {
            @Override
            public void run() {
                lock.lock();
                try {
                    System.out.println(Thread.currentThread().getName() + "获取到锁,开始休息2s," + DateUtil.getTime());
                    Thread.sleep(2000);
                    System.out.println(Thread.currentThread().getName() + "用2s执行完一半任务,await休息一下," + DateUtil.getTime());
                    condition.await();
                    System.out.println(Thread.currentThread().getName() + "从await返回,开始释放锁," + DateUtil.getTime());
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
                    System.out.println("  " + Thread.currentThread().getName() + "获取到锁,开始休息6s" + DateUtil.getTime());
                    Thread.sleep(6 * 1000);
                    System.out.println("  " + Thread.currentThread().getName() + "signal一下,然后休息3s," + DateUtil.getTime());
                    condition.signal();
                    Thread.sleep(3 * 1000);
                    System.out.println("  " + Thread.currentThread().getName() + "休息3s完毕，开始释放锁," + DateUtil.getTime());
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
            if (time == 9){
                threadA.interrupt();
                System.out.println("threadA发生中断,发生在threadB-signal之后" + DateUtil.getTime());
            }
            PrintUtils.printAqs(lock);
            print(condition);
        }
    }

    public static void print(Condition condition){
        Field firstWaiterField = ReflectionUtils.findField(AbstractQueuedSynchronizer.ConditionObject.class, "firstWaiter");
        ReflectionUtils.makeAccessible(firstWaiterField);
        Object firstWaiter = ReflectionUtils.getField(firstWaiterField, condition);
        String firstWaiterInfo = PrintUtils.getNodeInfo(firstWaiter);
        System.out.println("WaitingQueue:" + firstWaiterInfo + "," + DateUtil.getTime());
    }


}
