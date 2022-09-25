package com.gsonkeno.jdk.training.concurrent.lock;

import java.util.concurrent.atomic.AtomicReference;

/**
 * 相对于SpinLock而言，引入了计数次数，和判断当前线程是否是原子引用类中包裹的线程
 * 来解决无法重入锁的问题
 * Created by gaosong on 2018-03-23
 */
public class SpinLock1 {
    private AtomicReference<Thread> reference = new AtomicReference<>();
    private int count;

    public void lock(){
        Thread current = Thread.currentThread();

        if (reference.get() == current){
            count ++;
            return;
        }

        while (!reference.compareAndSet(null, current)){

        }
    }

    public void unlock(){
        Thread current = Thread.currentThread();

        if (reference.get() == current){
            if (count != 0){
                count--;
            }else{
                reference.compareAndSet(current,null);
            }
        }
    }
}
