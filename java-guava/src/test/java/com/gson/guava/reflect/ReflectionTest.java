package com.gson.guava.reflect;

import com.google.common.base.Function;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Lists;
import com.google.common.reflect.ClassPath;
import com.google.common.reflect.Invokable;
import com.google.common.reflect.Reflection;
import com.google.common.reflect.TypeToken;
import com.gson.guava.Foo;
import com.gson.guava.MathService;
import com.gson.guava.User;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
        // E
        System.out.println(returnType);

        // List<String>的get(int i)方法返回参数类型是什么?
        Invokable<List<String>, ?> invokableMethod = new TypeToken<List<String>>() {
        }.method(List.class.getMethod("get", int.class));
        // java.lang.String
        System.out.println(invokableMethod.getReturnType());

        // public方法
        Method operate = MathService.class.getMethod("operate", Object.class);
        Assert.assertTrue(Invokable.from(operate).isPublic());

        // package方法
        Method add = MathService.class.getDeclaredMethod("add", Number.class, Number.class);
        Assert.assertTrue(Invokable.from(add).isPackagePrivate());

        // protected方法
        Method divide = MathService.class.getDeclaredMethod("divide", Number.class, Number.class);
        Assert.assertTrue(Invokable.from(divide).isProtected());

        // 私有方法
        Method multi = MathService.class.getDeclaredMethod("multi", Number.class, Number.class);
        Assert.assertTrue(Invokable.from(multi).isPrivate());
    }

    @Test
    public void testProxy(){
        Foo foo = Reflection.newProxy(Foo.class, new InvocationHandler() {

            // 未增强被代理对象，要增强被代理对象的话，需要建立一个InvocationHandler的子类，
            // 并把被代理对象target 注入进去, 使用method.invoke(target)
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println("methodName is " + method.getName() + " and args=" + Arrays.toString(args));
                return null;
            }
        });
        foo.say("hello");
    }

    @Test
    public void testClassPath() throws IOException {
        ClassPath classPath = ClassPath.from(this.getClass().getClassLoader());
        ImmutableSet<ClassPath.ClassInfo> topLevelClasses = classPath.getTopLevelClasses("com.gson.guava");
        System.out.println(topLevelClasses.size());
        for(ClassPath.ClassInfo classInfo : topLevelClasses){
            System.out.println(classInfo);
        }
    }

    @Test
    public void testReflectInitialize(){
        Reflection.initialize(User.class);
    }
}
