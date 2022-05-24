package com.gson.design.thread;

public class PrintFoo implements Runnable{
    @Override
    public void run() {
        System.out.print("foo");
    }
}
