package com.gson.design.thread;
import java.util.concurrent.Semaphore;

/**
 * N个线程顺序打印print1-1， print2-2, .... , printN-100*N
 *
 * 参考 https://codeantenna.com/a/9cBdZnlJlS
 */
public class Print1TO100NSemaphore2 {

    /**
     * 线程个数
     */
    private int totalThreads;
    /**
     * 每个线程打印多少次
     */
    private int printTimesPerThread;

    private int value = 1;

    public Print1TO100NSemaphore2(int totalThreads, int printTimesPerThread){
        this.totalThreads = totalThreads;
        this.printTimesPerThread = printTimesPerThread;
    }

    public void doWork() throws InterruptedException {
        Semaphore[] semaphores = new Semaphore[totalThreads];
        // 除了第一个信号量有资源，其他信号量暂时都没资源，保证第一个信号量对应的线程可以先执行
        semaphores[0] = new Semaphore(1);
        for (int i = 1; i < semaphores.length; i++) {
            semaphores[i] = new Semaphore(0);
        }

        for (int i = 0; i < semaphores.length; i++) {
            if (i == semaphores.length-1){
                new ExecThread(semaphores[semaphores.length-1], semaphores[0], "thread" + (i+1)).start();
            }else {
                new ExecThread(semaphores[i], semaphores[i+1], "thread" + (i+1)).start();
            }
        }
    }

    public class ExecThread extends Thread{
        private final Semaphore curSemaphore;
        private final Semaphore nextSemaphore;

        public ExecThread(Semaphore curSemaphore, Semaphore nextSemaphore, String threadName){
            super(threadName);
            this.curSemaphore = curSemaphore;
            this.nextSemaphore = nextSemaphore;
        }
        @Override
        public void run() {
            for (int i = 1; i <= printTimesPerThread ; i++) {
                try {
                    curSemaphore.acquire(1);
                    System.out.println(Thread.currentThread().getName() + "-" + value++);
                    nextSemaphore.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Print1TO100NSemaphore2 print1TO100N = new Print1TO100NSemaphore2(21, 100);
        print1TO100N.doWork();
    }
}
