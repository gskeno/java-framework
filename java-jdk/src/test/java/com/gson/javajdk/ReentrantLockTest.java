package com.gson.javajdk;

import org.junit.Test;

import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;


public class ReentrantLockTest {
    @Test
    public void test() throws InterruptedException {
        ReentrantReadWriteLock readWriteLock = new ReentrantReadWriteLock(true);
        ReentrantReadWriteLock.ReadLock readLock = readWriteLock.readLock();
        ReentrantReadWriteLock.WriteLock writeLock = readWriteLock.writeLock();
        readLock.lock();
        Thread.sleep(3*1000);
        readLock.lock();
        Thread.sleep(1*1000);
        readLock.lock();
        Thread.sleep(1*1000);
        readLock.lock();
        Thread.sleep(1*1000);
        readLock.lock();
        Thread.sleep(1*1000);
        readLock.lock();

        readLock.unlock();

    }
}
