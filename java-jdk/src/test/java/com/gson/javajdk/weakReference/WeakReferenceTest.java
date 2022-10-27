package com.gson.javajdk.weakReference;

import com.gson.javajdk.BigObject;
import org.junit.Assert;
import org.junit.Test;

import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.WeakReference;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;

/**
 * @author ruchen
 * @date 2022/4/11
 */
public class WeakReferenceTest {
    /**
     * gc 后
     * 弱引用对象指向的业务对象被回收,
     * reference.get返回空
     */
    @Test
    public void testWeakOnGC(){
        WeakReference<Object> weakReference = new WeakReference<>(new Object(), null);
        Assert.assertNotNull(weakReference.get());
        System.gc();
        Assert.assertNull(weakReference.get());
    }

    @Test
    public void testWeakWithEnqueue() throws InterruptedException {
        ReferenceQueue<Object> queue = new ReferenceQueue<>();
        WeakReference<Object> weakReference = new WeakReference<>(new Object(), queue);
        Assert.assertFalse(weakReference.isEnqueued());

        // 显式 将虚引用 Reference 放置到引用队列中去，成功返回true
        Assert.assertTrue(weakReference.enqueue());
        // 验证虚引用 Reference 已经放置到引用队列中
        Assert.assertTrue(weakReference.isEnqueued());
        // 验证引用队列中的元素 正好是 刚创建的虚拟引用  weakReference
        Reference<?> referenceInQueue = queue.poll();
        Assert.assertEquals(weakReference, referenceInQueue);
        // 因为没有gc，所以业务对象未被回收
        Assert.assertNotNull(weakReference.get());

        WeakReference<Object> implicitWeakReference = new WeakReference<>(new Object(), queue);
        Assert.assertFalse(implicitWeakReference.isEnqueued());
        // 因为gc， 虚引用 implicitWeakReference 被 隐式 放置到引用队列中
        System.gc();
        // 不可省略，因为gc需要时间
        Thread.sleep(1000);
        // 验证虚引用 implicitWeakReference 被隐式放置到引用队列中
        Assert.assertTrue(implicitWeakReference.isEnqueued());
        Reference<?> implicitReferenceInQueue = queue.poll();
        // 验证引用队列中的元素 正好是 刚创建的虚拟引用  weakReference
        Assert.assertEquals(implicitReferenceInQueue, implicitWeakReference);
        // 因为gc，业务对象被回收
        Assert.assertNull(implicitReferenceInQueue.get());

        Assert.assertNull(queue.poll());
    }

    /**
     * WeakHashMap内部的Entry继承WeakReference，实现了Map.Entry,
     * 键为弱建，当只有WeakHashMap引用key，key不再被其他对象引用时，
     * gc回收时会回收该key。
     */
    @Test
    public void testWeakHashMap(){
        WeakHashMap<String,String> whm = new WeakHashMap<>();
        String key1 = new String("张三丰");
        String value1 = "武当创始人";
        String key2 = new String("郭襄");
        String value2 = "峨眉创始人";
        whm.put(key1, value1);
        whm.put(key2, value2);
        System.out.println(whm.size());
        key1 = null;
        System.gc();
        System.out.println("gc后立即取size=" + whm.size());
        Set<Map.Entry<String, String>> entries = whm.entrySet();
        for(Map.Entry<String,String> entry : entries){
            System.out.println("遍历到元素 " + entry.getKey() + ":" + entry.getValue());
        }
        System.out.println("gc后遍历后取size=" + whm.size());
    }
}
