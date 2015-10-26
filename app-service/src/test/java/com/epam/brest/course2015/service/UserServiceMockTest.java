package com.epam.brest.course2015.service;

import com.epam.brest.course2015.dao.UserDao;
import com.epam.brest.course2015.domain.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.easymock.EasyMock.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:test-spring-service-mock.xml"})
public class UserServiceMockTest {

    private static final Logger LOGGER = LogManager.getLogger();
    private static final User user = new User("log1","pass1");

    @Autowired
    private UserService userService;

    @Autowired
    private UserDao mockUserDao;


    @After
    public void clean() {
        verify(mockUserDao);
        reset(mockUserDao);
    }

    @Test
    public void testLogUser() throws Exception {
        replay(mockUserDao);
        userService.logUser(user);
    }


    @Test
    public void testAddUser() {
        expect(mockUserDao.addUser(user)).andReturn(5);
        replay(mockUserDao);
        int id = mockUserDao.addUser(user);
        Assert.assertTrue(id == 5);
    }
}
