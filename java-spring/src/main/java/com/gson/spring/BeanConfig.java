package com.gson.spring;

import com.gson.spring.bean.Engine;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {

    // 创建bean后，执行initMethod指定的方法
    @Bean(initMethod = "start")
    public Engine engine(){
        return new Engine();
    }
}
