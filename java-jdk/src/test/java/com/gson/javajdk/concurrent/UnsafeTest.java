package com.gson.javajdk.concurrent;

import org.junit.Test;
import sun.misc.Unsafe;

import java.lang.reflect.Field;

public class UnsafeTest {
    class Car{
        private String name;

        public void setName(String name) {
            this.name = name;
        }
    }
    @Test
    public void test() throws NoSuchFieldException, IllegalAccessException {
        Field theUnsafe = Unsafe.class.getDeclaredField("theUnsafe");
        theUnsafe.setAccessible(true);
        Unsafe unsafe = (Unsafe)theUnsafe.get(null);

        Thread thread = Thread.currentThread();

        long threadLocalRandomProbeOffset = unsafe.objectFieldOffset(Thread.class.getDeclaredField("threadLocalRandomProbe"));
        System.out.println(threadLocalRandomProbeOffset);

        Car car = new Car();
        car.setName("BenChi");
        long nameOffset = unsafe.objectFieldOffset(Car.class.getDeclaredField("name"));
        System.out.println(nameOffset);
        Object object = unsafe.getObject(car, nameOffset);
        System.out.println(object);
    }
}
