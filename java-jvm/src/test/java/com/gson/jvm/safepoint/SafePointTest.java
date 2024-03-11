package com.gson.jvm.safepoint;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * https://mp.weixin.qq.com/s/_Khj3Qa2MZBN9718KhVp0w
 * -XX:+PrintSafepointStatistics
 */
public class SafePointTest {

    public static AtomicInteger counter = new AtomicInteger(0);

    public static void main(String[] args) throws Exception {
        long startTime = System.currentTimeMillis();
        Runnable runnable = () -> {
            System.out.println(interval(startTime) + "ms后，" + Thread.currentThread().getName() + "子线程开始运行");
            // 注意, int 与 long的区别
            for (long i = 0; i < 100000000; i++) {
                counter.getAndAdd(1);
            }
            System.out.println(interval(startTime) + "ms后，" + Thread.currentThread().getName() + "子线程结束运行, counter=" + counter);
        };

        Thread t1 = new Thread(runnable, "zz-t1");
        Thread t2 = new Thread(runnable, "zz-t2");

        t1.start();
        t2.start();

        System.out.println(interval(startTime) + "ms后，主线程开始sleep.");

        Thread.sleep(1000L);

        System.out.println(interval(startTime) + "ms后，主线程结束sleep.");
        System.out.println(interval(startTime) + "ms后，主线程结束，counter:" + counter);
    }

    private static long interval(Long startTime) {
        return System.currentTimeMillis() - startTime;
    }

}
