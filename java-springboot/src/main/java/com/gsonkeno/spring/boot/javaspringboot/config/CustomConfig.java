package com.gsonkeno.spring.boot.javaspringboot.config;

import com.gsonkeno.spring.boot.javaspringboot.model.Student;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CustomConfig {

    @Value("${name}")
    private String customName;

    public String getCustomName() {
        return customName;
    }

    public void setCustomName(String customName) {
        this.customName = customName;
    }

    @Bean
    public Student student(){
        Student student = new Student();
        student.setName(customName);
        return student;
    }
}
