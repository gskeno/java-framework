package com.gson.design.thread;
import java.util.concurrent.Semaphore;

/**
 * N个线程顺序打印print1-1， print2-2, .... , printN-100*N
 *
 * 参考 https://codeantenna.com/a/9cBdZnlJlS
 */
public class Print1TO100NSemaphore {

    // 使用信号量，线程编号为1,2,3,..., N-1, N，一直到N
    // 每个线程对应一个信号量Semaphore,资源数为1
    // 每个线程在打印数字时，需要获取到上一个线程绑定的信号量preSemaphore，获取到后，执行打印，即完成了自己的逻辑
    // 再释放自己的信号量，直到最后打印的数字>100*N,则不再打印

    // 考虑到需要使第一个线程先打印，我们认为第一个线程的上一个线程是最后一个线程，使其信号量可使用(即acquire立马返回)，
    // 其他线程的信号量不可使用(已执行acquire过，信号被占有中)
    private int totalThreads;
    private int value = 1;

    public Print1TO100NSemaphore(int totalThreads){
        this.totalThreads = totalThreads;
    }

    public void doWork() throws InterruptedException {
        Thread[] threads = new Thread[totalThreads];
        Semaphore[] semaphores = new Semaphore[totalThreads];
        for (int i = 0; i < totalThreads; i++) {
            semaphores[i] = new Semaphore(1);
            // 除了最后一个线程，其他线程的信号量全部被占用，则第一个线程首先执行
            if (i != totalThreads - 1){
                semaphores[i].acquire();
            }
        }

        for (int i = 0; i <totalThreads; i++) {
            Semaphore pre = (i == 0) ? semaphores[totalThreads-1] : semaphores[i-1];
            Semaphore cur = semaphores[i];
            threads[i] = new ExecThread(pre, cur, "thread" + (i+1));
            threads[i].start();
        }
    }

    public class ExecThread extends Thread{
        private final Semaphore preSemaphore;
        private final Semaphore curSemaphore;

        public ExecThread(Semaphore preSemaphore, Semaphore curSemaphore, String name){
            super(name);
            this.preSemaphore = preSemaphore;
            this.curSemaphore = curSemaphore;
        }
        @Override
        public void run() {
            while (true){
                try {

                    preSemaphore.acquire();
                    // if提前
                    if (value <= totalThreads * 100){
                        System.out.println(Thread.currentThread().getName() + "-" + value++);
                    }
                    curSemaphore.release();
                    if (value > totalThreads * 100){
                        break;
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Print1TO100NSemaphore print1TO100N = new Print1TO100NSemaphore(21);
        print1TO100N.doWork();
    }
}
