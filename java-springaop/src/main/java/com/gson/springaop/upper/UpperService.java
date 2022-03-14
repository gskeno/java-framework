package com.gson.springaop.upper;

import com.gson.springaop.Constants;
import com.gson.springaop.services.ReadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UpperService {
    @Autowired
    private ReadService readService;

    public String upper(){
        String serviceResult = readService.read("upper");
        if (!Constants.SERVICE_EXCETION.equals(serviceResult)){
            return "service层未出现异常,结果是" + serviceResult;
        }
        throw new RuntimeException("service层出现了异常，被service层拦截器捕捉到了");
    }
}
