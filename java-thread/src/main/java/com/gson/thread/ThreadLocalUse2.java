package com.gsonkeno.jdk.training.concurrent;

import java.util.concurrent.TimeUnit;

/**
 * Created by gaosong on 2017-08-31
 */
public class ThreadLocalUse2 {
    private static ThreadLocal<Long> local = new ThreadLocal<Long>(){
        @Override
        protected Long initialValue() {
            return System.currentTimeMillis();
        }
    };

    public static void begin(){
        local.set(System.currentTimeMillis());
    }

    public static long end(){
        return System.currentTimeMillis() - local.get();
    }

    public static void main(String[] args) {
        ThreadLocalUse2.begin();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(ThreadLocalUse2.end());
    }
}
