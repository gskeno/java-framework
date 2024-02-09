package com.gson.javajdk.lock;

import com.gson.javajdk.DateUtil;
import com.gson.javajdk.PrintUtils;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class AQSCondition2Test {
    static ReentrantLock lock = new ReentrantLock(true);
    static Condition condition = lock.newCondition();

    /**
     *
     * A线程await后，B线程获取到锁，B线程signal后，使A线程中断，B线程释放锁
     * A线程await返回，表示再次获取到锁
     *
     * 整个过程虽然发生了中断，但是中断发生在signal之后，所以A线程能够继续完成lock/unlock的整套动作
     * @param args
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        Thread threadA = new Thread(new Runnable() {
            @Override
            public void run() {
                lock.lock();
                try {
                    System.out.println(Thread.currentThread().getName() + ",获取到锁,开始休息2s," + DateUtil.getTime());
                    Thread.sleep(2000);
                    System.out.println(Thread.currentThread().getName() + ",用2s执行完一半任务,开始await休息一下," + DateUtil.getTime());
                    condition.await();
                    System.out.println(Thread.currentThread().getName() + ",从await返回,开始释放锁," + DateUtil.getTime());
                    PrintUtils.printAqs(lock);
                    PrintUtils.print(condition);
                    boolean interrupted = Thread.currentThread().isInterrupted();
                    System.out.println("是否中断过:" + interrupted);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                    System.out.println("  " + Thread.currentThread().getName() + ",释放锁完毕," + DateUtil.getTime());
                    PrintUtils.printAqs(lock);
                    PrintUtils.print(condition);
                }

            }
        }, "threadA");
        threadA.start();

        Thread threadB = new Thread(new Runnable() {
            @Override
            public void run() {
                lock.lock();
                try {
                    System.out.println("  " + Thread.currentThread().getName() + ",获取到锁,开始休息6s" + DateUtil.getTime());
                    Thread.sleep(6 * 1000);
                    System.out.println("  " + Thread.currentThread().getName() + ",signal一下," + DateUtil.getTime());
                    PrintUtils.printAqs(lock);
                    PrintUtils.print(condition);
                    condition.signal();
                    PrintUtils.printAqs(lock);
                    PrintUtils.print(condition);
                    System.out.println("  " + Thread.currentThread().getName() + ",signal后，开始睡眠3s," + DateUtil.getTime());
                    Thread.sleep(3 * 1000);
                    System.out.println("  " + Thread.currentThread().getName() + ",睡眠3s完毕，开始释放锁," + DateUtil.getTime());
                    PrintUtils.printAqs(lock);
                    PrintUtils.print(condition);
                } catch (Exception e) {

                } finally {
                    lock.unlock();
                    System.out.println("  " + Thread.currentThread().getName() + ",释放锁完毕," + DateUtil.getTime());
                    PrintUtils.printAqs(lock);
                    PrintUtils.print(condition);
                }
            }
        }, "threadB");
        threadB.start();
        int time = 0;
        while (true){
            Thread.sleep(1000);
            time++;
            if (time == 9){
                threadA.interrupt();
                System.out.println("threadA发生中断,发生在threadB-signal之后" + DateUtil.getTime());
            }
            PrintUtils.printAqs(lock);
            PrintUtils.print(condition);
        }
    }

}
