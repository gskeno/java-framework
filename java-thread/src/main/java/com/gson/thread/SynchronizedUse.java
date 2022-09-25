package com.gson.thread;


/**
 * Created by gaosong on 2017-08-31
 */
public class SynchronizedUse {
    public static void main(String[] args) {
        synchronized (SynchronizedUse.class){
            
        }
        
        display();
    }

    public static synchronized void display() {
        System.out.println(1);
    }


}
