package com.gsonkeno.spring.jdbc.transaction;

import com.gsonkeno.spring.jdbc.User;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Random;

/**
 * Created by gaosong on 2018-04-17
 */
public class UserServiceImplTest {
    private static ClassPathXmlApplicationContext context;
    private static UserService1 userService1;
    @BeforeClass
    public static void init() {
        context = new ClassPathXmlApplicationContext("Jdbc.xml");
        userService1= (UserService1) context.getBean("userService1");
    }
    public static void main(String[] args) {
        User user = new User();
        user.setSex("男");
        user.setAge(20);
        user.setName("何");

        userService1.saveUser(user);
    }

    @Test
    public void testUpdate(){
        User user = new User(3,"张",new Random().nextInt(),"男");
        userService1.updateUser(user);
    }
}
