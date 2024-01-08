package com.gson.javajdk.concurrent;

public class InheritableThreadLocalDemo2 {
    private static InheritableThreadLocal<String> threadLocal = new InheritableThreadLocal<>();

    public static void main(String[] args) throws InterruptedException {
        threadLocal.set("mainThread");
        System.out.println("value:"+threadLocal.get());
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                String value = threadLocal.get();
                System.out.println("value:"+value);
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                System.out.println("value:"+threadLocal.get());
            }
        });
        thread.start();

        threadLocal.set("B");
        Thread.sleep(20000);
    }
}
