package com.gson.spring.olympic;


import com.gson.spring.bean.House;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 引入spring-test包
 * 利用Runwith
 * 利用ContextConfiguration 支持(多个)配置文件和(多个)配置类
 *
 * 可以读取main/resources目录和test/resources目录下的资源文件
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(value="classpath:olympic_beans_test.xml")
public class OlympicTests {

    @Autowired
    private BasketBall basketBall;

    @Autowired
    private House house;

    @Test
    public void test1(){
        basketBall.description();

        System.out.println(house.getChair());
    }
}
