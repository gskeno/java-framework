package com.gson.javajdk.weakReference;

import com.gson.javajdk.BigObject;
import org.junit.Test;

import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;

/**
 * @author ruchen
 * @date 2022/4/11
 */
public class WeakReferenceTest {

    @Test
    public void testWeakReferenceNoQueue(){
        Object obj = new Object();
        // 创建弱引用对象时没有设置相关联队列，所以当弱引用失效时，wf.isEnqueued永远为false
        WeakReference<Object> wf = new WeakReference<Object>(obj);
        System.out.println(wf.isEnqueued());
        obj = null;
        System.gc();
        System.out.println(wf.isEnqueued());
    }

    @Test
    public void testWeakReferenceWithQueue() throws InterruptedException {
        BigObject obj = new BigObject("m1");
        // 创建弱引用对象时没有设置相关联队列，所以当弱引用失效时，wf.isEnqueued永远为false
        ReferenceQueue<Object> queue = new ReferenceQueue<>();
        WeakReference<Object> wf = new WeakReference<Object>(obj, queue);
        System.out.println("未gc前弱引用包裹的对象是 " + wf.get());
        System.out.println("未gc前弱引用是否入队列 " + wf.isEnqueued());
        obj = null;
        System.gc();
        // Thread.sleep(2000);
        System.out.println("gc后弱引用包裹的对象是 " + wf.get());

        Thread.sleep(2000);
        System.out.println("gc后弱引用是否入队列 " + wf.isEnqueued());
        Reference<?> poll = queue.poll();
        System.out.println("移除队列中的弱引用包裹器是 " + poll);
        System.out.println("移除队列中的弱引用包裹器包裹的真实对象是 " + poll.get());
    }
}
