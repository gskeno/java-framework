package com.gson.spring.olympic;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(value="classpath:olympic_beans.xml")
public class OlympicTests {

    @Autowired
    private BasketBall basketBall;

    @Test
    public void test1(){
        basketBall.description();
    }
}
