package com.gson.interview.concurrentprogrammingart.chapter9;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Job implements Runnable{
    private  int jobNo;
    private long useTime;

    public Job(int jobNo, long useTime){
        this.jobNo = jobNo;
        this.useTime = useTime;
    }
    @Override
    public void run() {
        try {
            System.out.println("线程 " + Thread.currentThread().getName() + " 执行任务 " + jobNo  + " " +getNow());
            Thread.sleep(useTime);
            System.out.println("线程 " + Thread.currentThread().getName() + " 结束任务 " + jobNo + " " + getNow());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return "Job{" +
                "jobNo=" + jobNo +
                '}';
    }

    public static String getNow(){
        LocalDateTime ldtt=LocalDateTime.now();
        DateTimeFormatter format=DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String str=ldtt.format(format);
        return str;
    }
}
