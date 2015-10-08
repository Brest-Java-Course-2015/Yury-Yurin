package com.epam.brest.course2015.dao;

import com.epam.brest.course2015.dao.rowmappers.UserMapper;
import com.epam.brest.course2015.domain.User;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.List;

/**
 * Created by blondeks on 7.10.15.
 */
public class UserDaoImpl implements UserDao {
    private UserMapper userMapper = new UserMapper();
    private NamedParameterJdbcTemplate jdbcTemplate;
    public UserDaoImpl(DataSource dataSource) {
        jdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    @Override
    public List<User> getAllUsers() {
        return jdbcTemplate.query("select * from user", userMapper);
    }

    @Override
    public User getUserById(Integer id) {
        HashMap hashMap = new HashMap();
        hashMap.put("id",id);
        return jdbcTemplate.queryForObject("select * from user where userid=:id",hashMap,userMapper);
    }
}
