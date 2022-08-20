package com.gson.design.thread;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Semaphore;

/**
 * 1个线程打印1,2,3，一个线程打印A,B,C。如何时两个线程交替打印A1B2C3
 */
public class 两线程交替打印A1B2C3 {

    private static Map<String,String> next = new HashMap<String,String>(){{
        put("A","B");
        put("B","C");
        put("C","A");
        put("1","2");
        put("2","3");
        put("3","1");
    }};

    public static void main(String[] args) {
        //solution1();
        //solution2();
        solution3();
    }

    private static  Long count = 0L;
    /**
     * 使用 同步关键字 synchronized
     */
    public static void solution1(){
        Object o = new Object();

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    synchronized (o){
                        try {
                            o.wait();
                            long mod = count % 6;
                            if (mod == 1){
                                System.out.print('1');
                            }else if (mod == 3){
                                System.out.print('2');
                            }
                            else if (mod == 5){
                                System.out.print('3');
                            }
                            count++;
                            o.notify();
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    synchronized (o){
                        try {
                            o.notify();
                            long mod = count % 6;
                            if (mod == 0){
                                System.out.print('A');
                            }else if (mod == 2){
                                System.out.print('B');
                            }
                            else if (mod == 4){
                                System.out.print('C');
                            }
                            count++;
                            o.wait();
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                    }
                }

            }
        }).start();
    }
    private static volatile long c = 0;
    private static void solution2(){
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    if (c % 6 == 1){
                        System.out.println("1");
                        c++;
                    }
                    if (c % 6 == 3){
                        System.out.println("2");
                        c++;
                    }
                    if (c % 6 == 5){
                        System.out.println("3");
                        c++;
                    }
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    if (c % 6 == 0){
                        System.out.println("A");
                        c++;
                    }
                    if (c % 6 == 2){
                        System.out.println("B");
                        c++;
                    }
                    if (c % 6 == 4){
                        System.out.println("C");
                        c++;
                    }
                }
            }
        }).start();
    }

    /**
     * 使用信号量，如果只使用一个信号量，释放信号量的线程可能再次获取到信号量
     * 需要使用两个信号量
     */
    private static void solution3(){
        Semaphore letterSemaphore = new Semaphore(1);
        Semaphore numberSemaphore = new Semaphore(0);
        new Thread(new Runnable() {

            @Override
            public void run() {
                String letter = "C";
                while (true){
                    try {
                        letterSemaphore.acquire(1);
                        letter = next.get(letter);
                        System.out.println(letter);
                        numberSemaphore.release(1);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }

            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                String num = "3";
                while (true){
                    try {
                        numberSemaphore.acquire(1);
                        num = next.get(num);
                        System.out.println(num);
                        letterSemaphore.release();
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }).start();
    }
}
