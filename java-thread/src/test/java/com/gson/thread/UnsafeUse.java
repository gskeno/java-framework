package com.gsonkeno.jdk.training.concurrent;

import org.junit.Test;
import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * Unsafe的使用
 * 1)获取Unsafe的实例，只有通过bootstrap 类加载机加载的类，才可以通过Unsafe.getUnsafe获取Unsafe实例
 *   其他方式最简单的方式是利用反射
 * Created by gaosong on 2018-04-01
 */
public class UnsafeUse {
    public static void main(String[] args) {

    }

    @Test
    public void getInInstance() throws NoSuchFieldException, IllegalAccessException {
        //抛出异常
//        Unsafe unsafe = Unsafe.getUnsafe();
//        System.out.println(unsafe);

        //利用反射获取Unsafe实例是可以的
         Field f = Unsafe.class.getDeclaredField("theUnsafe");
         f.setAccessible(true);
         Unsafe unsafe = (Unsafe) f.get(null);

    }
}
