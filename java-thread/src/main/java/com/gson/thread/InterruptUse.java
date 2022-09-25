package com.gsonkeno.jdk.training.concurrent;

/**
 * Created by gaosong on 2017-08-31
 * 中断使用
 */
public class InterruptUse {
    public static void main(String[] args) {


        Thread sleepThread = new Thread(new SleepThread(),"sleepThread");
        Thread runningThread = new Thread(new RunningThread(),"runningThread");

        sleepThread.start();
        runningThread.start();

        //让两个线程充分执行
        SleepUtil.sleep(5);

        sleepThread.interrupt();
        runningThread.interrupt();

        System.out.println("runningThread status is " + runningThread.isInterrupted() + " after interrupted ");
        System.out.println("sleepThread status is " +   sleepThread.isInterrupted() + " after interrupted ");

        //避免中断后线程立马结束

    }

    static class RunningThread implements Runnable{
        @Override
        public void run() {
            while (true){

            }
        }
    }

    static class SleepThread implements  Runnable{
        @Override
        public void run() {
            while (true){
                SleepUtil.sleep(10);
            }
        }
    }
}
