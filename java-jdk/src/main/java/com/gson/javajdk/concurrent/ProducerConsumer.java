package com.gson.javajdk.concurrent;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * 写一个生产者，消费者
 * ArrayBlockingQueue 就是一个很好的实现
 */
public class ProducerConsumer<T> {

    private BlockingQueue<T> queue;

    public ProducerConsumer(int capacity){
        this.queue = new ArrayBlockingQueue<>(capacity);
    }

    public T take() throws InterruptedException {
        return queue.take();
    }

    public void put(T e) throws InterruptedException {
        queue.put(e);
    }
}
