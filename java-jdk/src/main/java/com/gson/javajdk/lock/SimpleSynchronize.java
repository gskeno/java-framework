package com.gson.javajdk.lock;

public class SimpleSynchronize {
    private Object lock = new Object();

    public void doWork(){
        synchronized (lock){
            System.out.println("doWork");
        }
    }
}
