package com.gson.design.thread;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * https://leetcode.cn/problems/print-foobar-alternately/
 */
public class 两线程交替打印FooBar2 {
    private int n;

    public 两线程交替打印FooBar2(int n) {
        this.n = n;
    }

    CyclicBarrier cb = new CyclicBarrier(2);
    volatile boolean fin = true;

    public void foo(Runnable printFoo) throws InterruptedException {
        for (int i = 0; i < n; i++) {
            while(!fin);
            // 线程一进来就先打印
            printFoo.run();
            fin = false;
            try {
                // 到达屏障处
                cb.await();
            } catch (BrokenBarrierException e) {}
        }
    }

    public void bar(Runnable printBar) throws InterruptedException {
        for (int i = 0; i < n; i++) {
            try {
                // 到达屏障处
                cb.await();
            } catch (BrokenBarrierException e) {}
            printBar.run();
            fin = true;
        }
    }

    public static void main(String[] args) {
        两线程交替打印FooBar2 solution = new 两线程交替打印FooBar2(5);
        PrintFoo printFoo = new PrintFoo();
        PrintBar printBar = new PrintBar();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    solution.foo(printFoo);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    solution.bar(printBar);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
