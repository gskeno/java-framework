package com.gson.algo.linklist;

import org.junit.Test;

import java.util.LinkedList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ChainLockTest {
    @Test
    public void testChainLock(){
        LinkedList<Lock> locks = new LinkedList<Lock>();
        for (int i = 0; i <10 ; i++) {
            ReentrantLock lock = new ReentrantLock();
            locks.add(lock);
        }
        ChainLock.loopGetLock(locks);
    }
}
