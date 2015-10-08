package com.epam.brest.course2015.dao.rowmappers;

import com.epam.brest.course2015.domain.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by blondeks on 9.10.15.
 */
public class UserMapper implements RowMapper<User> {
        @Override
        public User mapRow(ResultSet resultSet, int i) throws SQLException {
            User user = new User();
            user.setUserId(resultSet.getInt("userId"));
            user.setLogin(resultSet.getString("login"));
            user.setPassword(resultSet.getString("password"));
            return user;
        }
}
