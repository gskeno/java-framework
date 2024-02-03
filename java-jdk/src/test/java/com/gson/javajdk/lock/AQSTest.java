package com.gson.javajdk.lock;

import org.junit.Test;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.concurrent.locks.ReentrantLock;

public class AQSTest {
    public static ReentrantLock reentrantLock = new ReentrantLock(true);

    @Test
    public void testLock() throws InterruptedException {
        new Thread(new Runnable() {
            @Override
            public void run() {
                reentrantLock.lock();
                try {
                    Thread.sleep(1000 * 60 * 4);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }                System.out.println(Thread.currentThread().getName() + "获取到锁");
                reentrantLock.unlock();
            }
        }, "thread-A").start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                reentrantLock.lock();
                try {
                    Thread.sleep(1000 * 60 * 5);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println(Thread.currentThread().getName() + "获取到锁");
                reentrantLock.unlock();
            }
        }, "thread-B").start();

        while (true){
            Thread.sleep(3000);
            System.out.println(reentrantLock.getQueueLength());
        }
    }

    public static void main(String[] args) throws InterruptedException {
        new Thread(new Runnable() {
            @Override
            public void run() {
                reentrantLock.lock();
                System.out.println(Thread.currentThread().getName() + "获取到锁");
                try {
                    Thread.sleep(1000 * 2);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println(Thread.currentThread().getName() + "执行完任务");
                reentrantLock.unlock();
            }
        }, "thread-A").start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                reentrantLock.lock();
                System.out.println(Thread.currentThread().getName() + "获取到锁");
                try {
                    Thread.sleep(1000 * 4);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println(Thread.currentThread().getName() + "执行完任务");
                reentrantLock.unlock();
            }
        },"thread-B").start();
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                reentrantLock.lock();
                System.out.println(Thread.currentThread().getName() + "获取到锁");
                try {
                    Thread.sleep(1000 * 6);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println(Thread.currentThread().getName() + "执行完任务");
                reentrantLock.unlock();
            }
        }, "thread-C");
        thread.start();
        while (true){
            Field syncField = ReflectionUtils.findField(ReentrantLock.class, "sync");
            ReflectionUtils.makeAccessible(syncField);
            Object fairSync = ReflectionUtils.getField(syncField, reentrantLock);

            Field headField = ReflectionUtils.findField(fairSync.getClass(), "head");
            ReflectionUtils.makeAccessible(headField);
            Object headNode = ReflectionUtils.getField(headField, fairSync);
            printNode(headNode);
            Thread.sleep(1000);
        }

    }

    public static void printNode(Object node){
        String nodeInfo = getNodeInfo(node);
        System.out.println(nodeInfo);
    }

    public static String getNodeInfo(Object node){
        if (node == null){
            return null;
        }
//        Field hashCodeField = ReflectionUtils.findField(node.getClass(), "hashCode");
////        ReflectionUtils.makeAccessible(hashCodeField);
//        Object hashCode = ReflectionUtils.getField(hashCodeField, node);

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
