package com.gsonkeno.spring.jdbc;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by gaosong on 2018-04-04
 */
public class UserRowMapper implements RowMapper<User> {
    @Override
    public User mapRow(ResultSet set, int i) throws SQLException {
        User person = new User(set.getInt("id"),set.getString("name"),
                set.getInt("age"), set.getString("sex"));
        return person;
    }
}
