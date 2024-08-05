package com.gsonkeno.javamybatisplus;

import com.gsonkeno.javamybatisplus.entity.Membership;
import com.gsonkeno.javamybatisplus.mapper.MembershipMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = JavaMybatisPlusApplication.class)
public class TestApplication {
    @Autowired
    private MembershipMapper membershipMapper;

    @Test
    public void test(){
        List<Membership> memberships = membershipMapper.selectList(null);
        System.out.println(memberships);
    }
}
