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
        BigObject obj = new BigObject();
        // 创建弱引用对象时没有设置相关联队列，所以当弱引用失效时，wf.isEnqueued永远为false
        ReferenceQueue<Object> queue = new ReferenceQueue<>();
        WeakReference<Object> wf = new WeakReference<Object>(obj, queue);
        System.out.println(wf);
        System.out.println(wf.isEnqueued());
        obj = null;
        System.gc();
        // Thread.sleep(2000);
        System.out.println("wf.get " + wf.get());
        Thread.sleep(2000);
        System.out.println(wf.isEnqueued());
        Reference<?> poll = queue.poll();
        System.out.println(poll);
        System.out.println(poll.get());
    }
}
