package com.gsonkeno.jdk.training.concurrent;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by gaosong on 2018-03-31
 */
public class CycleLock  {

    private static Lock lockA = new ReentrantLock();
    private static Lock lockB = new ReentrantLock();
    private static Lock lockC = new ReentrantLock();

    public static void main(String[] args) {
        try {
            lockA.lock();
            System.out.println("获取A锁");
            try {
                lockB.lock();
                System.out.println("获取B锁");
                lockA.unlock();
                System.out.println("释放A锁");
                try {
                    lockC.lock();
                    System.out.println("获取C锁");
                    lockB.unlock();
                    System.out.println("释放B锁");
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    lockC.unlock();
                    System.out.println("释放C锁");
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        }

    }
}
