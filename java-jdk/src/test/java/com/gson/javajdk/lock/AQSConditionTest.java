package com.gson.javajdk.lock;

import com.gson.javajdk.DateUtil;
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
        while (true){
            Thread.sleep(1000);
            print(condition);
        }
    }

    public static void print(Condition condition){
        Field firstWaiterField = ReflectionUtils.findField(AbstractQueuedSynchronizer.ConditionObject.class, "firstWaiter");
        ReflectionUtils.makeAccessible(firstWaiterField);
        Object firstWaiter = ReflectionUtils.getField(firstWaiterField, condition);
        String firstWaiterInfo = getNodeInfo(firstWaiter);
        System.out.println(firstWaiterInfo + "," + DateUtil.getTime());
    }

    public static String getNodeInfo(Object node){
        if (node == null){
            return null;
        }
        Field threadField = ReflectionUtils.findField(node.getClass(), "thread");
        ReflectionUtils.makeAccessible(threadField);
        Thread threadObj = (Thread)ReflectionUtils.getField(threadField, node);
        String threadName = threadObj != null ? threadObj.getName() : null;

        Field waitStatusField = ReflectionUtils.findField(node.getClass(), "waitStatus");
        ReflectionUtils.makeAccessible(waitStatusField);
        Object waitStatus = ReflectionUtils.getField(waitStatusField, node);

        Field nextField = ReflectionUtils.findField(node.getClass(), "next");
        ReflectionUtils.makeAccessible(nextField);
        Object next = ReflectionUtils.getField(nextField, node);
        String nextNodeInfo = getNodeInfo(next);
        return "[hasCode=" + node.hashCode() + ",threadName=" + threadName + ",waitStatus=" + waitStatus + ",nextNode=" + nextNodeInfo + "]";
    }
}
