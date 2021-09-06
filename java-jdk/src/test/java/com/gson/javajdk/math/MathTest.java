package com.gson.javajdk.math;

import org.junit.Test;

public class MathTest {

    /**
     *
     * 计算出的值真实世界里应该是正数，这里依然是负数
     */
    @Test
    public void testLongMinValueDivideNegative1(){
        Long a = Long.MIN_VALUE;
        System.out.println(a);
        // /-1被优化为一条 取负指令，原所有bit位取反，末尾再加1
        long l = a / -1;
        System.out.println(l);

        long b = -1L;
        long c= a/b; // 指令无法优化，这里是一条除指令，在C编译器下会溢出
        System.out.println(c);
    }
}
