package com.gson.jvm;

import java.util.concurrent.TimeUnit;

/**
 * -Xms100G -Xmx100G
 * 不会启动失败
 */
public class JvmXmsXmx {

    public static void main(String[] args) throws InterruptedException {
        while (true){
            TimeUnit.SECONDS.sleep(2);
            System.out.println("adda");
        }
    }
}
