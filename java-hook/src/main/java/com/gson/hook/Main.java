package com.gson.hook;

public class Main {
    public static void main(String[] args) {
        Worker worker = new Worker();
        Thread t = new Thread(worker);
        t.start();
        try {
            t.join();
        } catch (InterruptedException e) {
            System.out.println("程序中断");
        }
        // 不会被打印
        System.out.println("Main线程退出");
    }


}