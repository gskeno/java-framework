package com.gson.javajdk;

import java.lang.reflect.Method;

public class 获取方法名 {
    public static void main(String[] args) {
        获取方法名 o = new 获取方法名();
        o.common();
    }
    public void common(){
        Method enclosingMethod = new Object() {
        }.getClass().getEnclosingMethod();
        System.out.println(enclosingMethod.getName());

        String methodName = new Throwable().getStackTrace()[0].getMethodName();
        System.out.println(methodName);

        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        String methodName1 = stackTrace[1].getMethodName();
        System.out.println(methodName1);
    }
}
