package com.gson.javajdk.concurrent;

import org.junit.Test;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;

public class ForkJoinTest {
    @Test
    public void testForkJoin() throws ExecutionException, InterruptedException {
        ForkJoinPool pool = new ForkJoinPool(2);
        long begin = 1;
        long end = 4;
        CountTask task = new CountTask(begin, end, pool);
        long start  = System.currentTimeMillis();
        ForkJoinTask<Long> longForkJoinTask = pool.submit(task);
        Long res = longForkJoinTask.get();
        System.out.println("begin " + begin + " end " + end + " result " + res + " use time " + (System.currentTimeMillis() - start) / 1000 + "s");
    }

    @Test
    public void test(){
        System.out.println(0x7fff);
        System.out.println(Runtime.getRuntime().availableProcessors());
    }

    @Test
    public void testBit(){
        //见ForkJoinPool 2341行
        int a = 0B111;
        a|=a>>>1;
        System.out.println(a);
        a|=a>>>2;
        System.out.println(a);
        a|=a>>>4;
        System.out.println(a>>>4);
        a|=a>>>8;
        System.out.println(a>>>8);
        a|=a>>>16;
        System.out.println(a>>>16);
        System.out.println(a);

        int b = 1;
        System.out.println(~b);

        int rs = 1;
        int RSLOCK = 1;
        int ns =4;
        // RSLOCK  0x 0001
        // ~RSLOCK 0x FFFE
        // rs      0x 0001
        //(rs & ~RSLOCK) 0x 0000
        //((rs & ~RSLOCK) | ns) 0x0004
        int c = ((rs & ~RSLOCK) | ns);
        System.out.println(c);

        int d = 1<<31;
        System.out.println(d);


        long e = -1970359196712960L;
        System.out.println((int)e);

        long f = 0x0001;
        String g = Long.toHexString(f << (32+15));
        System.out.println(g);

    }
}
