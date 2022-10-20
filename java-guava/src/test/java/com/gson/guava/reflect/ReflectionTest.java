package com.gson.guava.reflect;

import com.google.common.base.Function;
import com.google.common.collect.Lists;
import com.google.common.reflect.Invokable;
import com.google.common.reflect.TypeToken;
import com.gson.guava.User;
import org.junit.Assert;
import org.junit.Test;

import java.lang.reflect.Method;
import java.util.ArrayList;

public class ReflectionTest extends Assert {
    @Test
    public void testReflect(){
        ArrayList<String> stringList = Lists.newArrayList();
        ArrayList<Integer> intList = Lists.newArrayList();
        assertTrue(stringList.getClass().isAssignableFrom(intList.getClass()));
        // returns true, even though ArrayList<String> is not assignable from ArrayList<Integer>
    }

    /**
     * TypeToken的使用场景
     * 构造typeToken
     * resolve范型TypeParameter
     * typeToken.getType获取实际类型
     */
    @Test
    public void testTypeToken(){
        TypeToken<Function<Integer, String>> funcTypeToken = new TypeToken<Function<Integer, String>>(){};
        TypeToken<?> typeToken0 = funcTypeToken.resolveType(Function.class.getTypeParameters()[0]);
        TypeToken<?> typeToken1 = funcTypeToken.resolveType(Function.class.getTypeParameters()[1]);
        assertSame(Integer.class, typeToken0.getType());
        assertSame(String.class, typeToken1.getType());
    }

    @Test
    public void testInvokable() throws NoSuchMethodException {
        Method getMethod = ArrayList.class.getMethod("get", int.class);
        TypeToken<?> returnType = Invokable.from(getMethod).getReturnType();
        System.out.println(returnType);
    }
}
