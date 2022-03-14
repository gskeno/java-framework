package com.gson.springaop;

import com.gson.springaop.annotation.LogTag;
import org.springframework.aop.framework.AopContext;

public class LogService {
    @LogTag
    public String getA(){
        // 配合expose-proxy=true和AopContext.currentProxy()才能使方法
        // 内部自我调用增强
        String b = ((LogService) AopContext.currentProxy()).getB();
        // String b = getB();
        return "a" + b;
    }

    @LogTag
    public String getB(){
        return "b";
    }
}
