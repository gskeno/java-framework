package com.gson.javajdk;

import com.gson.javajdk.concurrent.CountTask;
import org.springframework.util.ReflectionUtils;

import javax.jws.Oneway;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.ForkJoinWorkerThread;

public class SourceInnerUtils {
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
        // array属性是一个数组
        Object[] arrays = new Object[len];
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

                Field arrayField = ReflectionUtils.findField(workQueues[i].getClass(), "array");
                ReflectionUtils.makeAccessible(arrayField);
                Object array = ReflectionUtils.getField(arrayField, workQueues[i]);
                arrays[i] = array;
            }
        }
        StringBuilder sb = new StringBuilder();
        sb.append(Thread.currentThread() + "---begin------").append("\n");
        for (int i = 0; i < len; i++) {
            if (workQueues[i] != null){

                sb.append("queue[" + i + "]: base=" + bases[i] + ",top=" + tops[i] + ",owner=" + owners[i] + ",currentJoinTask=" + currentJoins[i] + ",currentStealTask=" + currentSteals[i]);
                if (arrays[i] != null){
                    int length = Array.getLength(arrays[i]);
                    for (int j = 0; j < length; j++) {
                        Object o = Array.get(arrays[i], j);
                        if (o == null){
                            break;
                        }else {
                            sb.append(o);
                        }
                    }
                }
                sb.append("\n");
            }else {
                sb.append("queue[" + i + "]: null").append("\n");
            }
        }
        sb.append("currentTask:" + countTask + "\n");
        sb.append(Thread.currentThread() + "---end--------\n");
        System.out.println(sb);
    }
}
