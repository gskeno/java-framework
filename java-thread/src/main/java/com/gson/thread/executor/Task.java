package com.gsonkeno.jdk.training.concurrent.executor;

import java.util.concurrent.Callable;

/**
 * Created by gaosong on 2017-09-13
 */
public class Task implements Callable<Integer> {
    @Override
    public Integer call() throws Exception {
        System.out.println("Thread [" + Thread.currentThread().getName() + "] is running");
        int result = 0;
        for(int i = 0; i < 100;++i) {
            result += i;
        }

        Thread.sleep(3000);
        return result;
    }
}
