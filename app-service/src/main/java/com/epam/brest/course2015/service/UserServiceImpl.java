package com.epam.brest.course2015.service;

import com.epam.brest.course2015.dao.UserDao;
import com.epam.brest.course2015.domain.User;
import com.epam.brest.course2015.dto.UserDto;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;

@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    private static final Logger LOGGER = LogManager.getLogger();

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public List<User> getAllUsers() {
        LOGGER.debug("getAllUsers()");
        return userDao.getAllUsers();
    }

    @Override
    public Integer addUser(User user) {
        Assert.notNull(user, "user can't be NULL");
        Assert.isNull(user.getUserId(), "userId should be null");
        Assert.notNull(user.getLogin(), "userLogin can't be null");
        if (user.getLogin().equals("admin")) {
            LOGGER.error("user login can't be 'admin' ");
            throw new IllegalArgumentException("user login can't be 'admin' ");
        }
        userDao.addUser(user.getUserId(), user.getLogin(), user.getPassword(), user.getCreatedDate());
        return user.getUserId();

    }

    @Override
    public void logUser(User User) {
        LOGGER.debug("logUser(): user id = {} ", new User().getUserId());
    }

    @Override
    public UserDto getUserDto() {
        return null;
    }
}
