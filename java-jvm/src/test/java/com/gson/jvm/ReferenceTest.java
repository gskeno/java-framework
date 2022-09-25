package com.gsonkeno.jdk.training.jvm;

import org.junit.Test;

import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.util.LinkedList;
import java.util.Vector;

/**
 * java 引用测试
 * <li>强引用:无论内存是否足够,强引用的对象都不会回收</li>
 * <li>软引用:内存不足是，软引用的对象会被回收</li>
 * <li>弱引用:垃圾回收时，无论内存是否足够，弱引用的对象都有会被回收</li>
 * <li>虚引用:虚引用的对象随时都可能会被垃圾回收器回收</li>
 * <li><a href="http://blog.csdn.net/mazhimazh/article/details/19752475">csdn博客</a></li>
 * Created by gaosong on 2017-11-29
 */
public class ReferenceTest {

    //测试强引用
    @Test
    public void testStrongReference(){
        Vector<String> vector  = new Vector<>();
        vector.add("1");
        vector.add("2");
        //clear方法会遍历每个元素，将引用指向的对象置null
        vector.clear();
    }

    //测试软引用
    //页面浏览时后退按钮能够访问刚刚浏览的页面，适宜适宜软引用;
    //若内存不够，可能需要重新获取历史页面；若内存足够，则取出缓存即可
    // 软引用可以和一个引用队列（ReferenceQueue）联合使用，如果软引用所引用的对象被垃圾回收器回收，
    // Java虚拟机就会把这个软引用加入到与之关联的引用队列中
    @Test
    public void testSoftReference(){
        String str = "hello";
        ReferenceQueue<String> queue = new ReferenceQueue<>();
        SoftReference<String> ref = new SpecifiSoftReference(str,queue);

        System.gc();
        str = null;
        try {
            Thread.sleep(60000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    //测试弱引用
    //弱引用对象有自己的生命周期，使用者不想干预，如果对象被回收，使用者很容易再获取一个引用对象
    //而且，该对象很少被使用，这种情况比较适合使用弱引用
    //弱引用可以和一个引用队列（ReferenceQueue）联合使用，如果弱引用所引用的对象被垃圾回收，
    // Java虚拟机就会把这个弱引用(记住，是弱引用，而不是若引用关联的对象)加入到与之关联的引用队列中
    @Test
    public void testWeakReference(){
        ReferenceQueue<BigObj> queue = new ReferenceQueue<>();

        LinkedList<WeakReference<BigObj>> list = new LinkedList<>();
        for (int i = 0; i < 2; i++) {
            BigObj bigObj = new BigObj("大对象" + i);
            BigObjReference objWeakReference = new BigObjReference(bigObj,queue);

            //关键代码，可分析
            list.add(objWeakReference);
            //分析，这句代码若执行。弱引用是list容器的元素，不会被垃圾回收器回收。
            //但是由于弱引用指向的对象被回收，所以弱引用会被转移到queue中

            //如果这句代码不执行。弱引用会被作为垃圾回收，且弱引用指向的对象也会被回收
            //由于弱引用已经不存在，所以不会转移到queue中
        }


        System.gc();
        try {
            Thread.sleep(5*1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Reference<? extends BigObj> ref = null;

        while ((ref = queue.poll()) != null){
            System.out.println("in queue" + ref +"标识" + ((BigObjReference)ref).getId());
        }
    }

}
