package com.epam.brest.course2015.rest;

import com.epam.brest.course2015.domain.User;
import com.epam.brest.course2015.dto.UserDto;
import com.epam.brest.course2015.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import javax.annotation.Resource;
import java.util.Arrays;

import static org.easymock.EasyMock.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:test-spring-rest-mock.xml"})
public class UserRestControllerMockTest {
    @Resource
    private UserRestController userRestController;

    private MockMvc mockMvc;

    @Autowired
    private UserService userService;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(userRestController)
                .setMessageConverters(new MappingJackson2HttpMessageConverter()).build();
    }

    @After
    public void tearDown() {
        verify(userService);
        reset(userService);
    }

    @Test
    public void testAddUser() throws Exception {
        expect(userService.addUser(anyObject(User.class))).andReturn(3);
        replay(userService);
        String user = new ObjectMapper().writeValueAsString(new User());
        mockMvc.perform(post("/user/login1/password1").accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(user))
                .andDo(print())
                .andExpect(status().isCreated()).andExpect(content().string("3"));
    }

    @Test
    public void testGetAllUsers() throws Exception {
        expect(userService.getAllUsers()).andReturn(Arrays.<User>asList(new User()));
        replay(userService);
        mockMvc.perform(get("/users").accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void testGetUserDto() throws Exception {
        UserDto userDto = new UserDto();
        userDto.setUsers(Arrays.asList(new User("login1","pass1"),
                new User("login2","pass2"),new User("login3","pass3")));
        userDto.setTotal(3);
        expect(userService.getUserDto()).andReturn(userDto);
        replay(userService);
        mockMvc.perform(get("/userdto").accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }
}
