package com.gsonkeno.spring.jdbc;

import java.util.List;

/**
 * Created by gaosong on 2018-04-04
 */
public interface UserService {

    public void saveUser(User user);

    public List<User> getUsers();
}
