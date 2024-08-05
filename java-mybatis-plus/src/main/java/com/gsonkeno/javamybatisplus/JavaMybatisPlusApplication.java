package com.gsonkeno.javamybatisplus;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.gsonkeno.javamybatisplus.mapper")
public class JavaMybatisPlusApplication {

    public static void main(String[] args) {
        SpringApplication.run(JavaMybatisPlusApplication.class, args);
    }
}
