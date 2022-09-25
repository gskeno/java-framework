package com.gsonkeno.jdk.training.concurrent;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by gaosong on 2017-12-01
 * 当线程处在obj的wait()方法时；或者处在当前线程类的sleep(),join()方法时
 * 对其中断会使其抛出 中断异常
 */
public class InterruptTest {
    private static  Object lock = new Object();

    public static void main(String[] args) {
        System.out.println("时间:" +  new SimpleDateFormat("HH:mm:ss").format(new Date()));
        Thread thread = new Thread(new NormalThread(), "正常线程");
        thread.start();
        SleepUtil.sleep(2);
        thread.interrupt();

    }

    static class NormalThread implements Runnable{
        @Override
        public void run() {
            synchronized (lock){
                System.out.println(Thread.currentThread() + " is interrupted " + Thread.currentThread().isInterrupted());
                SleepUtil.sleep(5);
                System.out.println(Thread.currentThread() + " is interrupted " + Thread.currentThread().isInterrupted());
            }
            System.out.println(Thread.currentThread() + "退出锁" );
            System.out.println("时间:" +  new SimpleDateFormat("HH:mm:ss").format(new Date()));
        }
    }
}
