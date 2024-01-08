package com.gson.javattl;

import com.alibaba.ttl.TransmittableThreadLocal;
import com.alibaba.ttl.TtlCallable;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicLong;

/**
 * https://github.com/alibaba/transmittable-thread-local
 */
public class SimpleSample {

    public static void main(String[] args) throws InterruptedException {
        AtomicLong atomic = new AtomicLong(0);
        ExecutorService executorService = new ThreadPoolExecutor(30, 30, 10L,
                TimeUnit.SECONDS, new LinkedBlockingDeque<>(200), new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                Thread thread = new Thread(r, "simpleSample pool, thread id " + atomic.getAndAdd(1));
                return thread;
            }
        }, new RejectedExecutionHandler() {
            @Override
            public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
                System.out.println("reject runnable " + r);
            }
        });

        TransmittableThreadLocal<String> context = new TransmittableThreadLocal<>();
        // =====================================================

        // 在父线程中设置
        context.set("value-set-in-parent");

        Callable call = new Callable() {
            @Override
            public Object call() throws Exception {
                String value = null;
                for (int i = 0; i < 3; i++) {
                    value = context.get();
                    System.out.println("child thread value:" + value);
                    Thread.sleep(1000);
                    context.set("value-modify-in-child" + i);
                }
                return value;
            }
        };
        // 额外的处理，生成修饰了的对象ttlCallable
        Callable ttlCallable = TtlCallable.get(call);
        executorService.submit(ttlCallable);

        // =====================================================
        Thread.sleep(1000);
        context.set("value-modify-in-parent");
        for (int i = 0; i < 3; i++) {
            String parentValue = context.get();
            System.out.println("parentValue=" + parentValue);
        }

    }
}
