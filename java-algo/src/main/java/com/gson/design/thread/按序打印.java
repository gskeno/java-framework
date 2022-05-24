package com.gson.design.thread;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * https://leetcode.cn/problems/print-in-order/
 */
public class 按序打印 {
    private AtomicInteger firstRunEnd = new AtomicInteger(0);
    private AtomicInteger secondRunEnd = new AtomicInteger(0);
    public 按序打印() {

    }

    public void first(Runnable printFirst) throws InterruptedException {

        // printFirst.run() outputs "first". Do not change or remove this line.
        printFirst.run();
        firstRunEnd.incrementAndGet();
    }

    public void second(Runnable printSecond) throws InterruptedException {
        while (firstRunEnd.get() != 1){

        }
        // printSecond.run() outputs "second". Do not change or remove this line.
        printSecond.run();
        secondRunEnd.incrementAndGet();
    }

    public void third(Runnable printThird) throws InterruptedException {
        while (secondRunEnd.get() != 1){

        }
        // printThird.run() outputs "third". Do not change or remove this line.
        printThird.run();
    }

    public static void main(String[] args) {
        按序打印 solution = new 按序打印();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    solution.third(()->{
                        System.out.print("third");
                    });
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    solution.second(()->{
                        System.out.print("second");
                    });
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    solution.first(()->{
                        System.out.print("first");
                    });
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
