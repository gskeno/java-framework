package com.gson.javajdk;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * 互斥锁，
 * 同一时间只能有一个线程获取到锁
 */
public class MutexLock implements Lock {
    private static Sync sync = new Sync();

    private static class Sync extends AbstractQueuedSynchronizer {
        private static final long serialVersionUID = -9062448839908589231L;

        /**
         * state = 1 表示锁被当前线程占用
         *
         * @return
         */
        @Override
        protected boolean isHeldExclusively() {
            return getState() == 1;
        }

        //即时返回
        @Override
        protected boolean tryAcquire(int arg) {

            if (compareAndSetState(0, 1)) {
                setExclusiveOwnerThread(Thread.currentThread());
                //获取到锁
                return true;
            }
            return false;
        }

        @Override
        protected boolean tryRelease(int arg) {
            if (compareAndSetState(1, 0)) {
                setExclusiveOwnerThread(null);
                return true;
            }
            return false;
        }

        protected Condition newCondition() {
            return new ConditionObject();
        }
    }

    /**
     * 全部代理给同步器sync处理
     **/

    @Override
    public void lock() {
        sync.acquire(1);
    }


    @Override
    public void lockInterruptibly() throws InterruptedException {
        sync.acquireInterruptibly(1);
    }

    @Override
    public boolean tryLock() {
        return sync.tryAcquire(1);
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return sync.tryAcquireNanos(1, unit.toNanos(time));
    }


    @Override
    public void unlock() {
        sync.release(1);
    }

    @Override
    public Condition newCondition() {
        return sync.newCondition();
    }
}
