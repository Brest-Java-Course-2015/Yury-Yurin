package com.epam.brest.course2015.domain;

import org.junit.Before;

import static org.junit.Assert.*;
public class UserTest {
    private User user;
    @org.junit.Test
    @Before
    public void setUp() throws Exception {
        user = new User();
    }
    public void testGetLogin() throws Exception {
          user.setLogin("LOGIN");
          assertEquals("LOGIN",user.getLogin());
    }
}