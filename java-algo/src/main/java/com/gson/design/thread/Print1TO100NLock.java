package com.gson.design.thread;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * N个线程顺序打印print1-1， print2-2, .... , printN-100*N
 *
 * 参考 https://codeantenna.com/a/9cBdZnlJlS
 */
public class Print1TO100NLock {
    /**
     * 每个线程打印多少次
     */
    private final int printTimesPerThread;
    /**
     * 总共有多少个线程参与打印
     */
    private int totalThreads;

    private volatile int value = 1;

    private Lock lock = new ReentrantLock();

    private Condition condition = lock.newCondition();

    public Print1TO100NLock(int totalThreads, int printTimesPerThread){
        this.totalThreads = totalThreads;
        this.printTimesPerThread = printTimesPerThread;
    }

    public void doWork() throws InterruptedException {
        for (int i = 1; i <= totalThreads; i++) {
            new ExecThread(i).start();
        }
    }

    public class ExecThread extends Thread{
        private final int threadNo;

        public ExecThread(int threadNo){
            this.threadNo = threadNo;
        }
        @Override
        public void run() {
            for (int i = 1; i <= printTimesPerThread;) {
                lock.lock();
                try {
                    if (value % totalThreads == threadNo){
                        System.out.println("thread" + threadNo + "-" + value++);
                        i++;
                        // 一定要调用signalAll，否则会锁住执行不完
                        condition.signal();
                    }else if (value % totalThreads == 0 && threadNo == totalThreads){
                        System.out.println("thread" + threadNo + "-" + value++);
                        i++;
                        condition.signal();
                    }else {
                        // 该线程当前不应该打印，放弃锁,等待其他线程signal唤醒，只有重新获取到锁的线程才能用等待方法返回
                        condition.signal();
                        // 线程放弃锁之前，自身要唤醒其他线程
                        condition.await();
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }finally {
                    lock.unlock();
                }

            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Print1TO100NLock print1TO100NLock = new Print1TO100NLock(21, 100);
        print1TO100NLock.doWork();
    }
}
