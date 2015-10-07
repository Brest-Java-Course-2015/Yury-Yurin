package com.epam.brest.course2015.domain;

import org.junit.Before;
import org.junit.Test;

import java.sql.Date;

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
    Integer a=5;
       if(a>0) {
           user.setUserId(a);
           assertEquals(a,user.getUserId());
       }
    }

    @Test
    public void testGetLogin() throws Exception {
        user.setLogin("LOGIN");
        assertEquals("LOGIN",user.getLogin());
    }

    @Test
    public void testSetLogin() throws Exception {
        String login = user.getLogin();
        assertNull(login);
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
       String password = new String();
        user.setPassword(password);
        assertEquals(password,user.getPassword());

    }

    @Test
    public void testGetCreatedDate() throws Exception {
    // user.setCreatedDate(Date.valueOf("12.02.2014"));
      //  assertEquals(Date.valueOf("12.02.2014"),user.getCreatedDate());
    }

    @Test
    public void testSetCreatedDate() throws Exception {

    }
}