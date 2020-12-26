package com.gson.algo.linklist;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.concurrent.locks.Lock;

/**
 * 先获取A锁，再获取B锁，
 * 当获取到B锁后，释放A锁再去获取C锁
 * 当获取到C锁后，释放B锁再去获取D锁
 * ...
 * 当获取到Y锁后，释放X锁再去获取Z锁
 */
public class ChainLock {
    public static void loopGetLock(LinkedList<Lock> link) {
        if (link == null || link.size() == 0) {
            return;
        }

        Iterator<Lock> iterator = link.iterator();
        Lock lock = iterator.next();
        iterator.remove();
        loopGetLock(lock, iterator, false);
    }

    public static void loopGetLock(Lock lock, Iterator<Lock> iterator, boolean hasLocked) {
        if (lock == null){
            return;
        }
        Lock next = null;
        StringBuilder sb = new StringBuilder();
        if (!hasLocked){
            lock.lock();
            sb.append(" 获取锁" + Integer.toHexString(lock.hashCode()));
        }
        try {
            if (iterator.hasNext()) {
                next = iterator.next();
            } else {
                return;
            }
            next.lock();
            sb.append(" 获取锁" + Integer.toHexString(next.hashCode()));
        } finally {
            lock.unlock();
            sb.append(" 释放锁" + Integer.toHexString(lock.hashCode()));
            if (next != null){
                iterator.remove();
            }
            System.out.println(sb.toString());
            loopGetLock(next, iterator, true);
        }
    }
}
