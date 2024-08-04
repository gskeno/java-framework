package com.gson.guava;

import com.google.common.util.concurrent.RateLimiter;
import org.junit.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class RateLimiterTest {
    @Test
    public void testSmoothBurstyUse() throws InterruptedException {
        // 每秒可以通过5个请求，也即每秒放入5个令牌
        RateLimiter rateLimiter = RateLimiter.create(5);
        // 这里睡眠2s，令牌桶里也不会有10个令牌；
        // 因为框架中 maxBurstSeconds 强制设置为1，表示令牌桶只会保留1s的令牌数即5个
        // 令牌桶容量就是5
        Thread.sleep(2000);

        int requests = 20;
        CountDownLatch countDownLatch = new CountDownLatch(1);
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                try {
                    countDownLatch.await();
                    // 前6个请求耗时都为0
                    // 前5个请求耗时为0，是因为桶里有5个令牌
                    // 第6个请求耗时为0，是因为"预支"原理，先将令牌预先取到，不耗时；
                    // 由于平均0.2s才生产1个令牌，所以下次请求获取令牌需要等待0.2s
                    System.out.println(Thread.currentThread().getName() + " 请求耗时:" + rateLimiter.acquire(1));
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        };
        ExecutorService service = Executors.newFixedThreadPool(requests);
        for (int i = 0; i < requests; i++) {
            service.submit(runnable);
        }
        Thread.sleep(1000);
        countDownLatch.countDown();
        service.awaitTermination(60, TimeUnit.SECONDS);
    }

    @Test
    public void testWarmUpRateLimiter() throws InterruptedException {
        // 每秒生成5个令牌，预热期设置为2s(即2s后令牌生成速度变为0.2s生成一个，在此之前，生成速度更慢) ，预热期令牌生成速度会低于稳定期
        RateLimiter rateLimiter = RateLimiter.create(5, 2, TimeUnit.SECONDS);
        for (int i = 0; i < 20; i++) {
            //0.0
            //0.553163   开始时获取令牌耗时最长，随后逐渐减小
            //0.476724
            //0.396308
            //0.319991
            //0.237281
            //0.198168    2s后，获取令牌耗时 趋于稳定
            //0.195875
            //0.196701
            //...
            System.out.println(rateLimiter.acquire(1));
        }
    }
}
