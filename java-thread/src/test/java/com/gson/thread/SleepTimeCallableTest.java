package com.gson.thread;

import org.junit.Test;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class SleepTimeCallableTest {

    @Test
    public void test() throws ExecutionException, InterruptedException {
        ExecutorService pool = Executors.newFixedThreadPool(1);
        Future<Integer> future1 = pool.submit(new SleepTimeCallable("task1"));
        Future<Integer> future2 = pool.submit(new SleepTimeCallable("task2"));
        Thread.sleep(500);
        future1.cancel(true);
        future2.get();
    }
}
