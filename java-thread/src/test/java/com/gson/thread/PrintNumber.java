package com.gsonkeno.jdk.training.concurrent;

/**
 * 两个线程一个打印奇数，一个打印偶数，直至打印到100，程序结束。
 * 考察 notify和wait的使用
 * Created by gaosong on 2018-03-12
 */
public class PrintNumber {

    private static Object o = new Object();

    public static void main(String[] args) {
        OddThread oddThread = new OddThread();
        oddThread.start();

        EvenThread evenThread = new EvenThread();
        evenThread.start();
    }


    static class OddThread extends Thread{
        private int begin = 1;

        @Override
        public void run() {
            while (begin < 100){
                synchronized (o){
                    System.out.println(begin);


                    begin = begin + 2;
                    o.notify();
                    try {
                        o.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    if (begin == 101) return;

                }

            }
        }
    }

    static class EvenThread extends Thread{
        private int begin = 2;
        @Override
        public void run() {
            while (begin <= 100){
                synchronized (o){
                    o.notify();
                    System.out.println(begin);
                    if (begin == 100) return;

                    begin = begin + 2;
                    try {
                        o.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }
        }
    }
}
