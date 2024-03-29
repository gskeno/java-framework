package com.gson.springjdbc.transaction;

import com.gson.springjdbc.User;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by gaosong on 2018-04-17
 */
@Transactional(propagation = Propagation.REQUIRED)
public interface UserService1 {
    public void saveUser(User user);

    public void updateUser(User user);
}
