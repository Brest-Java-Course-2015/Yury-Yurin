package com.epam.brest.course2015.project.service;

import com.epam.brest.course2015.project.core.User;
import com.epam.brest.course2015.project.dao.UsersDao;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Random;

public class UsersServiceImpl implements UsersService {

    private static final Logger LOGGER = LogManager.getLogger();
    Random random  = new Random();
    private Integer r;
    private int N = 10;


    private UsersDao usersDao;

    public void setUsersDao(UsersDao usersDao) {
        this.usersDao = usersDao;
    }

    @Override
    public Integer addUser(String login, String hash) {
        try {
            List<User> userList = usersDao.getAllUsers();
            if (userList.size() != 0) {
                for (User user : userList) {
                    if (user.getLogin().equals(login)) {
                        return -1;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        Assert.notNull(login);
        Assert.notNull(hash);
        return usersDao.addUser(login,hash,r,N);
    }

    @Override
    public Boolean checkUser(String login, String hash) {
        LOGGER.info("Login: " + login + "; Password: " + hash);
        Assert.notNull(login);
        Assert.notNull(hash);
        try {
            User user = usersDao.getUser(login);
            if(user!=null) {
                String testHash = user.getHash();
                if(testHash.equals(getHash(hash))) {
                    usersDao.setNewUserHash(login,hash,user.getN()-1);
                    return true;
                }
            }
        }
        catch (Exception e) {
            return false;
        }
        return false;
    }

    @Override
    public List<User> getAllUsers() {
        try {
            return usersDao.getAllUsers();
        }
        catch (Exception e) {
            return null;
        }
    }

    @Override
    public Integer generateR() {
        r = random.nextInt(100000);
        return r;
    }

    @Override
    public Integer getN(String login) {
        try {
            N = usersDao.getN(login);
            return N - 1;
        }
        catch (Exception e) {
            return -1;
        }
    }

    @Override
    public Integer getNewN() {
        N = 10;
        return N;
    }

    @Override
    public Integer getR(String login) {
        try {
            r = usersDao.getR(login);
            return r;
        }
        catch (Exception e) {
            return -1;
        }
    }

    public String getHash(String hash) {
        hash = DigestUtils.md5Hex(hash);
        return hash;
    }
}
