package com.gson.thread;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**线程池饱和策略;
 * 当线程池中所有的线程正在工作，且线程数达到线程池允许最大值时
 * (最大值是允许并发执行的线程数最大值 + 等待队列长度，详见Exectors.newFixedThreadPool(int nThreads)函数)
 *
 *  见:http://blog.csdn.net/lixwjava/article/details/51813032
 * Created by gaosong on 2017-07-16.
 */
public class SaturationPoicy {
    /**
     * 线程池工作队列已满时，在不同饱和策略下表现
     * @param handler 线程池工作队列饱和策略
     */
    public static void policy(RejectedExecutionHandler handler){
        //基本线程2个，最大线程数为3，工作队列容量为5
        ThreadPoolExecutor exec = new ThreadPoolExecutor(2,3,0l, TimeUnit.MILLISECONDS,new LinkedBlockingDeque<>(5));
        if (handler != null){
            exec.setRejectedExecutionHandler(handler);//设置饱和策略
        }
        for (int i = 0; i < 10; i++) {
            exec.submit(new Task());//提交任务
        }
        exec.shutdown();
    }

    public static void main(String[] args) {
        System.out.println("开始执行时间" + System.currentTimeMillis()/1000 + "秒");
 //         policy(new ThreadPoolExecutor.AbortPolicy());
//          policy((new ThreadPoolExecutor.CallerRunsPolicy()));
 //       policy(new ThreadPoolExecutor.DiscardPolicy());
        policy(new ThreadPoolExecutor.DiscardOldestPolicy());
    }

    //自定义任务
    static class Task implements Runnable {
        private static int count = 0;
        private int id = 0;//任务标识
        public Task() {
            id = ++count;
        }
        @Override
        public  void run() {
            try {
                TimeUnit.SECONDS.sleep(7);//休眠3秒
            } catch (InterruptedException e) {
                System.err.println("线程被中断" + e.getMessage());
            }
            System.out.println(" 任务：" + id + "\t 工作线程: "+
                    Thread.currentThread().getName() + " 执行完毕"
                    + "\t 时刻" + System.currentTimeMillis()/1000 + "秒");
        }
    }
}
