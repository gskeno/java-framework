package com.gson.spring;

import com.gson.spring.bean.Tree;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class PlaceholderConfigurerTest {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("classpath*:placeholder.xml");
        Tree tree = context.getBean(Tree.class);
        System.out.println(tree);
        //用处不大，运行中修改
        CustomPlaceholderConfigurer configurer = context.getBean(CustomPlaceholderConfigurer.class);
        configurer.map.put("height","78");
        System.out.println(tree);
    }

}
