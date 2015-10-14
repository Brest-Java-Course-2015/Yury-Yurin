package com.epam.brest.course2015.service;

import com.epam.brest.course2015.domain.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.epam.brest.course2015.api.UserDao;
import java.util.List;

public class UserServiceImpl implements UserSrvice {

    private UserDao userDao;

    private static final Logger LOGGER = LogManager.getLogger();

    public void setUpUserDao(UserDao userDao)  {
        this.userDao=userDao;
    }
    @Override
    public List<User> getAllUsers() {
        LOGGER.debug("getAllUsers()");
        return userDao.getAllUsers();
    }

    @Override
    public void addUser(User user) {

    }
}
