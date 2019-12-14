package com.gson.springaop;


import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

import java.lang.reflect.Method;
import java.util.Arrays;

public class ServiceMethodInteceptor implements MethodInterceptor {

    @Override
    public Object invoke(MethodInvocation methodInvocation) {
        try {
            long begin = System.currentTimeMillis();
            //获取切入点的类名
            String className = methodInvocation.getThis().getClass().getName();
            //获取切入点方法
            Method method = methodInvocation.getMethod();
            //获取方法参数
            Object[] arguments = methodInvocation.getArguments();
            //获取方法的执行结果
            Object methodResult = methodInvocation.proceed();

            System.out.println("类:" + className + "\n"
            + "方法名:" + method + "\n"
            + "方法入参:" + Arrays.toString(arguments) + "\n"
            + "返回值:" + methodResult + "\n"
            + "耗时:" + (System.currentTimeMillis() - begin) + "ms" + "\n");
            return methodResult;
        }catch (Throwable e){
            System.out.println(e);
            return null;
        }
    }
}
