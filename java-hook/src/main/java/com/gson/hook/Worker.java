package com.gson.hook;

import java.util.Date;
import java.util.Scanner;

public class Worker implements Runnable {
    private volatile boolean exited = false;

    public Worker() {
        Runtime.getRuntime().addShutdownHook(new ExitHandler());
    }

    @Override
    public void run() {
        System.out.println("===请输入字符，将原样打印（Ctrl + C 退出）===");
        Scanner sc = new Scanner(System.in);
        while (!exited) {
            String line = sc.nextLine();
            System.out.println(line);
        }

    }

    class ExitHandler extends Thread {
        @Override
        public void run() {
            try {
                System.out.println("开始退出 " + new Date());
                Thread.sleep(2000);
                System.out.println("退出结束 " + new Date());
                exited = true;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}


