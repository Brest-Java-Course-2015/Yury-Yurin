package com.epam.brest.course2015.service;

import com.epam.brest.course2015.api.UserDao;
import com.epam.brest.course2015.domain.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Assert;

import java.util.Date;
import java.util.List;

import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.replay;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:test-spring-service-mock.xml"})
public class UserServiceMockTest {

    private static final Logger LOGGER = LogManager.getLogger();
    private static final User user = new User(null,"log1","pass1",new Date());
    @Autowired
    private UserService userService;

    @Autowired
    private UserDao mockUserDao;

    @Test
    public void testLogUser() throws Exception {
        replay(mockUserDao);
        userService.logUser(new User(10, "22222", "22222", new Date()));
    }


    @Test
    public void testAddUser() {
        expect(mockUserDao.addUser(user)).andReturn(5);
        replay(mockUserDao);
        Assert.isTrue(userService.addUser(user)==5);
    }

    @Test
    public void testGetAllUsers() {
        List<User> list = userService.getAllUsers();
        expect(mockUserDao.getAllUsers()).andReturn(list);
        replay(mockUserDao);
    }
}
