package com.gson.javajdk.lock;

public class DeadLockTest {
    public static void main(String[] args) {
        LockTask task0 = new LockTask(0);
        LockTask task1 = new LockTask(1);
        Thread thread0 = new Thread(task0, "thread0");
        Thread thread1 = new Thread(task1, "thread1");
        thread0.start();
        thread1.start();
    }
}

class LockTask implements Runnable{
    private int flag;

    LockTask(int flag){
        this.flag = flag;
    }

    /**
     * 资源1
     */
    private static String resource1 = "resource1";


    /**
     * 资源2
     */
    private static String resource2 = "resource2";

    @Override
    public void run() {
        if (flag == 0){
            //锁定资源1，再去尝试锁定资源2
            synchronized (resource1){
                try {
                    System.out.println(Thread.currentThread() + " lock " + resource1);
                    Thread.sleep(2*1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                synchronized (resource2){
                    System.out.println(Thread.currentThread() + " lock " + resource2);
                }
            }
        }else if (flag == 1){
            //锁定资源2，再去尝试锁定资源1
            synchronized (resource2){
                try {
                    System.out.println(Thread.currentThread() + " lock " + resource2);
                    Thread.sleep(2*1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                synchronized (resource1){
                    System.out.println(Thread.currentThread() + " lock " + resource1);
                }
            }
        }
    }
}
