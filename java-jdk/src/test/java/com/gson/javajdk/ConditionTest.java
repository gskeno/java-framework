package com.gson.javajdk;

import org.junit.Test;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ConditionTest {

    private Lock lock = new ReentrantLock();
    private Condition condition = lock.newCondition();

    @Test
    public void test() throws InterruptedException {
        new Thread(new Runnable() {
            @Override
            public void run() {
                lock.lock();
                try {
                    condition.await();
                }catch (Exception e){
                }finally{
                    lock.unlock();
                }
            }
        },"thread1").start();

        Thread.sleep(5*1000);

        new Thread(new Runnable() {
            @Override
            public void run() {
                lock.lock();
                try {
                    condition.signal();
                }finally {
                    lock.unlock();
                }
            }
        },"thread2").start();

        while (true){

        }
    }

}
