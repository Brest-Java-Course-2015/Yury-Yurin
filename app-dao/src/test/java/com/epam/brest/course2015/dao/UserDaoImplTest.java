package com.epam.brest.course2015.dao;

import com.epam.brest.course2015.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.activation.DataSource;

import static org.junit.Assert.*;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:test-spring-dao.xml"})

public class UserDaoImplTest {

    @Autowired
    private UserDao userDao;

    @Test
    public void testGetAllUsers() throws Exception {
        List<User> users = userDao.getAllUsers();
        assertNotNull(users);
    }

    @Test
    public void testGetUserById() throws Exception {
        User user = userDao.getUserById(2);
        assertNotNull(user.getLogin());
    }
}