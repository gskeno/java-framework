package com.gson.guava;

import com.google.common.collect.Lists;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class ListsTest {

    @Test
    public void test(){
        User user1 = new User();
        user1.setSite("A");
        user1.setName("Gs");

        List<User> oldUsers = new ArrayList<>();
        oldUsers.add(user1);

        ArrayList<User> newUsers = Lists.newArrayList(oldUsers);
        newUsers.remove(user1);
        System.out.println(newUsers);
        System.out.println(oldUsers);
    }
}
