package com.gsonkeno.jdk.training.concurrent.executor;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created by gaosong on 2017-09-13
 */
public class FutureTaskUse {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Task task = new Task();

        ExecutorService service = Executors.newFixedThreadPool(1);
        Future<Integer> future = service.submit(task);

        System.out.println("Thread " + Thread.currentThread().getName() + " is running");

        System.out.println(future.get());
    }
}
