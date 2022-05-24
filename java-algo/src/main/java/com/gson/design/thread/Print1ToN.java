package com.gson.design.thread;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 有{N}个线程，1号线程打印Thread1-1, 2号线程打印Thread2-2, {N}号线程打印Thread{N}-{N}
 *
 * 从print1-1 一直打印到
 *  printN-100*N
 */
public class Print1ToN {
    private int totalThreads;
    private AtomicInteger ai = new AtomicInteger(1);
    private int maxValue;


    public Print1ToN(int threadNums){
        this.totalThreads = threadNums;
        maxValue = 100 * totalThreads;
    }

    public void doWork(){
        for (int i = totalThreads; i >=1; i--) {
            new Thread(new PrintRunnable(), String.valueOf(i)).start();
        }
    }



    class PrintRunnable implements Runnable{
        @Override
        public void run() {
            try {
                String threadNum = Thread.currentThread().getName();
                while (true){
                    // 所有线程，无时无刻不在竞争
                    if (ai.get() % totalThreads == Integer.valueOf(threadNum)){
                        System.out.println("Thread" + threadNum + "-" + ai.get());
                        ai.incrementAndGet();
                    }else if (ai.get() % totalThreads == 0 && Integer.valueOf(threadNum) == totalThreads){
                        System.out.println("Thread" + threadNum + "-" + ai.get());
                        ai.incrementAndGet();
                    }else if (ai.get() > maxValue){
                        break;
                    }
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }


    public static void main(String[] args) {
        Print1ToN print1ToN = new Print1ToN(15);
        print1ToN.doWork();
    }
}
