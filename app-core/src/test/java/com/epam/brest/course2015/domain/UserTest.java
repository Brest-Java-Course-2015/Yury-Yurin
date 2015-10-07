package com.epam.brest.course2015.domain;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserTest {

    private User user;

    @Before
    public void setUp() throws Exception {
    user = new User();
    }

    @Test
    public void testGetUserId() throws Exception {
    user.setUserId((Integer)10);
        assertEquals((Integer)10,user.getUserId());
    }

    @Test
    public void testSetUserId() throws Exception {
    Integer id = user.getUserId();
           user.setUserId(id);
           assertEquals(id,user.getUserId());
    }

    @Test
    public void testGetLogin() throws Exception {
        user.setLogin("LOGIN");
        assertEquals("LOGIN",user.getLogin());
    }

    @Test
    public void testSetLogin() throws Exception {
        String login = user.getLogin();
        user.setLogin(login);
        assertEquals(login,user.getLogin());
    }

    @Test
    public void testGetPassword() throws Exception {
    user.setPassword("12345abcde");
        assertEquals("12345abcde",user.getPassword());
    }

    @Test
    public void testSetPassword() throws Exception {
       String password = user.getPassword();
        user.setPassword(password);
        assertEquals(password,user.getPassword());

    }

    @Test
    public void testGetCreatedDate() throws Exception {
        java.util.Date date = new java.util.Date();
    user.setCreatedDate(date);
     assertEquals(date,user.getCreatedDate());
    }

    @Test
    public void testSetCreatedDate() throws Exception {
        java.util.Date date = user.getCreatedDate();
        user.setCreatedDate(date);
        assertEquals(date,user.getCreatedDate());
    }
}