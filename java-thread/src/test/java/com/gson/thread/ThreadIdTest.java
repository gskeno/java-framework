package com.gsonkeno.jdk.training.concurrent;

import com.gsonkeno.jdk.training.ThreadId;

/**
 * Created by gaosong on 2018-03-27
 */
public class ThreadIdTest {

    public static void main(String[] args) {
//        int threadId = ThreadId.get();
//        System.out.println(threadId);
//
//        threadId = ThreadId.get();
//        System.out.println(threadId);

        for (int i = 0; i < 1000; i++) {
            new Thread(){
                @Override
                public void run() {
                    System.out.println(ThreadId.get());
                }
            }.start();
        }
    }
}
