package com.gson.guava.concurrency;

import com.google.common.util.concurrent.*;
import org.junit.Test;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ConcurrencyTest {
    @Test
    public void testListenableFuture(){
        ExecutorService service = Executors.newFixedThreadPool(10);
        // ExecutorService--->经过包装---> ListeningExecutorService
        ListeningExecutorService listeningExecutorService = MoreExecutors.listeningDecorator(service);

        // 参数1: 任务  参数2:任务执行完成后返回结果的处理 参数3:执行器
        ListenableFuture<String> listenableFuture = listeningExecutorService.submit(new Callable<String>() {
            @Override
            public String call(){
                return "得到结果" + 100/0;
            }
        });
        Futures.addCallback(listenableFuture, new FutureCallback<String>() {
            @Override
            public void onSuccess(String result) {
                System.out.println("success result " + result);
            }

            @Override
            public void onFailure(Throwable t) {
                System.out.println("failure result " + t);
            }
        }, listeningExecutorService);
    }
}
