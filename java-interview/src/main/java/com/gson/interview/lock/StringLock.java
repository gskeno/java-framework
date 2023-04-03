package com.gson.interview.lock;

public class StringLock {
    private final String lockA = "a";
    private final String lockB = "a";

    public void doA() throws InterruptedException {
        synchronized (lockA){
            System.out.println("进入到A锁");
            Thread.sleep(10 * 1000);
        }
    }

    public void doB() throws InterruptedException {
        synchronized (lockB){
            System.out.println("进入到B锁");
            Thread.sleep(60 * 1000);
        }
    }
    public static void main(String[] args) throws InterruptedException {
        StringLock stringLock = new StringLock();
        new Thread(()-> {
            try {
                stringLock.doA();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).start();

        new Thread(()-> {
            try {
                stringLock.doB();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }).start();
    }
}
