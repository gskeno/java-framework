package com.gson.javajdk.lang;

public class ShutDownHookUsage {
    public static void main(String[] args) throws InterruptedException {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> System.out.println("执行清理工作")));
        Thread.sleep(2000);
        System.out.println("main线程将要结束");
    }
}
