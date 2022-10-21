package com.gson.guava.basic.utility;

import com.google.common.base.Function;
import com.google.common.collect.Lists;
import com.google.common.collect.Ordering;
import com.gson.guava.User;
import org.junit.Test;

import javax.annotation.Nullable;
import java.util.List;

public class OrderTest {
    @Test
    public void testOrder(){
        Ordering<User> userOrdering = Ordering.natural().nullsFirst().onResultOf(new Function<User, String>() {
            @Nullable
            @Override
            public String apply(@Nullable User input) {
                return input != null ? input.getName() : null;
            }
        });
        List<User> userList = Lists.newArrayList(
                new User("site1", "name1"),
                new User("site3", "name3"),
                new User("site2", "name2"),
                null);
        List<User> users = userOrdering.sortedCopy(userList);
        System.out.println(users);
    }
}
