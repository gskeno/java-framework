package com.gsonkeno.jdk.training.concurrent;

import java.util.concurrent.TimeUnit;

/**
 * Created by gaosong on 2017-09-05
 */
public class SleepUtil {

    public static void sleep(long seconds){
        try {
            TimeUnit.SECONDS.sleep(seconds );
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
