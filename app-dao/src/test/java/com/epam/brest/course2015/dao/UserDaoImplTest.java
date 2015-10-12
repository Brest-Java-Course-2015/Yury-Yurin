package com.epam.brest.course2015.dao;

import com.epam.brest.course2015.api.UserDao;
import com.epam.brest.course2015.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

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

    @Test
    public void testDeleteUser() {
        int size = userDao.getAllUsers().size();
        userDao.deleteUser("userLogin1");
        assertNotEquals(userDao.getAllUsers().size(), size);
    }

    @Test
    public void testAddUser() {
        int size = userDao.getAllUsers().size();
        userDao.addUser(4,"userLogin4","54321");
        assertNotEquals(userDao.getAllUsers().size(), size);
    }
}