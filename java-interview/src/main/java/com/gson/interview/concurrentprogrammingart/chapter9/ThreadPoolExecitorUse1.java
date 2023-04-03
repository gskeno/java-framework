package com.gson.interview.concurrentprogrammingart.chapter9;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.concurrent.*;

public class ThreadPoolExecitorUse1 {
    public static void main(String[] args) throws NoSuchFieldException {
        // 阻塞队列
        ArrayBlockingQueue<Runnable> abq = new ArrayBlockingQueue<Runnable>(3);
        // 线程工厂，这里使用guava提供的builder创建，每次创建一个线程，线程名中的序号加1
        ThreadFactory factory = new ThreadFactoryBuilder().setNameFormat("XX-task-%d").build();
        // 自定义的饱和策略
        RejectedExecutionHandler handler = new RejectedExecutionHandler() {
            @Override
            public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
                System.out.println("打印日志，任务r" + r + "被丢弃");
            }
        };

        ThreadPoolExecutor pool = new ThreadPoolExecutor(2,5,
                5, TimeUnit.SECONDS,abq,factory, handler);
        // 5s内没有任务，2个核心线程也会消亡
        pool.allowCoreThreadTimeOut(true);

        Field workers = getField("workers");
        try {
            System.out.println("time:" + getNow());
            int jobNo = 0;
            System.out.println("fieldWorkers :" +  ((HashSet)(workers.get(pool))).size()  + getNow());
            pool.execute(new Job(jobNo++, 1000));
            Thread.sleep(200);
            System.out.println("fieldWorkers :" +  ((HashSet)(workers.get(pool))).size() + getNow());

            System.out.println("activeCount " + pool.getActiveCount() + getNow());
            Thread.sleep(7000);
            System.out.println("fieldWorkers :" +  ((HashSet)(workers.get(pool))).size() + getNow());
            Thread.sleep(7000);
            System.out.println("fieldWorkers :" +  ((HashSet)(workers.get(pool))).size() + getNow());

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static Field getField(String fieldName) throws NoSuchFieldException {
        Field field = ThreadPoolExecutor.class.getDeclaredField(fieldName);
        field.setAccessible(true);
        return field;
    }

    public static String getNow(){
        LocalDateTime ldtt=LocalDateTime.now();
        DateTimeFormatter format=DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String str=ldtt.format(format);
        return "  " + str;
    }

}
