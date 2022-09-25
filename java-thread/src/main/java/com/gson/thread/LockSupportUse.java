package com.gsonkeno.jdk.training.concurrent;

import java.util.concurrent.locks.LockSupport;

/**
 * Created by gaosong on 2017-09-14
 * LockSupport.park()阻塞当前线程使其进入waiting状态
 *
 * LockSupport.unpark(Thread t)使线程t恢复到运行状态，相当于给线程t颁发一个许可证
 */
public class LockSupportUse {
    public static void main(String[] args) {
        testParkThenInterrupt();
    }

    public static void testParkThenInterrupt(){
        Thread thread = new Thread(new LockSupportThread(), "LockSupportThread");

        thread.start();

        SleepUtil.sleep(2);
        //充分运行线程LockSupportThread两秒钟后，中断该线程，
        //该线程能从park()方法返回
        thread.interrupt();
        //LockSupport.unpark(thread);
    }

    static class LockSupportThread implements Runnable{
        @Override
        public void run() {
            LockSupport.park(); //阻塞自己
            System.out.println(Thread.currentThread().getName() + "从park()中返回");
        }
    }
}

