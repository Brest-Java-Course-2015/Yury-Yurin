package com.epam.brest.course2015.service;

import com.epam.brest.course2015.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:test-spring-service.xml"})
@Transactional
public class UserServiceImplTest {

    @Autowired
    private UserService userService;

    @Test
    public void testGetAllUsers() throws Exception {
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddNullUser() throws Exception {
        userService.addUser(null);
    }

    @Test
    public void testAddUser() throws Exception {
        User user = new User();
        user.setUserId(null);
        user.setLogin("login");
        user.setPassword("2323");
        user.setCreatedDate(new Date());
        userService.addUser(user);
    }
}