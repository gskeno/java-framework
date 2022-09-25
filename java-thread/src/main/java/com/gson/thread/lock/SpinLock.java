package com.gsonkeno.jdk.training.concurrent.lock;

import java.util.concurrent.atomic.AtomicReference;

/**
 * http://ifeve.com/java_lock_see4/
 * 可重入锁
 * Created by gaosong on 2018-03-23
 *
 * 1、若有同一线程两调用lock() ，会导致第二次调用lock位置进行自旋，产生了死锁
 * 说明这个锁并不是可重入的。（在lock函数内，应验证线程是否为已经获得锁的线程）
 * 2、若1问题已经解决，当unlock（）第一次调用时，就已经将锁释放了。实际上不应释放锁。
 *（采用计数次进行统计）
 */
public class SpinLock {

    private AtomicReference<Thread> owner =new AtomicReference<>();
    public void lock(){
        Thread current = Thread.currentThread();

        System.out.println("指代的线程初始值:" + owner.get());
        while(!owner.compareAndSet(null, current)){
            System.out.println("lock自旋中");
        }
        System.out.println("lock自旋结束");
    }

    public void unlock (){
        Thread current = Thread.currentThread();

        owner.compareAndSet(current, null);
    }

}
