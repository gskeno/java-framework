package com.gsonkeno.jdk.training.concurrent;

import com.gsonkeno.jdk.training.concurrent.lock.SpinLock;
import org.junit.Test;

/**
 * http://ifeve.com/java_lock_see4/
 * 可重入锁
 * Created by gaosong on 2018-03-23
 */
public class SpinLockTest {
    @Test
    public void test(){
        SpinLock spinLock = new SpinLock();
        try {
            spinLock.lock();
            spinLock.lock();
            System.out.println("hs");
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            spinLock.unlock();
        }
    }
}
