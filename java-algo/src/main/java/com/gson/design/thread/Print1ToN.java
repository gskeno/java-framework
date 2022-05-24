package com.gson.design.thread;

/**
 * 有{N}个线程，1号线程打印Thread1-1, 2号线程打印Thread2-2, {N}号线程打印Thread{N}-{N}
 */
public class Print1ToN {
    private int threadNums;

    public Print1ToN(int threadNums){
        this.threadNums = threadNums;
    }

    public void doWork(){
        for (int i = 1; i <= threadNums; i++) {
            new Thread(new PrintRunnable(), "thread" + i).start();
        }
    }



    class PrintRunnable implements Runnable{
        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName());
        }
    }


    public static void main(String[] args) {
        Print1ToN print1ToN = new Print1ToN(100);
        print1ToN.doWork();
    }
}
