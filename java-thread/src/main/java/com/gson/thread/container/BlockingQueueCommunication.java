package com.gsonkeno.jdk.training.concurrent.container;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class BlockingQueueCommunication {

    public static void main(String[] args) {
        Business bussiness = new Business();

        new Thread(new Runnable() {// 开启一个子线程

            @Override
            public void run() {
                for (int i = 1; i <= 3; i++) {

                    bussiness.sub();
                }

            }
        }).start();

        // main方法主线程
        for (int i = 1; i <= 3; i++) {

            bussiness.main();
        }
    }
}

class Business {

    private int i = 0;

    BlockingQueue queue1 = new ArrayBlockingQueue(1);
    BlockingQueue queue2 = new ArrayBlockingQueue(1);

    {
        try {
            queue1.put(1);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void sub() {
        try {
            queue1.put(1); //如果主线程没执行完，则子线程缓冲区一直有数，子线程在这里被阻塞
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        System.out.println("子线程执行前i=" + i++);
        System.out.println("子线程执行后i=" + i);

        try {
            queue2.take(); //取出主线程中缓冲区的数，让主线程执行
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void main() {
        try {
            queue2.put(1); //如果子线程没执行完，则主线程缓冲区一直有数，主线程在这里被阻塞
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        System.out.println("主线程执行前i=" + i++);
        System.out.println("主线程执行后i=" + i);

        try {
            queue1.take(); //取出子线程中缓冲区的数，让子线程执行
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
