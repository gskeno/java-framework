package com.gson.javajdk.concurrent;

import org.junit.Test;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;

public class ForkJoinTest {
    @Test
    public void testForkJoin() throws ExecutionException, InterruptedException {
        ForkJoinPool pool = new ForkJoinPool();
        ForkJoinTask<Long> result = pool.submit(new CountTask(0, 101L, 1));
        while (true){

        }
//        Long sum = result.get();
//        System.out.println(sum);
    }

    @Test
    public void test(){
        System.out.println(0x7fff);
        System.out.println(Runtime.getRuntime().availableProcessors());
    }
}
