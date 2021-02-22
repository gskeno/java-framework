package com.gson.guava;

import com.google.common.util.concurrent.AbstractFuture;
import com.google.common.util.concurrent.Futures;
import org.junit.Test;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class AbstractFutureTest {

    @Test
    public void test() throws ExecutionException, InterruptedException {
        Executor executor= Executors.newFixedThreadPool(1);
        DefaultListenableFuture<String> lotteryFuture = new DefaultListenableFuture();
        lotteryFuture.addListener(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread() + " listener called");
            }
        }, executor);

        executor.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                }
                lotteryFuture.set("pdd lottery will overhead 300");
            }
        });
        String predict = lotteryFuture.get();
        System.out.println(Thread.currentThread() + " predict=" + predict);
    }
}
