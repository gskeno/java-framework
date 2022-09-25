package com.gsonkeno.jdk.training.concurrent;

import java.util.Date;

/**
 * Created by gaosong on 2018-03-31
 */
public class Synchronized {

    public static void main(String[] args) {
        synchronized (Synchronized.class){

        }
        m();
    }

    private synchronized  static void m() {

    }
}

 class SynchronizedTest{
     static class A{
         public static synchronized void B(){
             System.out.println(new Date() + "B");
             SleepUtil.sleep(5);
         }

         public static synchronized void C(){
             System.out.println(new Date() + "C");
         }
     }
     public static void main(String[] args) {

         new Thread(){
             @Override
             public void run() {
                A.B();
             }
         }.start();
         //SleepUtil.sleep(2);
         new Thread(){
             @Override
             public void run() {
                A.C();
             }
         }.start();
     }
}
