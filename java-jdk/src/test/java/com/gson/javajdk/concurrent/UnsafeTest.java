package com.gson.javajdk.concurrent;

import org.junit.Assert;
import org.junit.Test;
import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.util.Arrays;

public class UnsafeTest extends Assert{
    private static Unsafe unsafe;
    static {
        //获取声名的Unsafe类下的theSafe字段
        Field theUnsafe = null;
        try {
            theUnsafe = Unsafe.class.getDeclaredField("theUnsafe");
            //由于private修饰，要想通过反射访问，setAccessible(true)
            theUnsafe.setAccessible(true);
            unsafe = (Unsafe)theUnsafe.get(null);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

    }

    class Car{
        private String name;
        public void setName(String name) {
            this.name = name;
        }
    }

    @Test
    public void test() throws NoSuchFieldException, IllegalAccessException {
        Thread thread = Thread.currentThread();
        //unsafe获取Thread类的包反问可访问的属性 threadLocalRandomProbe的对象起始地址偏移量
        long PROBE = unsafe.objectFieldOffset(Thread.class.getDeclaredField("threadLocalRandomProbe"));
        //位置偏移量就是240
        Assert.assertTrue(PROBE ==240);
        //通过unsafe输出当前Thread的包范围可访问属性threadLocalRandomProbe 的值
        int probeValue = unsafe.getInt(thread, PROBE);
        //初始值就是0
        Assert.assertTrue(probeValue == 0);
        //PROBE重新设置为89
        unsafe.putInt(thread, PROBE, 89);
        Assert.assertEquals(unsafe.getInt(thread, PROBE), 89);


        Car car = new Car();
        car.setName("BenChi");
        long nameOffset = unsafe.objectFieldOffset(Car.class.getDeclaredField("name"));
        System.out.println(nameOffset);
        Object object = unsafe.getObject(car, nameOffset);
        System.out.println(object);
    }

    /**
     * 测试数组对象偏移量
     * Object,Integer,Long数组表现一致
     */
    @Test
    public void testArrayOffset(){
        Object[] a = new Object[8];
        //Object数组的首元素偏移地址为16
        int arrayBaseOffsetA = unsafe.arrayBaseOffset(a.getClass());
        assertEquals(arrayBaseOffsetA, 16);
        int objectIndexScale = unsafe.arrayIndexScale(a.getClass());
        //Object数组元素的空间大小为4
        assertEquals(objectIndexScale, 4);

        Integer[] b = new Integer[8];
        int arrayBaseOffsetB = unsafe.arrayBaseOffset(b.getClass());
        assertEquals(arrayBaseOffsetB, 16);
        int intIndexScale = unsafe.arrayIndexScale(b.getClass());
        assertEquals(intIndexScale, 4);

        Long[] c = new Long[8];
        int arrayBaseOffsetC = unsafe.arrayBaseOffset(c.getClass());
        assertEquals(arrayBaseOffsetC, 16);
        int longIndexScale = unsafe.arrayIndexScale(c.getClass());
        assertEquals(longIndexScale, 4);

        //将long数组的前3个元素都设置为1
        unsafe.putOrderedObject(c, arrayBaseOffsetC + longIndexScale *0, 1L);
        unsafe.putOrderedObject(c, arrayBaseOffsetC + longIndexScale *1, 1L);
        unsafe.putOrderedObject(c, arrayBaseOffsetC + longIndexScale *2, 1L);
        assertArrayEquals(c, new Long[]{1L,1L,1L, null, null, null, null, null});
    }
}
