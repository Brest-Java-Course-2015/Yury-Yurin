package com.epam.brest.course2015.project.rest;

import com.epam.brest.course2015.project.core.Malfunction;
import com.epam.brest.course2015.project.service.MalfunctionService;
import com.fasterxml.jackson.core.JsonProcessingException;
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

import java.util.ArrayList;
import java.util.List;

import static org.easymock.EasyMock.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(value = "classpath*:mock-test-spring-rest.xml")
public class MockMalfunctionRestControllerTest {

    @Autowired
    private MalfunctionService malfunctionService;

    @Resource
    private MalfunctionRestController malfunctionRestController;

    private Malfunction malfunction = new Malfunction(null,"name","auto","description",1);

    private MockMvc mockMvc;


    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(malfunctionRestController)
                .setMessageConverters(new MappingJackson2HttpMessageConverter()).build();
    }

    @After
    public void tearDown() {
        verify(malfunctionService);
        reset(malfunctionService);
    }

    @Test
    public void TestAddMalfunction() throws Exception {
        expect(malfunctionService.addMalfunction(anyObject(Malfunction.class))).andReturn(1);
        replay(malfunctionService);
        String newMalfunction = new ObjectMapper().writeValueAsString(malfunction);
        mockMvc.perform(put("/malfunction").accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(newMalfunction))
                .andDo(print())
                .andExpect(status().isCreated());

    }

    @Test
    public void TestUpdateMalfunction() throws Exception {
        //malfunction.setMalfunctionId(1);
        malfunctionService.updateMalfunction(anyObject(Malfunction.class));
        expectLastCall();
       String newMalfunction = new ObjectMapper().writeValueAsString(malfunction);
        replay(malfunctionService);
        mockMvc.perform(post("/malfunction/update").accept(MediaType.APPLICATION_JSON)
        .contentType(MediaType.APPLICATION_JSON)
        .content(newMalfunction))
                .andDo(print())
                .andExpect(status().isAccepted())
                .andExpect(content().string(""));
    }


    @Test
    public void TestDeleteMalfunction() throws Exception {
        malfunctionService.deleteMalfunction(1);
        expectLastCall();
        replay(malfunctionService);
        mockMvc.perform(delete("/malfunction/delete/1"))
                .andDo(print())
                .andExpect(status().isOk());

    }

    @Test
    public void TestGetMalfunctionById() throws Exception {
        malfunction.setMalfunctionId(1);
        expect(malfunctionService.getMalfunctionById(1)).andReturn(malfunction);
        expectLastCall();
        replay(malfunctionService);
        mockMvc.perform(get("/malfunction/1").accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());

    }

    @Test
    public void TestGetMalfunctionsByApplicationId() throws Exception {
        malfunction.setApplicationId(1);
        List<Malfunction> malfunctionList = new ArrayList<Malfunction>();
        malfunctionList.add(malfunction);
        expect(malfunctionService.getAllMalfunctionsByIdApplication(1)).andReturn(malfunctionList);
        replay(malfunctionService);
        mockMvc.perform(get("/malfunctions/1").accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());

    }

    @Test
    public void TestAddCosts() throws Exception {
        malfunctionService.addCostsToMalfunction(1,1000,2000,3000);
        expectLastCall();
        replay(malfunctionService);
        mockMvc.perform(post("/malfunction/1/1000/2000/3000"))
                .andDo(print())
                .andExpect(status().isOk());

    }
}
