package com.gson.javajdk.concurrent;

import com.gson.javajdk.TestUtils;
import org.junit.Test;

import java.util.concurrent.*;

public class DelayQueueTest {
    @Test
    public void testScheduleTask() throws InterruptedException {
        ScheduledThreadPoolExecutor service = new ScheduledThreadPoolExecutor(5);
        for (int i = 0; i < 10; i++) {
            int finalI = i;
            service.schedule(new Runnable() {
                @Override
                public void run() {
                    //十个任务，每个任务执行时间相差1s
                    System.out.println("i=" + finalI +
                            ", and thread=" +Thread.currentThread().getName() +
                            ",time=" + TestUtils.getTime());
                }
            }, i * 1000, TimeUnit.MILLISECONDS);
        }

        Thread.sleep(20*1000);
    }
}
