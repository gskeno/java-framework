package com.gsonkeno.javagenerics;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;

public class GenericUtils {
    public static Class<?> getParentGenericType(Class<?> clazz)
    {
        // 获取父类的GenericSuperclass信息
        Type genericSuperclass = clazz.getGenericSuperclass();

        if (genericSuperclass instanceof ParameterizedType) {
            ParameterizedType parameterizedType = (ParameterizedType) genericSuperclass;
            // 获取父类的泛型参数
            Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
            Type actualTypeArgument = actualTypeArguments[0];
            return (Class<?>)actualTypeArgument;
        }
        throw new RuntimeException("该类没有父类泛型参数");
    }

    public static Class<? extends Event> getParentInterfaceGenericType(Class<? extends EventHandler> clazz){
        // 获取所有实现的接口
        Type[] genericInterfaces = clazz.getGenericInterfaces();

        for (Type genericInterface : genericInterfaces) {
            if (genericInterface instanceof ParameterizedType) {
                ParameterizedType parameterizedType = (ParameterizedType) genericInterface;
                // 获取接口的泛型参数
                Type[] actualTypeArguments = parameterizedType.getActualTypeArguments();
                return (Class<? extends Event>)actualTypeArguments[0];
            }
        }
        throw new RuntimeException("该类没有父类泛型参数");
    }
}
