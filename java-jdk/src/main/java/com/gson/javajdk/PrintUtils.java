package com.gson.javajdk;

import com.gson.javajdk.concurrent.CountTask;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.ForkJoinWorkerThread;
import java.util.concurrent.locks.AbstractOwnableSynchronizer;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class PrintUtils {

    public static void printForkJoinState(ForkJoinPool pool, CountTask countTask){
        Field workQueuesField = ReflectionUtils.findField(ForkJoinPool.class, "workQueues");
        ReflectionUtils.makeAccessible(workQueuesField);
        Object workQueuesObj = ReflectionUtils.getField(workQueuesField, pool);
        int len = Array.getLength(workQueuesObj);
        Object[] workQueues = new Object[len];
        ForkJoinTask[] currentJoins = new ForkJoinTask[len];
        ForkJoinTask[] currentSteals = new ForkJoinTask[len];
        int[] tops = new int[len];
        int[] bases = new int[len];
        ForkJoinWorkerThread[] owners = new ForkJoinWorkerThread[len];

        for(int i = 0; i < len; i++) {
            workQueues[i] = Array.get(workQueuesObj, i);
            if (workQueues[i] != null){
                Field currentJoinField = ReflectionUtils.findField(workQueues[i].getClass(), "currentJoin");
                ReflectionUtils.makeAccessible(currentJoinField);
                ForkJoinTask currentJoinTask = (ForkJoinTask) ReflectionUtils.getField(currentJoinField, workQueues[i]);
                currentJoins[i] = currentJoinTask;

                Field currentStealField = ReflectionUtils.findField(workQueues[i].getClass(), "currentSteal");
                ReflectionUtils.makeAccessible(currentStealField);
                ForkJoinTask currentStealTask = (ForkJoinTask) ReflectionUtils.getField(currentStealField, workQueues[i]);
                currentSteals[i] = currentStealTask;

                Field topField = ReflectionUtils.findField(workQueues[i].getClass(), "top");
                ReflectionUtils.makeAccessible(topField);
                int top = (int)ReflectionUtils.getField(topField, workQueues[i]);
                tops[i] = top;

                Field baseField = ReflectionUtils.findField(workQueues[i].getClass(), "base");
                ReflectionUtils.makeAccessible(baseField);
                int base = (int)ReflectionUtils.getField(baseField, workQueues[i]);
                bases[i] = base;

                Field ownerField = ReflectionUtils.findField(workQueues[i].getClass(), "owner");
                ReflectionUtils.makeAccessible(ownerField);
                ForkJoinWorkerThread owner = (ForkJoinWorkerThread)ReflectionUtils.getField(ownerField, workQueues[i]);
                owners[i] = owner;
            }
        }
        System.out.println("------begin------");
        for (int i = 0; i < len; i++) {
            if (workQueues[i] != null){
                System.out.println("queue[" + i + "]: base=" + bases[i] + ",top=" + tops[i] + ",owner=" + owners[i] + ",currentJoinTask=" + currentJoins[i] + ",currentStealTask=" + currentSteals[i]);
            }else {
                System.out.println("queue[" + i + "]: null");
            }
        }
        System.out.println("------end--------");
    }
    public static void printAqs(ReentrantLock reentrantLock){
        Field syncField = ReflectionUtils.findField(ReentrantLock.class, "sync");
        ReflectionUtils.makeAccessible(syncField);
        Object fairSync = ReflectionUtils.getField(syncField, reentrantLock);

        Field headField = ReflectionUtils.findField(fairSync.getClass(), "head");
        ReflectionUtils.makeAccessible(headField);
        Object headNode = ReflectionUtils.getField(headField, fairSync);
        String headNodeInfo = getNodeInfo(headNode);

        Field tailField = ReflectionUtils.findField(fairSync.getClass(), "tail");
        ReflectionUtils.makeAccessible(tailField);
        Object tailNode = ReflectionUtils.getField(tailField, fairSync);
        String tailInfo = getNodeInfo(tailNode);



        Field exclusiveOwnerThreadField = ReflectionUtils.findField(AbstractOwnableSynchronizer.class, "exclusiveOwnerThread");
        ReflectionUtils.makeAccessible(exclusiveOwnerThreadField);
        Object exclusiveOwnerThread = ReflectionUtils.getField(exclusiveOwnerThreadField, fairSync);
        System.out.println(Thread.currentThread().getName() + " AQS:headNodeInfo->" + headNodeInfo +  ",exclusiveOwnerThread->" + (exclusiveOwnerThread == null ? null : ((Thread)exclusiveOwnerThread).getName()) + ",tailInfo->" + tailInfo);
    }

    public static void print(Condition condition){
        Field firstWaiterField = ReflectionUtils.findField(AbstractQueuedSynchronizer.ConditionObject.class, "firstWaiter");
        ReflectionUtils.makeAccessible(firstWaiterField);
        Object firstWaiter = ReflectionUtils.getField(firstWaiterField, condition);
        String firstWaiterInfo = getNodeInfo(firstWaiter);

        Field lastWaiterField = ReflectionUtils.findField(AbstractQueuedSynchronizer.ConditionObject.class, "lastWaiter");
        ReflectionUtils.makeAccessible(lastWaiterField);
        Object lastWaiter = ReflectionUtils.getField(lastWaiterField, condition);
        String lastWaiterInfo = getNodeInfo(lastWaiter);


        System.out.println(Thread.currentThread().getName() + " WaitingQueue:" + firstWaiterInfo + ", lastWaiterInfo:" + lastWaiterInfo + "," + DateUtil.getTime());
    }

    public static String getNodeInfo(Object node){
        if (node == null){
            return null;
        }
        Field threadField = ReflectionUtils.findField(node.getClass(), "thread");
        ReflectionUtils.makeAccessible(threadField);
        Thread threadObj = (Thread)ReflectionUtils.getField(threadField, node);
        String threadName = threadObj != null ? threadObj.getName() : null;

        Field waitStatusField = ReflectionUtils.findField(node.getClass(), "waitStatus");
        ReflectionUtils.makeAccessible(waitStatusField);
        Object waitStatus = ReflectionUtils.getField(waitStatusField, node);

        Field nextField = ReflectionUtils.findField(node.getClass(), "next");
        ReflectionUtils.makeAccessible(nextField);
        Object next = ReflectionUtils.getField(nextField, node);
        String nextNodeInfo = getNodeInfo(next);
        return "[hasCode=" + node.hashCode() + ",threadName=" + threadName + ",waitStatus=" + waitStatus + ",nextNode=" + nextNodeInfo + "]";
    }
}
