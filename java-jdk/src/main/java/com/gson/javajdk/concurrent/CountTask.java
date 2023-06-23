package com.gson.javajdk.concurrent;

import com.gson.javajdk.DateUtil;

import java.util.Map;
import java.util.concurrent.RecursiveTask;

public class CountTask extends RecursiveTask<Long> {
    long begin;
    long end;

    public CountTask(long begin, long end) {
        this.begin = begin;
        this.end = end;
    }

    public long getBegin() {
        return begin;
    }

    public long getEnd() {
        return end;
    }

    // 求[begin, end]的所有元素和
    @Override
    protected Long compute() {
        //if (Thread.currentThread().getName().equals("ForkJoinPool-1-worker-2")){
        // printCurrentStack();
        //System.out.println(getJavaStackTrace());
        //}
        //当begin与end相邻时，任务已经足够小，直接返回
        if (end - begin <= 1) {
            System.out.println(Thread.currentThread() + "---start " + begin + "," + end + " , " + DateUtil.getTime());
            try {
                Thread.sleep(60000);
            } catch (Exception e) {
            }
            System.out.println(Thread.currentThread() + "---finish " + begin + "," + end + " , " + DateUtil.getTime());
            return end == begin ? end : begin + end;
        }
        // 继续拆分任务
        System.out.println(Thread.currentThread() + "---start " + begin + "," + end + " , " + DateUtil.getTime());
        long mid = begin + (end - begin) / 2;
        CountTask task1 = new CountTask(begin, mid);
        CountTask task2 = new CountTask(mid + 1, end);
        task1.fork();
        task2.fork();

        Long v1 = task1.join();
        //System.out.println(Thread.currentThread() + "---task1JoinFinish " + begin + "," + end + " , " + DateUtil.getTime());
        Long v2 = task2.join();
        //System.out.println(Thread.currentThread() + "---task2JoinFinish " + begin + "," + end + " , " + DateUtil.getTime());

        //invokeAll(task1, task2);
        System.out.println(Thread.currentThread() + "---finish " + begin + "," + end + " , " + DateUtil.getTime());

        return v1 + v2;
    }

    /**
     * 获取线程快照信息
     *
     * @return
     */
    public static String getJavaStackTrace() {
        StringBuffer msg = new StringBuffer();
        for (Map.Entry<Thread, StackTraceElement[]> stackTrace : Thread.getAllStackTraces().entrySet()) {
            Thread thread = (Thread) stackTrace.getKey();
            StackTraceElement[] stack = (StackTraceElement[]) stackTrace.getValue();
            if (thread.equals(Thread.currentThread())) {
                continue;
            }
            msg.append("\n 线程:").append(thread.getName()).append("\n");
            for (StackTraceElement element : stack) {
                msg.append("\t").append(element).append("\n");
            }
        }
        return msg.toString();
    }

    public static void printCurrentStack(){
        for ( StackTraceElement ele : Thread.currentThread().getStackTrace()) {
            System.out.println("出：" + Thread.currentThread().getName() + ":" + ele.getClassName() + "$" + ele.getMethodName() + "$" + ele.getFileName() + "$" + ele.getLineNumber());
        }
    }
}
