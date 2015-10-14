package com.epam.brest.course2015.service;

import com.epam.brest.course2015.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.*;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:test-spring-service.xml"})
@Transactional()
public class UserServiceImplTest {

    @Autowired
    private UserSrvice userService;

    @Test
    public void testGetAllUsers() throws Exception {
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddNullUser() throws Exception {
        userService.addUser(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddlUser() throws Exception {
        User user = new User();
        user.setUserId(1);
        userService.addUser(user);
    }
}