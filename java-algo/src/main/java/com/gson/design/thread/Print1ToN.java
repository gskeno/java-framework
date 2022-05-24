package com.gson.design.thread;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 有{N}个线程，1号线程打印Thread1-1, 2号线程打印Thread2-2, {N}号线程打印Thread{N}-{N}
 */
public class Print1ToN {
    private int threadNums;
    private AtomicInteger ai = new AtomicInteger(1);
    private Object lock = new Object();


    public Print1ToN(int threadNums){
        this.threadNums = threadNums;
    }

    public void doWork(){
        for (int i = threadNums; i >=1; i--) {
            new Thread(new PrintRunnable(), String.valueOf(i)).start();
        }
    }



    class PrintRunnable implements Runnable{
        @Override
        public void run() {
            try {
                String threadNum = Thread.currentThread().getName();
                while (true){
                    if (ai.get() == Integer.valueOf(threadNum)){
                        System.out.println("Thread" + threadNum + "-" + ai.get());
                        ai.incrementAndGet();
                        break;
                    }
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }


    public static void main(String[] args) {
        Print1ToN print1ToN = new Print1ToN(77);
        print1ToN.doWork();
    }
}
