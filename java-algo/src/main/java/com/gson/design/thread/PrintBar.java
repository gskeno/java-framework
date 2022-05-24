package com.gson.design.thread;

public class PrintBar implements Runnable{
    @Override
    public void run() {
        System.out.print("bar");
    }
}
