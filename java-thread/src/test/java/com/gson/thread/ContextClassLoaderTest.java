package com.gson.thread;

/**
 * 线程上下文类加载器
 */
public class ContextClassLoaderTest {
    public static void main(String[] args) throws InstantiationException, IllegalAccessException {
        Thread thread = new Thread();
        ClassLoader contextClassLoader = thread.getContextClassLoader();
        //  新线程没有设置ContextClassLoader，将继承其父线程的上下文类加载器,看Thread类的构造函数即可
        System.out.println(contextClassLoader);
        //输出：null 因为Thread.class 是引导类加载加载的，所以其父类加载器 是为空
        ClassLoader classLoader = thread.getClass().getClassLoader();
        System.out.println(classLoader);
    }
}
