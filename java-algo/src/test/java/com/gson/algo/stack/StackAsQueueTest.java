package com.gson.algo.stack;

import org.junit.Test;

public class StackAsQueueTest {
    @Test
    public void test(){
        StackAsQueue stackAsQueue = new StackAsQueue();
        stackAsQueue.push(1);
        stackAsQueue.push(3);
        stackAsQueue.push(5);

        System.out.println(stackAsQueue.pop());

        stackAsQueue.push(7);
        stackAsQueue.push(8);
        System.out.println(stackAsQueue.pop());
        System.out.println(stackAsQueue.pop());
        System.out.println(stackAsQueue.pop());
        System.out.println(stackAsQueue.pop());
    }
}
