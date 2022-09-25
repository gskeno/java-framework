package com.gsonkeno.jdk.training.jvm;

import java.util.concurrent.TimeUnit;

/**
 * Created by gaosong on 2018-03-28
 */
public class NotStop {

    private static boolean stop = false;

    public static void main(String[] args) throws InterruptedException {
        Thread t = new Thread(new Runnable(){
            public void run() {
                int i = 0;
                while (!stop) {
                    i++;
                    //再举个栗子，在while循环中做一些耗时但不耗CPU的操作，也会结束的很快，
                    // 因为这个时候CPU空闲了，JVM就有机会尽快的将主存和栈变量同步
                    //Object[] arr = new Object[1000000];
                }
            }
        });

        t.start();

        TimeUnit.SECONDS.sleep(1);
        stop = true;
    }
}
