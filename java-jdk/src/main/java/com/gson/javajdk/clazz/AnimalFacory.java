package com.gson.javajdk.clazz;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;

public class AnimalFacory {
    /**只能用来触达 公共方法**/
    private static final MethodHandles.Lookup lookup = MethodHandles.publicLookup();
    /**无参构造函数 方法类型**/
    private static final MethodType NO_ARG_CTOR = MethodType.methodType(void.class);
    /**无参返回Animal 方法类型**/
    private static final MethodType NO_ARG_RETURNING_ANIMAL = MethodType.methodType(Animal.class);


    /**
     * 生产对象，clazz一定要有无参构造函数
     * @param clazz
     * @return
     */
    public static final Animal createInstance(Class<? extends Animal> clazz) {
        try {
            MethodHandle noArgMH = lookup.findConstructor(clazz, NO_ARG_CTOR);
            MethodHandle noArgReturnAnimalMH = noArgMH.asType(NO_ARG_RETURNING_ANIMAL);
            return (Animal) noArgReturnAnimalMH.invokeExact();
        } catch (Throwable e) {
            throw new IllegalArgumentException("Cannot lookup accessible no-arg constructor for: " + clazz.getName(), e);
        }
    }

    public static void main(String[] args) {
        Animal instance = AnimalFacory.createInstance(Cat.class);
    }
}
