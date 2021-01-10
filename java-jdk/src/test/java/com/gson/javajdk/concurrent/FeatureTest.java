package com.gson.javajdk.concurrent;

import org.junit.Assert;
import org.junit.Test;

import java.util.concurrent.*;

public class FeatureTest extends Assert {

    @Test
    public void testCancel() throws ExecutionException, InterruptedException {
        ExecutorService service = Executors.newFixedThreadPool(1);
        Future<Integer> future3 = service.submit(new IntCallable<>(3));
        Future<Integer> future4 = service.submit(new IntCallable<>(4));

        Thread.sleep(500);
        future3.cancel(true);
        future4.get();
        assertTrue(future3.isCancelled());
        assertTrue(future3.isDone());
        assertFalse(future4.isCancelled());
        assertTrue(future4.isDone());
    }

    /**
     * get阻塞时，如果取消任务则抛出异常
     * @throws ExecutionException
     * @throws InterruptedException
     */
    @Test
    public void testGetWhenCancel() {
        ExecutorService service = Executors.newFixedThreadPool(1);
        Future<Integer> future3 = service.submit(new IntCallable<>(3));
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2*1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                future3.cancel(true);
            }
        }).start();
        try {
            future3.get();
            assertTrue(future3.isDone());
            assertTrue(future3.isCancelled());
        }catch (Exception e){
            assertTrue(e instanceof CancellationException);
        }
    }

    //超时未获取结果异常
    @Test
    public void testGetTimeout() throws ExecutionException, InterruptedException, TimeoutException {
        ExecutorService service = Executors.newFixedThreadPool(1);
        Future<Integer> future3 = service.submit(new IntCallable<>(3));
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2*1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                future3.cancel(true);
            }
        }).start();
        try {
            future3.get(1500, TimeUnit.MILLISECONDS);
            assertTrue(future3.isDone());
        }catch (Exception e){
            assertTrue(e instanceof TimeoutException);
        }
    }

    public class IntCallable<Integer> implements Callable<Integer> {
        private Integer element;

        public IntCallable(Integer element){
            this.element = element;
        }
        @Override
        public Integer call() throws Exception {
            System.out.println("开始执行,element=" + element);
            long begin = System.currentTimeMillis();
            while (System.currentTimeMillis() - begin < 3000){

            }
            System.out.println("结束执行,element=" + element);
            return element;
        }
    }
}
