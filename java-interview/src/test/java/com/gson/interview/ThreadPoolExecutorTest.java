package com.gson.interview;

import org.junit.Assert;
import org.junit.Test;

import java.util.concurrent.atomic.AtomicInteger;

public class ThreadPoolExecutorTest {
    static final int COUNT_BITS = Integer.SIZE - 3;
    // 低29位为1，其余为0
    static final int CAPACITY   = (1 << COUNT_BITS) - 1;
    // 高3位为1，其余为0
    static final int RUNNING    = -1 << COUNT_BITS;
    // 初始值等于RUNNING
    final AtomicInteger ctl = new AtomicInteger(ctlOf(RUNNING, 0));
    @Test
    public void test(){
        Assert.assertEquals(Integer.toBinaryString(CAPACITY), "11111111111111111111111111111");
        Assert.assertEquals(Integer.toBinaryString(RUNNING), "11100000000000000000000000000000");
        Assert.assertEquals(ctl.get(), RUNNING);
    }

    static int ctlOf(int rs, int wc) { return rs | wc; }
    // 取c的低29位
    static int workerCountOf(int c)  { return c & CAPACITY; }
}
