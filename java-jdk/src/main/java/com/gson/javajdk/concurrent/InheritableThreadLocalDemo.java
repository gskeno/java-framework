package com.gson.javajdk.concurrent;

public class InheritableThreadLocalDemo {

    public static void main(String[] args)
    {
        ParentThread pt = new ParentThread("p1");
        pt.start();
    }
}

class ParentThread extends Thread {
    static int childCount = 0;

    public static InheritableThreadLocal threadId = new InheritableThreadLocal() {
        public Object childValue(Object parentValue)
        {
            return parentValue.toString() + ".c" + (++childCount) ;  //this is line 18 where NullPointerException is occuring
        }
    };

    public ParentThread(String pThreadId) {
        threadId.set(pThreadId);
        System.out.println("ParentThread constructor invoked, threadId=" + Thread.currentThread().getId() + ",threadName=" + Thread.currentThread().getName());
    }

    public void run()
    {
        System.out.println("ParentThread run invoked, threadId=" + Thread.currentThread().getId() + ",threadName=" + Thread.currentThread().getName() +", -Thread id:" + threadId.get());

        ChildThread childThread1 = new ChildThread();
        childThread1.start();

//        ChildThread childThread2 = new ChildThread();
//        childThread2.start();
    }
}
class ChildThread extends Thread {

    public void run()
    {
        System.out.println("Child Thread Value :" + ParentThread.threadId.get());
    }
}
