package com.gson.javajdk.concurrent;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.PriorityBlockingQueue;

public class PriorityBlockingQueueTest {



    @Test
    public void test(){
        PriorityBlockingQueue<FIFOEntry<String>> pbq = new PriorityBlockingQueue<>();

        pbq.put(new FIFOEntry("5"));
        pbq.put(new FIFOEntry("1"));
        pbq.put(new FIFOEntry("2"));
        pbq.put(new FIFOEntry("3"));
        pbq.put(new FIFOEntry("4"));
        pbq.put(new FIFOEntry("6"));

        System.out.println(pbq.toString());
    }

    @Test
    public void testHeapify() throws InterruptedException {
        List<FIFOEntry<String>> list = new ArrayList<>();
        list.add(new FIFOEntry("1"));
        list.add(new FIFOEntry("8"));
        list.add(new FIFOEntry("7"));
        list.add(new FIFOEntry("2"));
        list.add(new FIFOEntry("3"));
        list.add(new FIFOEntry("5"));
        list.add(new FIFOEntry("4"));

        //debug heapify method
        PriorityBlockingQueue<FIFOEntry<String>> pbq = new PriorityBlockingQueue<>(list);
        System.out.println(pbq.take());
        System.out.println(pbq.take());
        System.out.println(pbq.take());
        System.out.println(pbq.take());
        System.out.println(pbq.take());
    }
}
