package com.gson.javajdk.clazz;

import org.junit.Test;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;

public class MethodTypeTest {
    @Test
    public void test() {
        MethodHandles.Lookup lookup = MethodHandles.publicLookup();
        //无参构造函数,返回类型为Void, 无入参类型
        MethodType NO_ARG_CTOR = MethodType.methodType(void.class);
        //返回类型为ANIMAL,无入参
        MethodType NO_ARG_RETURNING_ANIMAL = MethodType.methodType(Animal.class);

        try {
            //方法句柄
            MethodHandle constructor = lookup.findConstructor(Dog.class, NO_ARG_CTOR);
            System.out.println(constructor.type());
            //将返回类型向上提升
            MethodHandle methodHandle = constructor.asType(NO_ARG_RETURNING_ANIMAL);
            System.out.println(methodHandle.type());

            Animal animal1 = (Animal) methodHandle.invokeExact();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }
}
