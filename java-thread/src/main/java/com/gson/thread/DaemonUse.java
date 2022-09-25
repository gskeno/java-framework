package com.gsonkeno.jdk.training.concurrent;

/**
 * Created by gaosong on 2017-08-31
 * 守护线程，当jvm中运行的全是守护线程时，jvm就会退出
 */
public class DaemonUse {
    public static void main(String[] args) {
        Runnable runTarget = new Runnable() {
            @Override
            public void run() {
                int i = 1;
                while (true){
                    i++;
                    System.out.println(i);
                }
            }
        };

        Thread thread = new Thread(runTarget);
        thread.setDaemon(true);
        thread.start();

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("JVM EXIST");
            }
        }));
    }
}
