package com.gson.interview.concurrentprogrammingart.chapter9;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.concurrent.*;

/**
 * 线程池的处理任务的流程,各个参数意义 以及使用方式
 * @author gaosong
 * @since 2019-03-30
 */
public class ThreadPoolExecitorUse {
    public static void main(String[] args) throws NoSuchFieldException {
        ArrayBlockingQueue<Runnable> abq = new ArrayBlockingQueue<Runnable>(5);
        //线程工厂，这里使用guava提供的builder创建，每次创建一个线程，线程名中的序号加1
        ThreadFactory factory = new ThreadFactoryBuilder().setNameFormat("XX-task-%d").build();

        //自定义的饱和策略
        RejectedExecutionHandler handler = new RejectedExecutionHandler() {
            @Override
            public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
                System.out.println("打印日志，任务r" + r + "被丢弃");
            }
        };

        //ThreadPoolExecutor内置了四种饱和策略，
        //1. DiscardPolicy 直接丢弃，实现RejectedExecutionHandler接口的方式是用空方法实现了抽象方法
        //2. DiscardOldestPolicy 丢弃最老的任务，最老的任务肯定排在队列的开头，因为它最先进入队列
        //        public void rejectedExecution(Runnable r, ThreadPoolExecutor e) {
        //            if (!e.isShutdown()) {
        //                e.getQueue().poll();  //移除队列的首部元素，即最早的任务。可以考虑下，首部空了，后面新的任务是如何再加入队列里的
        //                e.execute(r);//执行新加入的任务
        //            }
        //        }
        //3. AbortPolicy抛出异常
        //4. CallerRunsPolicy,使用当前调用者线程执行
        //           public void rejectedExecution(Runnable r, ThreadPoolExecutor e) {
        //            if (!e.isShutdown()) {
        //                r.run();
        //            }
        //        }



        ThreadPoolExecutor pool = new ThreadPoolExecutor(2,5,
                5, TimeUnit.SECONDS,abq,factory, handler);
        Field workers = getField("workers");
        try {
            System.out.println("time:" + getNow());
            int jobNo = 0;
            while (jobNo < 11){
                pool.execute(new Job(jobNo++, 3000));
                Thread.sleep(100);
                System.out.println("fieldWorkers :" +  ((HashSet)(workers.get(pool))).size() );
            }
            System.out.println("activeCount " + pool.getActiveCount());

            //因为任务队列的长度为5，所以前5个任务跑完需要3s，后5个任务跑完需要3s，总共需要6s
            //5s内没有任务，所以活着的5个worker转化为2个(核心线程数)
            //再等5s，提交下面这个任务时，可以debug观察到只有两个worker了
            Thread.sleep(16*1000);
            System.out.println("activeCount " + pool.getActiveCount());
            System.out.println("fieldWorkers :" +  ((HashSet)(workers.get(pool))).size() );

            pool.execute(new Job(jobNo++, 3000));
            Thread.sleep(1 * 1000);
            System.out.println("activeCount " + pool.getActiveCount());
            System.out.println("fieldWorkers :" +  ((HashSet)(workers.get(pool))).size() );



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
        return str;
    }


}
