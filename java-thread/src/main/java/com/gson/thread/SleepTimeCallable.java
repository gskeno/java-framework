package com.gson.thread;

import java.util.concurrent.Callable;

public class SleepTimeCallable implements Callable<Integer> {

    private String name;

    public SleepTimeCallable(String name){
        this.name = name;
    }
    @Override
    public Integer call() throws Exception {
        try {
            System.out.println(name+"开始执行");
            Thread.sleep(1000);
            System.out.println(name+"执行完成");
        }catch (Exception e){
            e.printStackTrace();
        }
        return 3;
    }
}
