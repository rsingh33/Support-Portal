package com.citi.spring.web.dao.rowMappers;

import com.citi.spring.web.dao.entity.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRowMapper implements RowMapper<User> {
    @Override
    public User mapRow(ResultSet resultSet, int i) throws SQLException {
        User user = new User();


        user.setAuthority(resultSet.getString("authority"));
        user.setId(resultSet.getInt("id"));
        user.setEnabled(resultSet.getBoolean("enabled"));
        user.setusername(resultSet.getString("username"));
        user.setPassword(resultSet.getString("password"));
        user.setEmail(resultSet.getString("email"));
        user.setResetToken(resultSet.getString("reset_token"));
        user.setName(resultSet.getString("name"));


        return user;
    }
}
