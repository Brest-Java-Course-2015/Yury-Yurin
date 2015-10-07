package com.epam.brest.course2015.dao;

import com.epam.brest.course2015.domain.User;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.List;

/**
 * Created by blondeks on 7.10.15.
 */
public class UserDaoImpl implements UserDao {

    private JdbcTemplate jdbcTemplate;

    public UserDaoImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @Override
    public List<User> getAllUsers() {
        return null;
    }

    @Override
    public User getUserById() {
        return null;
    }
}
