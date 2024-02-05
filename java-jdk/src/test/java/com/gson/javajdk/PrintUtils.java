package com.gson.javajdk;

import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.concurrent.locks.AbstractOwnableSynchronizer;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class PrintUtils {
    public static void printAqs(ReentrantLock reentrantLock){
        Field syncField = ReflectionUtils.findField(ReentrantLock.class, "sync");
        ReflectionUtils.makeAccessible(syncField);
        Object fairSync = ReflectionUtils.getField(syncField, reentrantLock);

        Field headField = ReflectionUtils.findField(fairSync.getClass(), "head");
        ReflectionUtils.makeAccessible(headField);
        Object headNode = ReflectionUtils.getField(headField, fairSync);
        String nodeInfo = getNodeInfo(headNode);

        Field exclusiveOwnerThreadField = ReflectionUtils.findField(AbstractOwnableSynchronizer.class, "exclusiveOwnerThread");
        ReflectionUtils.makeAccessible(exclusiveOwnerThreadField);
        Object exclusiveOwnerThread = ReflectionUtils.getField(exclusiveOwnerThreadField, fairSync);
        System.out.println("AQS:" + nodeInfo + ",exclusiveOwnerThread=" + (exclusiveOwnerThread == null ? null : ((Thread)exclusiveOwnerThread).getName()));
    }

    public static void print(Condition condition){
        Field firstWaiterField = ReflectionUtils.findField(AbstractQueuedSynchronizer.ConditionObject.class, "firstWaiter");
        ReflectionUtils.makeAccessible(firstWaiterField);
        Object firstWaiter = ReflectionUtils.getField(firstWaiterField, condition);
        String firstWaiterInfo = getNodeInfo(firstWaiter);
        System.out.println("WaitingQueue:" + firstWaiterInfo + "," + DateUtil.getTime());
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
