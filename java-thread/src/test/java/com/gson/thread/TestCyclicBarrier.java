package com.gsonkeno.jdk.training.concurrent;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * Created by gaosong on 2018-03-21
 */
public class TestCyclicBarrier {

    public static void main(String[] args) {
        int num = 10;
        CyclicBarrier barrier = new CyclicBarrier(num, new Runnable() {
            @Override
            public void run() {
                // TODO Auto-generated method stub
                System.out.println("go on together!");
            }
        });
        for (int i = 1; i <= num; i++) {
            new Thread(new CyclicBarrierWorker(i, barrier)).start();
        }
    }

    static class CyclicBarrierWorker implements Runnable {
        private int id;
        private CyclicBarrier barrier;

        public CyclicBarrierWorker(int id, final CyclicBarrier barrier) {
            this.id = id;
            this.barrier = barrier;
        }

        @Override
        public void run() {
            // TODO Auto-generated method stub
            try {
                System.out.println(id + " th people wait");
                barrier.await(); // 大家等待最后一个线程到达
            } catch (InterruptedException | BrokenBarrierException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
}
