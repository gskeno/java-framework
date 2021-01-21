package com.gson.javajdk.concurrent;

import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

public class CountTask extends RecursiveTask<Long> {

    private final long start;
    private final long end;
    private transient final int level;

    private static final int threshold = 5;

    public CountTask(long start, long end, int level) {
        this.start = start;
        this.end = end;
        this.level = level;
    }

    @Override
    protected Long compute() {
        while (true){

        }
//        long sum = 0L;
//        if (end - start > threshold) {
//            long middle = (end + start) / 2;
//
//            int newLevel = level + 1;
//            CountTask leftTask = new CountTask(start, middle, newLevel);
//            //System.out.println("start,end,level=" + start + "," + middle + "," + newLevel);
//            CountTask rightTask = new CountTask(middle, end, newLevel);
//            //System.out.println("start,end,level=" + middle + "," + end + "," + newLevel);
//
//
//            Long leftResult = leftTask.join();
//            Long rightResult = rightTask.join();
//            sum = leftResult + rightResult;
//            System.out.println(Thread.currentThread().getName() + "if sum=" + sum +"=" + leftResult + "+" + rightResult);
//        } else {
//            for (long i = start; i < end; i++) {
//                sum += i;
//            }
//            StringBuilder sb = new StringBuilder(Thread.currentThread().getName() + "else sum=" + sum + "=");
//            for (long j = start; j < end; j++) {
//                sb.append(j).append("+");
//            }
//            System.out.println(sb.substring(0, sb.length() - 1));
//        }
//        return sum;
    }
}
